<template>
  <el-scrollbar wrapClass="scrollbar-wrapper">
    <el-menu mode="vertical"
             :show-timeout="200"
             :default-active="$route.path"
             :collapse="isCollapse"
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#409EFF">
      <sidebar-item :routes="permissionRouters"></sidebar-item>
    </el-menu>
  </el-scrollbar>
</template>

<script>
  import {mapGetters} from 'vuex'
  import SidebarItem from './SidebarItem'
  import Vue from 'vue'
  export default {
    components: {SidebarItem},
    data() {
      return {
        permissionRouters: []
      }
    },
    computed: {
      ...mapGetters([
        'permission_routers',
        'sidebar'
      ]),
      isCollapse() {
        return !this.sidebar.opened
      }
    },
    mounted() {
      this.getPermissionRouters();
    },
    methods: {
      getPermissionRouters() {
        this.permissionRouters = Vue.prototype.constantRouterMap.concat(JSON.parse(this.getPermitTreeList("permitTreeList")))
      }
    },
  }
</script>
