<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-right: 10px;" @click="handleCreate" type="primary"
                 icon="el-icon-edit">新增菜单
      </el-button>
      <el-input clearable style="width: 200px;" class="filter-item" v-model="permitQueryList.name"
                placeholder="菜单名"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="getPermitList">搜索</el-button>
    </div>
    <el-table :data="permitList" stripe style="width: 100%">
      <el-table-column align="center" label='菜单编号' min-width="100">
        <template slot-scope="scope">
          {{scope.row.permitId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='父菜单编号' min-width="100">
        <template slot-scope="scope">
          {{scope.row.parentPermitId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='菜单名' min-width="150">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='图标' min-width="120">
        <template slot-scope="scope">
          {{scope.row.icon}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='URL' min-width="120">
        <template slot-scope="scope">
          {{scope.row.url}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='状态' min-width="50">
        <template slot-scope="scope">
          {{scope.row.status}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='创建时间' min-width="150">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='更新时间' min-width="150">
        <template slot-scope="scope">
          {{scope.row.lastUpdateTime}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="deletePermit(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @current-change="handleCurrentChange"
                     :current-page="currentPage" :page-size="pageSize"
                     layout="total, prev, pager, next, jumper" :total="totalCount">
      </el-pagination>
    </div>

    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="clearForm('permitForm')">
      <el-form :model="permitForm" class="demo-ruleForm" :rules="rules" ref="permitForm" label-width="100px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="权限ID:" prop="permitId">
          <el-input v-model="permitForm.permitId" :disabled="isEdit"></el-input>
        </el-form-item>
        <el-form-item label="父权限ID:" prop="parentPermitId">
          <el-input v-model="permitForm.parentPermitId"></el-input>
        </el-form-item>
        <el-form-item label="菜单名称:" prop="name">
          <el-input v-model="permitForm.name"></el-input>
        </el-form-item>
        <el-form-item label="图标:" prop="icon">
          <el-input v-model="permitForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="URL:" prop="url">
          <el-input v-model="permitForm.url"></el-input>
        </el-form-item>
        <el-form-item label="状态:" prop="status">
          <el-input v-model="permitForm.status"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogTitle == '添加菜单'" type="primary" @click="createPermit">确定</el-button>
        <el-button v-else="dialogTitle == '编辑菜单'" type="primary" @click="updatePermit">确定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  export default {
    name: 'permitManage',
    data() {
      return {
        rules: {
          name: [{required: true, message: '请输入菜单名称', trigger: 'blur'},
            {max: 60, message: '长度不大于60', trigger: 'blur'},
            {pattern: /^[\S]*$/, message: '菜单名不能包含不可见字符'}]
        },
        currentPage: 1,
        pageSize: 5,
        totalCount: 0,
        permitList: [],
        permitQueryList: {
          name: ''
        },
        dialogFormVisible: false,
        dialogTitle: '',
        permitForm: {
          permitId:'',
          parentPermitId:'',
          name: '',
          icon: '',
          url: '',
          status: '',
        },
        isEdit: ''
      }
    },
    mounted() {
      this.getPermitList()
    },
    methods: {
      getPermitList() {
        let param = {
          'permitId': this.permitQueryList.name,
          'parentPermitId': this.permitQueryList.name,
          'name': this.permitQueryList.name,
          'currentPage': this.currentPage,
          'pageSize': this.pageSize,
        };
        let _this = this;
        this.ajax(_this.apiType().queryAllPermit, _this.serviceType().api, param, function (res) {
          _this.permitList = res.body;
          _this.totalCount = Number(res.totalSize);
        })
      },
      handleCurrentChange(currentPage) {
        this.$emit("currentPageChange", currentPage);
        this.currentPage = currentPage;
        this.getPermitList();
      },
      clearForm(formName) {
        this.$refs[formName].resetFields();
      },
      setData(row) {
        this.permitForm.permitId = row.permitId;
        this.permitForm.parentPermitId = row.parentPermitId;
        this.permitForm.name = row.name;
        this.permitForm.icon = row.icon;
        this.permitForm.url = row.url;
        this.permitForm.status = row.status;
      },
      handleCreate() {
        this.permitForm = {}
        this.dialogFormVisible = true
        this.dialogTitle = "添加菜单"
        this.isEdit = false
      },
      handleEdit(row) {
        this.dialogFormVisible = true
        this.dialogTitle = "编辑菜单"
        this.isEdit = true
        this.setData(row)
      },
      createPermit() {
        this.$refs['permitForm'].validate((valid) => {
          if (valid) {
            let param = {
              'permitId': this.permitForm.permitId,
              'parentPermitId': this.permitForm.parentPermitId,
              'name': this.permitForm.name,
              'icon': this.permitForm.icon,
              'url': this.permitForm.url,
              'status': this.permitForm.status,
            };
            let _this = this;
            this.ajax(this.apiType().permitAdd, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '新增菜单成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getPermitList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );

          } else {
            return false
          }

        });
      },
      updatePermit() {
        this.$refs['permitForm'].validate((valid) => {
          if (valid) {
            let param = {
              'permitId': this.permitForm.permitId,
              'parentPermitId': this.permitForm.parentPermitId,
              'name': this.permitForm.name,
              'icon': this.permitForm.icon,
              'url': this.permitForm.url,
              'status': this.permitForm.status
            };
            let _this = this;
            this.ajax(this.apiType().permitModify, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '修改菜单成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getPermitList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );
          } else {
            return false
          }

        });
      },
      deletePermit(row) {
        this.$confirm('此操作将永久删除菜单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {
            'permitId': row.permitId,
          };
          let _this = this;
          this.ajax(this.apiType().permitDelete, this.serviceType().api, param, function (responseData) {
              _this.$message({
                showClose: true,
                message: '删除成功!',
                type: 'success',
                duration: 2000
              });
              _this.getPermitList();
            }, function (responseData) {
              _this.$alert('请求错误：' + responseData.message);
            }
          );
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
