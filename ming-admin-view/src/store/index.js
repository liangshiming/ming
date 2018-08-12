import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import session from './modules/session'
import tagsView from './modules/tagsView'
import user from './modules/user'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    session,
    tagsView,
    user
  },
  getters
})

export default store
