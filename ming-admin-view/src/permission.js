import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import {getToken} from '@/utils/auth' // getToken from cookie
import Vue from 'vue'

NProgress.configure({showSpinner: false})// NProgress Configuration


router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  let operator = Vue.prototype.getSessionStorage("operator");
  if (operator) { // determine if there has token
    if (to.path === '/login') {
      next('/')
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      if (store.getters.refreshStatus) {
        next()
      } else {
        let _this = Vue.prototype;
        let sessionPermitTreeList = _this.getPermitTreeList("permitTreeList");
        if (sessionPermitTreeList === '') {
          _this.ajax(_this.apiType().queryPermitTree, _this.serviceType().api, {}, function (res) {
            let permitTreeList = res.body;
            let tree = _this.construction(permitTreeList);
            router.addRoutes(tree);
            _this.setPermitTreeList("permitTreeList", JSON.stringify(permitTreeList));
            store.commit("SET_REFRESH_STATUS" , '1');
            next({...to, replace: true})
          })
        } else {
          let tree = _this.construction(JSON.parse(sessionPermitTreeList));
          router.addRoutes(tree);
          store.commit("SET_REFRESH_STATUS" , '1');
          next({...to, replace: true})
        }
      }
    }
  } else {
    //非登录状态全部跳转到登录页
    if (to.path == '/login') {
      next();
    } else {
      next('/login');
    }
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
