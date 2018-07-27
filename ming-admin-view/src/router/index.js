import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/views/layout/Layout'

export const constantRouterMap = [
    { path: '/login', component: () => import('@/views/login/index'), hidden: true },
    {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '首页',
        meta: {title: '首页', icon: 'user'}
      }
    ]
  },
  {
    path: '/project',
    component: Layout,
    redirect: '/project/index',
    meta: {
      title: '项目',
      icon: 'company'
    },
    children: [{
      path: 'index',
      component: () => import('@/views/project/projectManage'),
      name: '项目管理',
      meta: {title: '项目管理', icon: 'card-list'}
    }, {
      path: 'product',
      component: () => import('@/views/project/projectAnalysis'),
      name: '项目统计',
      meta: {title: '项目统计', icon: 'card-image'}
    }]
  }
]
export default new Router({
  routes: constantRouterMap
})
