<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-right: 10px;" @click="handleCreate" type="primary"
                 icon="el-icon-edit">新增角色
      </el-button>
      <el-input clearable style="width: 200px;" class="filter-item" v-model="roleQueryList.name"
                placeholder="角色名"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="getRoleList">搜索</el-button>
    </div>
    <el-table :data="roleList" stripe style="width: 100%; min-height: 400px;">
      <el-table-column align="center" label='角色编号' min-width="150">
        <template slot-scope="scope">
          {{scope.row.roleId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='角色名' min-width="150">
        <template slot-scope="scope">
          {{scope.row.name}}
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
          <el-button type="danger" size="mini" @click="deleteRole(scope.row)">删除</el-button>
          <el-button type="info" size="mini" @click="openRolePermitEdit(scope.row)">权限</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @current-change="handleCurrentChange"
                     :current-page="currentPage" :page-size="pageSize"
                     layout="total, prev, pager, next, jumper" :total="totalCount">
      </el-pagination>
    </div>

    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="clearForm('roleForm')">
      <el-form :model="roleForm" class="demo-ruleForm" :rules="rules" ref="roleForm" label-width="120px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="角色名称:" prop="name">
          <el-input v-model="roleForm.name"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogTitle == '添加角色'" type="primary" @click="createRole">确定</el-button>
        <el-button v-else="dialogTitle == '编辑角色'" type="primary" @click="updateRole">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配权限 " width='360px' center :visible.sync='modifyRolePermitDialog' :show-close='false'>
      <el-tree :data="permitTree" :props="{children: 'permitTreeList',label: 'name'}"
               accordion
               show-checkbox
               default-expand-all
               check-strictly
               node-key="permitId"
               ref="permitTree"></el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click='closeBang()'>取 消</el-button>
        <el-button type="primary" @click='rolePermitEdit'>确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: 'roleManage',
    data() {
      return {
        rules: {
          name: [{required: true, message: '请输入角色名称', trigger: 'blur'},
            {max: 60, message: '长度不大于60', trigger: 'blur'},
            {pattern: /^[\S]*$/, message: '角色名不能包含不可见字符'}]
        },
        currentPage: 1,
        pageSize: 5,
        totalCount: 0,
        roleList: [],
        roleQueryList: {
          name: ''
        },
        dialogFormVisible: false,
        dialogTitle: '',
        roleForm: {
          roleId: '',
          name: ''
        },
        isEdit: '',
        modifyRolePermitDialog: false,
        currentEditRoleId: '',
        permitTree: [],
      }
    },
    mounted() {
      this.getRoleList()
      this.queryAllPermitTree()
    },
    methods: {
      getRoleList() {
        let param = {
          'name': this.roleQueryList.name,
          'currentPage': this.currentPage,
          'pageSize': this.pageSize,
        };
        let _this = this;
        this.ajax(_this.apiType().queryAllRole, _this.serviceType().api, param, function (res) {
          _this.roleList = res.body;
          _this.totalCount = Number(res.totalSize);
        })
      },
      queryAllPermitTree() {
        let param = {};
        let _this = this;
        this.ajax(_this.apiType().queryAllPermitTree, _this.serviceType().api, param, function (res) {
          let list = res.body;
          _this.permitTree = list;
        })
      },
      handleCurrentChange(currentPage) {
        this.$emit("currentPageChange", currentPage);
        this.currentPage = currentPage;
        this.getRoleList();
      },
      clearForm(formName) {
        this.$refs[formName].resetFields();
      },
      setData(row) {
        this.roleForm.roleId = row.roleId;
        this.roleForm.name = row.name;
      },
      handleCreate() {
        this.roleForm = {}
        this.dialogFormVisible = true
        this.dialogTitle = "添加角色"
        this.isEdit = false
      },
      handleEdit(row) {
        this.dialogFormVisible = true
        this.dialogTitle = "编辑角色"
        this.isEdit = true
        this.setData(row)
      },
      createRole() {
        this.$refs['roleForm'].validate((valid) => {
          if (valid) {
            let param = {
              'name': this.roleForm.name
            };
            let _this = this;
            this.ajax(this.apiType().roleAdd, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '新增角色成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getRoleList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );

          } else {
            return false
          }

        });
      },
      updateRole() {
        this.$refs['roleForm'].validate((valid) => {
          if (valid) {
            let param = {
              'roleId': this.roleForm.roleId,
              'name': this.roleForm.name
            };
            let _this = this;
            this.ajax(this.apiType().roleModify, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '修改角色成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getRoleList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );
          } else {
            return false
          }

        });
      },
      deleteRole(row) {
        this.$confirm('此操作将永久删除角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {
            'roleId': row.roleId,
          };
          let _this = this;
          this.ajax(this.apiType().roleDelete, this.serviceType().api, param, function (responseData) {
              _this.$message({
                showClose: true,
                message: '删除成功!',
                type: 'success',
                duration: 2000
              });
              _this.getRoleList();
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
      openRolePermitEdit(row) {
        this.modifyRolePermitDialog = true;
        this.currentEditRoleId = row.roleId
        let param = {
          'roleId': row.roleId,
        };
        let _this = this;
        this.ajax(this.apiType().queryRolePermitList, this.serviceType().api, param, function (res) {
            let rolePermitList = res.body;
            let keyList = new Array();
            for (let item in rolePermitList) {
              keyList.push(rolePermitList[item].permitId)
            }
            _this.$refs.permitTree.setCheckedKeys(keyList)
          }, function (responseData) {
            _this.$alert('请求错误：' + responseData.message);
          }
        );
      },
      closeBang() {
        this.modifyRolePermitDialog = false;
        this.currentEditRoleId = ''
      },
      rolePermitEdit() {
        let permitListArray = this.$refs.permitTree.getCheckedKeys()
        let param = {
          'roleId': this.currentEditRoleId,
          permitListStr: permitListArray.toString()
        };
        let _this = this;
        this.ajax(this.apiType().rolePermitModify, this.serviceType().api, param, function (res) {
            _this.closeBang()
            _this.$message({
              showClose: true,
              message: '修改权限成功',
              type: 'success',
              duration: 2000
            });
          }, function (responseData) {
            _this.$alert('请求错误：' + responseData.body.message);
          }
        );
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
