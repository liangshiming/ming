
const session = {
  state: {
    sessionStorage: {}
  },

  mutations: {
    SET_SESSION_STORAGE: (state, obj) => {
      state.sessionStorage[obj.key] = obj.value
    }
  },
  actions: {
    GenerateSession({
       commit
      }, data) {
      return new Promise(resolve => {
        commit('SET_SESSION_STORAGE', data)
        resolve()
      })
    }
  }
}

export default session
