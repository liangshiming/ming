import Vue from "vue";
import store from '@/store'

Vue.prototype.routerTree = [];

Vue.prototype.getReqToken = function (businessType ,callBackFunc) {
  let _this = this;
  _this.ajax(_this.apiType().fetchReqToken, _this.serviceType().api, {}, function (responseData) {
    if (!_this.getSessionStorage(businessType + "-reqToken")) {
    _this.setSessionStorage(businessType + "-reqToken", responseData.token);
  }
    callBackFunc();
  }, function (responseData) {
      _this.$message.error("请求发生异常");
    _this.removeSessionStorage(businessType + "-reqToken");
  });
};

Vue.prototype.setSessionStorage = function (key, value) {
  let obj = {};
  obj.key = key;
  obj.value = value;
  store.dispatch('GenerateSession', obj).then(() => {});
  window.sessionStorage.setItem(key, value);
  return;
};
Vue.prototype.getSessionStorage = function (key) {
  let value = store.getters.sessionStorage[key];
  if(!value){
    value = window.sessionStorage.getItem(key);
    if(value){
      Vue.prototype.setSessionStorage(key, value);
    }
  }
  if (!value || value === '') {
    return '';
  }
  return value;
};
Vue.prototype.removeSessionStorage = function (key) {
  window.sessionStorage.removeItem(key);
  return;
};

Vue.prototype.setPermitTreeList = function (key, value) {
  let obj = {};
  obj.key = key;
  obj.value = value;
  store.dispatch('GenerateSession', obj).then(() => {});
  window.sessionStorage.setItem(key, value);
  return;
};
Vue.prototype.getPermitTreeList = function (key) {
  let value = store.getters.sessionStorage[key];
  if(!value){
    value = window.sessionStorage.getItem(key);
    if(value){
      Vue.prototype.setPermitTreeList(key, value);
    }
  }
  if (!value || value === '') {
    return '';
  }
  return value;
};

Vue.prototype.removePermitTreeList = function (key) {
   window.sessionStorage.removeItem(key);
  return;
};


Vue.prototype.getDictList = function (key,callback) {
  let value = store.getters.dictList[key];
  if(!value){
    Vue.prototype.ajax(Vue.prototype.apiType().queryDictList, Vue.prototype.serviceType().query, {"dictId": key}, function(responseData) {
      let dict = responseData.body;
      let obj = {};
      obj.key = key;
      obj.value = dict;
      store.dispatch('GenerateDict', obj ).then(() => {});
      callback(dict)
    });
  }else {
    callback(value);
  }
};

Vue.prototype.checkToken = function (key) {
  let reg = /^[02468][0-9a-z]{32}$/;
  return reg.test(key);
};

Vue.prototype.formatMessage = function (str) {
  let ary = str.split('<br/>');
  let arr = [];
  const h = this.$createElement;
  for (let i in ary) {
    arr.push(h('p', null, ary[i]));
  }
  return arr;
};
Date.prototype.Format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1,                 //月份
    "d+": this.getDate(),                    //日
    "h+": this.getHours(),                   //小时
    "m+": this.getMinutes(),                 //分
    "s+": this.getSeconds(),                 //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds()             //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};

Vue.prototype.formatMoney = function (val) {
  let array = Number(val).toFixed(2).toString().split('.');
  return array[0].replace(/(?=((?!\b)\d{3})+$)/g, ',') + '.' + array[1];
};

Vue.prototype.formatToInt = function (val) {
  let array = Number(val).toFixed(0).toString().split('.');
  return array[0].replace(/(?=((?!\b)\d{3})+$)/g, ',');
};

Vue.prototype.removeArrayValue = function (array, val) {
  for (var i = 0; i < array.length; i++) {
    if (array[i] == val) {
      array.splice(i, 1);
      break;
    }
  }
};
Vue.prototype.upperCase = function (value) {
  if (value) {
    return value.toUpperCase();
  }
}

Vue.prototype.$messages = function (value) {
  this.$message({
    showClose: true,
    message: value,
    type: 'error',
    duration: 0
  });
};
Vue.prototype.errorMessage = function (responseData) {
  let message = responseData.message;
  const h = this.$createElement;
  this.$msgbox({
    title: "提示",
    message: h('div', null, this.formatMessage(message)),
    customClass: 'errorMessageStl',
    showCancelButton: false
  });
};
// 身份证号字母转换为大写
Vue.prototype.filterID = function (value) {
  if (value) {
    return value.toUpperCase();
  } else {
    return '';
  }
};

function getApiName(url) {
	if(!url){
		return null;
	}
	url = url.split('?')[1];
  let reg = new RegExp("(^|&)apiName=([^&]*)(&|$)", "i");
  let r = url.match(reg);
  if (r != null) return unescape(r[2]);
  return null;
};
