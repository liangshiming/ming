const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  device: state => state.app.device,
  sessionStorage: state => state.session.sessionStorage,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  refreshStatus: state => state.user.refreshStatus
}
export default getters
