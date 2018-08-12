import Layout from '../views/layout/Layout'
import Vue from 'vue'

let baseUrl = '';
let base = '';

function formatTreeData(data) {
  data.component = data.permitTreeList ? Layout : Vue.prototype.initComponent().get(data.url) == null ? Layout : Vue.prototype.initComponent().get(data.url);
  data.hidden = false;
  if (!data.parentPermitId) {
    data.path = "/" + data.url;
    baseUrl = data.path;
    base = data.path;
    data.component = Layout;
    if (data.permitTreeList) {
      data.children = Vue.prototype.construction(data.permitTreeList);
    }
  } else {
    data.path = data.url;
    if (data.parentPermitId && data.permitTreeList) {
      data.path = baseUrl + "/" + data.url;
      base = base + "/" + data.url;
    }
    if (data.permitTreeList) {
      data.component = Layout;
      data.children = Vue.prototype.construction(data.permitTreeList);
    } else {
      data.component = Vue.prototype.initComponent().get(data.url) == null ? Layout : Vue.prototype.initComponent().get(data.url);
      data.meta = {title: data.name, icon: data.icon};
    }
  }
  if (data.url.indexOf("http") >= 0) {
    data.path = data.url;
  }
  data.meta = {title: data.name, icon: data.icon};
}

Vue.prototype.construction = function (datas) {
  if (!datas) {
    return {};
  }
  const trees = datas.filter(data => {
    formatTreeData(data);
    return true;
  })
  let array = base.split("/");
  base = "/" + array[1];
  return trees;
}
