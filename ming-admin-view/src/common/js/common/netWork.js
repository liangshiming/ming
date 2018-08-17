import axios from 'axios';
import Vue from 'vue';
import {Loading} from 'element-ui';

let cancel, promiseArr = {}
const CancelToken = axios.CancelToken;
//请求拦截器
axios.interceptors.request.use(config => {
  //发起请求时，取消掉当前正在进行的相同请求
  if (promiseArr[config.url]) {
    promiseArr[config.url]('操作取消')
    promiseArr[config.url] = cancel
  } else {
    promiseArr[config.url] = cancel
  }
  return config
}, error => {
  return Promise.reject(error)
})
var _this = Vue.prototype;
//响应拦截器即异常处理
axios.interceptors.response.use(response => {
  return response
}, error => {
  if (error.message !== '操作取消') {
    if (error && error.response) {
      switch (error.response.status) {
        case 400:
          error.message = '错误请求'
          break;
        case 401:
          error.message = '未授权，请重新登录'
          break;
        case 403:
          error.message = '拒绝访问'
          break;
        case 404:
          error.message = '请求错误,未找到该资源'
          break;
        case 405:
          error.message = '请求方法未允许'
          break;
        case 408:
          error.message = '请求超时'
          break;
        case 500:
          error.message = '服务器端出错'
          break;
        case 501:
          error.message = '网络未实现'
          break;
        case 502:
          error.message = '网络错误'
          break;
        case 503:
          error.message = '服务不可用'
          break;
        case 504:
          error.message = '网络超时'
          break;
        case 505:
          error.message = 'http版本不支持该请求'
          break;
        default:
          error.message = `连接错误${error.response.status}`
      }
    } else {
      error.message = "连接到服务器失败"
    }
    ;
    _this.$message.error(error.message)
    return Promise.resolve(error.response)
  }
})

//设置默认请求头
axios.defaults.headers = {
  'X-Requested-With': 'XMLHttpRequest',
  'Cache-Control': 'no-cache',
  'Pragma': 'no-cache'
}

Vue.prototype.ajax = function (apiType, serviceType, params, cbfunc, errorfunc, hasLoading) {
  if (hasLoading !== false) {
    hasLoading = true;
  }
  this.doAjax(apiType, serviceType, params, cbfunc, errorfunc, hasLoading)
}

Vue.prototype.doAjax = function (apiType, serviceType, params, cbfunc, errorfunc, hasLoading) {
  var newData = Object.assign(this.paramBase(), params);
  let loadingInstance = undefined;
  if (hasLoading) {
    loadingInstance = Loading.service();
  }
  axios({
    method: 'post',
    url: this.apiUrl().serverPath + serviceType + '?apiName=' + apiType,
    params: newData,
    responseType: 'json',
    // cancelToken: new CancelToken(c => {
    //   cancel = c
    // }),
    timeout: 30000//请求超时时间30分钟
  }).then((responseData) => {
    var resultCode = responseData.data.resultCode;
    var message = responseData.data.message;
    if (hasLoading) {
      loadingInstance.close();
    }
    if (resultCode === this.requestStatus().success) {
      cbfunc(responseData.data);
      return;
    } else if (resultCode === this.requestStatus().sessionTimeOut) {
      this.removeSessionStorage("operator");
      this.removeSessionStorage("token");
      this.$message({
        showClose: true,
        message: message,
        onClose: function () {
          _this.removeSessionStorage("operator");
          _this.removeSessionStorage("token");
          location.reload();
        }
      });
      return;
    } else if (resultCode !== this.requestStatus().success && errorfunc) {
      errorfunc(responseData.data);
      return;
    } else {
      this.$message.error(message);
      return;
    }
  }).catch((response) => {
      if (hasLoading) {
        loadingInstance.close();
      }
      this.$message.error("请求发生异常");
    }
  )
};
