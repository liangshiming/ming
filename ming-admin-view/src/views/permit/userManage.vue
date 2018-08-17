<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-right: 10px;" @click="handleCreate" type="primary"
                 icon="el-icon-edit">新增用户
      </el-button>
      <el-input clearable style="width: 200px;" class="filter-item" v-model="userQueryList.userId"
                placeholder="用户ID"></el-input>
      <el-input clearable style="width: 200px;" class="filter-item" v-model="userQueryList.name"
                placeholder="用户名"></el-input>
      <el-select clearable class="filter-item" style="width: 200px" v-model="userQueryList.status" placeholder="用户状态">
        <el-option v-for="item in  userStatus" :key="item.key" :label="item.display_name" :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="getUserList">搜索</el-button>
    </div>
    <el-table :data="userList" stripe style="width: 100%; min-height: 400px;">
      <el-table-column align="center" label='用户编号' min-width="150">
        <template slot-scope="scope">
          {{scope.row.userId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='用户名' min-width="150">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='用户昵称' min-width="150">
        <template slot-scope="scope">
          {{scope.row.nickName}}
        </template>
      </el-table-column>
      <el-table-column align="center" label='账号状态' min-width="150">
        <template slot-scope="scope">
          {{scope.row.status|userStatusFilter}}
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
          <el-button type="danger" size="mini" @click="deleteUser(scope.row)">删除</el-button>
          <el-button type="info" size="mini" @click="openUserRoleEdit(scope.row)">角色</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination background @current-change="handleCurrentChange"
                     :current-page="currentPage" :page-size="pageSize"
                     layout="total, prev, pager, next, jumper" :total="totalCount">
      </el-pagination>
    </div>


    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="clearForm('userForm')">
      <el-form :model="userForm" class="demo-ruleForm" :rules="rules" ref="userForm" label-width="120px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="用户账号:" prop="userId">
          <el-input v-model="userForm.userId" :disabled="isEdit"></el-input>
        </el-form-item>
        <el-form-item label="用户名称:" prop="name">
          <el-input v-model="userForm.name"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称:" prop="nickName">
          <el-input v-model="userForm.nickName"></el-input>
        </el-form-item>
        <el-form-item label="用户密码:" prop="password" v-show="show">
          <el-input v-model="userForm.password" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="passwordConfirm" v-show="show">
          <el-input v-model="userForm.passwordConfirm" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户状态:" prop="status">
          <el-radio v-model="userForm.status" value="1" label="1">可用</el-radio>
          <el-radio v-model="userForm.status" value="0" label="0">不可用</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogTitle == '添加用户'" type="primary" @click="createUser">确定</el-button>
        <el-button v-else="dialogTitle == '编辑用户'" type="primary" @click="updateUser">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色" width='360px' center :visible.sync='modifyUserRoleDialog' :show-close='false'>
      <el-table ref='multipleTable' height="290" :data="roleList" border stripe size='small'
                @selection-change="selectRoleChange">
        <el-table-column type="selection" width="60"></el-table-column>
        <el-table-column label="角色名" prop="name">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click='closeBang("multipleTable")'>取 消</el-button>
        <el-button type="primary" @click='userRoleEdit'>确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  const userStatus = [
    {key: '1', display_name: '可用'},
    {key: '0', display_name: '不可用'}
  ]
  const userStatusKeyValue = userStatus.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})
  export default {
    name: 'userManage',
    data() {
      let validatePasswordConfirm = (rule, value, callback) => {
        if (value === '') {
          if (this.show) {
            callback(new Error('请再次输入密码'));
          } else {
            callback();
          }
        } else if (value !== this.userForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      let validatePassword = (rule, value, callback) => {
        if (value === '' || !value) {
          if (this.show) {
            callback(new Error('请输入密码'));
          } else {
            callback();
          }
        } else {
          if (value.length < 6) {
            callback(new Error('密码长度不小于6位'));
          } else if (value.length > 18) {
            callback(new Error('密码长度不大于18位'));
          } else if (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$/.test(value) == false) {
            callback(new Error('密码必须是数字和字母组合'));
          } else {
            callback();
          }

        }
      };
      return {
        rules: {
          userId: [{required: true, message: '请输入用户账号', trigger: 'blur'},
            {max: 50, message: '长度不大于50', trigger: 'blur'},
            {pattern: /[0-9a-zA-Z_.]{1,50}$/, message: '用户账号只能由数字和字母_.组成'}
          ],
          name: [{required: true, message: '请输入用户名称', trigger: 'blur'},
            {max: 60, message: '长度不大于60', trigger: 'blur'},
            {pattern: /^[\S]*$/, message: '用户名不能包含不可见字符'}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'},
            {validator: validatePassword, trigger: 'blur'}
          ],
          passwordConfirm: [
            {required: true, message: '请输入确认密码', trigger: 'blur'},
            {validator: validatePasswordConfirm, trigger: 'blur'}
          ],
          status: [
            {required: true, message: '请选择状态', trigger: 'blur'}
          ],
        },
        currentPage: 1,
        pageSize: 10,
        totalCount: 0,
        userList: [],
        roleList: [],
        userStatus,
        userQueryList: {
          userId: '',
          name: '',
          status: ''
        },
        dialogFormVisible: false,
        dialogTitle: '',
        userForm: {
          userId: '',
          name: '',
          nickName: '',
          password: '',
          passwordConfirm: '',
          status: '',
        },
        show: '',
        isEdit: '',
        modifyUserRoleDialog: false,
        isIndeterminate: true,
        currentEditUserId: '',
        checkRoleIdList: []
      }
    },
    mounted() {
      this.getUserList()
      this.queryRoleList()
    },
    methods: {
      getUserList() {
        let param = {
          'userId': this.userQueryList.userId,
          'name': this.userQueryList.name,
          'status': this.userQueryList.status,
          'currentPage': this.currentPage,
          'pageSize': this.pageSize,
        };
        let _this = this;
        this.ajax(_this.apiType().queryAllUser, _this.serviceType().api, param, function (res) {
          _this.userList = res.body;
          _this.totalCount = Number(res.totalSize);
        })
      },
      handleCurrentChange(currentPage) {
        this.$emit("currentPageChange", currentPage);
        this.currentPage = currentPage;
        this.getUserList();
      },
      queryRoleList() {
        let param = {};
        let _this = this;
        this.ajax(_this.apiType().queryAllRole, _this.serviceType().api, param, function (res) {
          let list = res.body;
          for (let i = 0; i < list.length; i++) {
            list[i].status = '0';
          }
          _this.roleList = list;
        })
      },
      selectRoleChange(value) {
        this.checkBangStatus(value, this.roleList)
      },
      checkBangStatus(selectObj, allObj) {
        //将选择项的ID整成一个数组
        let arr = [];
        for (let i = 0; i < selectObj.length; i++) {
          arr.push(selectObj[i].roleId)
        }
        //循环对象，如果ID在arr中，则bang的状态调1否0
        for (let i = 0; i < allObj.length; i++) {
          allObj[i].status = arr.indexOf(allObj[i].roleId) >= 0 ? '1' : '0'
        }
      },
      clearForm(formName) {
        this.$refs[formName].resetFields();
      },
      setData(row) {
        this.userForm.userId = row.userId;
        this.userForm.name = row.name;
        this.userForm.nickName = row.nickName;
        this.userForm.status = row.status;
      },
      handleCreate() {
        this.userForm = {}
        this.inputCss(true);
        this.dialogFormVisible = true
        this.dialogTitle = "添加用户"
        this.show = true
        this.isEdit = false
      },
      inputCss(val) {
        let passwordRule = this.rules.password[0];
        this.$set(passwordRule, 'required', val);
        let passwordConfirm = this.rules.passwordConfirm[0];
        this.$set(passwordConfirm, 'required', val);
      },
      handleEdit(row) {
        this.inputCss(false)
        this.dialogFormVisible = true
        this.dialogTitle = "编辑用户"
        this.isEdit = true
        this.show = false
        this.setData(row)
      },
      createUser() {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            let param = {
              'userId': this.userForm.userId,
              'name': this.userForm.name,
              'nickName': this.userForm.nickName,
              'password': this.userForm.password,
              'passwordConfirm': this.userForm.passwordConfirm,
              'status': this.userForm.status
            };
            let _this = this;
            this.ajax(this.apiType().userAdd, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '新增用户成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getUserList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );

          } else {
            return false
          }

        });
      },
      updateUser() {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            let param = {
              'userId': this.userForm.userId,
              'name': this.userForm.name,
              'nickName': this.userForm.nickName,
              'status': this.userForm.status,
            };
            let _this = this;
            this.ajax(this.apiType().userModify, this.serviceType().api, param, function (responseData) {
                _this.dialogFormVisible = false;
                _this.$message({
                  showClose: true,
                  message: '修改用户成功',
                  type: 'success',
                  duration: 2000
                });
                _this.getUserList();
              }, function (responseData) {
                _this.$alert('请求错误：' + responseData.message);
              }
            );
          } else {
            return false
          }

        });
      },
      deleteUser(row) {
        this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {
            'userId': row.userId,
          };
          let _this = this;
          this.ajax(this.apiType().userDelete, this.serviceType().api, param, function (responseData) {
              _this.$message({
                showClose: true,
                message: '删除成功!',
                type: 'success',
                duration: 2000
              });
              _this.getUserList();
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
      openUserRoleEdit(row) {
        this.modifyUserRoleDialog = true;
        this.currentEditUserId = row.userId
        let param = {
          'userId': row.userId,
        };
        let _this = this;
        this.ajax(this.apiType().queryUserRoleList, this.serviceType().api, param, function (res) {
            let userRoleList = res.body;
            _this.clearRoleListChecked()
            if (userRoleList != null) {
              for (let i = 0; i < userRoleList.length; i++) {
                for (let j = 0; j < _this.roleList.length; j++) {
                  if (userRoleList[i].roleId == _this.roleList[j].roleId) {
                    _this.roleList[j].status = '1'
                  }
                }
              }
            }
          }, function (responseData) {
            _this.$alert('请求错误：' + responseData.message);
          }
        );
        setTimeout(() => {
          this.checkBangDefault('multipleTable', this.roleList)
        }, 100)
      },
      checkBangDefault(formname, arr) {
        let newArr = []
        for (let i = 0; i < arr.length; i++) {
          if (arr[i].status === '1') {
            newArr.push(arr[i])
          }
        }
        if (newArr.length > 0) {
          newArr.forEach(row => {
            this.$refs[formname].toggleRowSelection(row);
          })
        }
      },
      closeBang(name) {
        this.modifyUserRoleDialog = false;
        this.currentEditUserId = ''
        this.checkRoleIdList = []
        this.$refs[name].clearSelection();
      },
      clearRoleListChecked() {
        let list = this.roleList;
        for (let i = 0; i < list.length; i++) {
          list[i].status = '0';
        }
        this.roleList = list;
      },
      userRoleEdit() {
        let roleIdArray = new Array();
        let val = this.roleList;
        for (let item in val) {
          if (val[item].status == '1') {
            roleIdArray.push(val[item].roleId);
          }
        }
        let param = {
          'userId': this.currentEditUserId,
          'roleListStr': roleIdArray.toString()
        };
        let _this = this;
        this.ajax(this.apiType().userRoleModify, this.serviceType().api, param, function (responseData) {
            _this.closeBang('multipleTable')
            _this.$message({
              showClose: true,
              message: '修改用户角色成功',
              type: 'success',
              duration: 2000
            });
            _this.getUserList();
          }, function (responseData) {
            _this.$alert('请求错误：' + responseData.message);
          }
        );
      }
    },

    filters: {
      userStatusFilter(status) {
        return userStatusKeyValue[status]
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
