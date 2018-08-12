/**
 * Created by Administrator on 2017/7/25 0025.
 */
import Vue from 'vue'

Vue.prototype.apiType = function() {
  return {
    fetchToken: 'fetchToken',
    fetchReqToken: 'fetchReqToken',
    login: 'login', // 登录
    queryPermitTree: 'queryPermitTree', // 查询菜单
    modifyPassword: 'modifyPassword', // 修改密码
    queryDictList: 'queryDictList', // 查看字典项
    queryParamValue: 'queryParamValue',
    logout: 'logout',
    queryConfigPermitTree: 'queryConfigPermitTree', // 查询配置菜单树
    addPermitMenu: 'addPermitMenu', // 添加菜单
    modifyPermitMenu: 'modifyPermitMenu', // 修改菜单
    queryAllDbList: 'queryAllDbList', // 修改菜单
  }
}
Vue.prototype.serviceType = function() {
  return {
    api: 'api', // 页面刷新获取token
    query: 'query',
    export: 'export'
  }
}
Vue.prototype.requestStatus = function() {
  return {
    success: 'SUCCESS',
    sessionTimeOut: 'SYS_SESSION_TIME_OUT'
  }
}
Vue.prototype.paramBase = function() {
  return {
    apiVersion: '1.0',
    loginToken: this.getSessionStorage('token'),
    userId: this.getSessionStorage('userId')
  }
}
