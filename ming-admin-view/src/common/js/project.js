/**
 * Created by Administrator on 2017/7/25 0025.
 */
import Vue from 'vue'

Vue.prototype.apiType = function() {
  return {
    getToken: 'getToken',
    login: 'login', // 登录
    queryPermitTree: 'queryPermitTree', // 查询菜单
    queryAllPermitTree: 'queryAllPermitTree', // 查询菜单
    queryAllUser: 'queryAllUser',
    queryAllRole: 'queryAllRole',
    queryAllPermit: 'queryAllPermit',
    queryUserRoleList:'queryUserRoleList',
    queryRolePermitList:'queryRolePermitList',
    userAdd:'userAdd',
    userModify:'userModify',
    userRoleModify:'userRoleModify',
    userDelete:'userDelete',
    roleAdd:'roleAdd',
    roleModify:'roleModify',
    rolePermitModify:'rolePermitModify',
    roleDelete:'roleDelete',
    permitAdd:'permitAdd',
    permitModify:'permitModify',
    permitDelete:'permitDelete',
    userRoleList:'userRoleList'
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
    loginToken: this.getSessionStorage('loginToken'),
    operator: this.getSessionStorage('operator'),
    token:this.getSessionStorage('token'),
    nickName:this.getSessionStorage('nickName')
  }
}
