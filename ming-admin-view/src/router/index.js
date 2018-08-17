import Vue from 'vue'
import Router from 'vue-router'

const _import = require('./_import_' + process.env.NODE_ENV)

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

//初始化component路径
Vue.prototype.initComponent = function () {
  var supportRouter = new Map();
  supportRouter.set("userManage", _import('permit/userManage'));
  supportRouter.set("roleManage", _import('permit/roleManage'));
  supportRouter.set("permitManage", _import('permit/permitManage'));
  return supportRouter;
}


Vue.prototype.constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index'),
      name: '首页',
      meta: {title: '首页', icon: 'user'}
    }]
  }
]

export default new Router({
  scrollBehavior: () => ({y: 0}),
  routes: this.constantRouterMap
})
