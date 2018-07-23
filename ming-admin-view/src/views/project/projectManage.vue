<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="项目名称"></el-input>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search">搜索</el-button>
    </div>
    <el-table :data="list" stripe style="width: 100%">
      <el-table-column label='项目编号' min-width="150">

      </el-table-column>
      <el-table-column label='项目名称' min-width="150">

      </el-table-column>
      <el-table-column label='客户编号' min-width="150">

      </el-table-column>
      <el-table-column label='客户编号' min-width="150">

      </el-table-column>
      <el-table-column label='客户编号' min-width="150">

      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: 'projectManage',
    data() {
      return {}
    }
  }
</script>

