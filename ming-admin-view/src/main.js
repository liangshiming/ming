import Vue from 'vue'
import App from './App'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets
import './styles/index.scss'; // 全局自定义的css样式
import './icons'

import router from './router'
import store from './store'
import './permission' // permission control
import './router/formatRouter'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import * as project from './common/js/project.js'
import * as projectPath from './common/js/projectPath.js'
import * as common from './common/js/common/common.js'
import * as filterCommon from './common/js/common/filterFunc.js'
import * as netWork from './common/js/common/netWork.js'
import './common/js/common/validate.js'

Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {
    App, project, projectPath, filterCommon, common, netWork
  },
  data: {
    eventHub: new Vue()
  }
})
