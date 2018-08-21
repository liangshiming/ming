<template>
  <div class="login-container">
    <div
      style="background-image: url(../../static/img/background.jpg);height: 100%; width: 100%; display: block;position:fixed;background-size:cover;"></div>
    <el-form autoComplete="off" label-position="left" label-width="0px" class="card-box login-form">
      <h3 class="title">后台管理系统</h3>
      <el-form-item prop="email">
				<span class="svg-container">
                  <svg-icon icon-class="user"></svg-icon>
                </span>
        <el-input name="userName" type="text" v-model="userName" autoComplete="off" placeholder="登录名">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
				<span class="svg-container">
                  <svg-icon icon-class="lock"></svg-icon>
                </span>
        <el-input name="password" type="password" v-model="password" autoComplete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item prop="graphCode">
				<span class="svg-container">
				  <svg-icon icon-class="identifying-code"></svg-icon>
				</span>
        <el-input name="graphCode" style="width:40%;" type="graphCode" @keyup.enter.native="handleLogin"
                  v-model="loginGraphCode" autoComplete="on" placeholder="验证码">
        </el-input>
        <img v-bind:src="graphCodeUrl" style="float: right;padding: 6px" @click="getPermitGraphCode()">
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:330px" :loading="loading" @click.native.prevent="handleLogin">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      const validateUserName = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入正确的用户名'));
        } else {
          callback();
        }
      };
      const validateGraphCode = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入正确的验证码'));
        } else {
          callback();
        }
      };
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('密码不能小于6位'));
        } else {
          callback();
        }
      };
      return {
        route: this.$route,
        params: this.$route.params,
        query: this.$route.query,
        apiName: {
          login: this.apiType().login
        },
        userName: '',
        password: '',
        loading: false,
        loginGraphCode: '',
        graphCodeUrl: ''
      }
    },
    mounted() {
      this.getPermitGraphCode();
    },
    methods: {
      getPermitGraphCode() {
        this.graphCodeUrl = this.apiUrl().serverPath + "getGraphCode?token=" + this.getSessionStorage("token") + "&d=" + Math.random();
      },
      handleLogin() {
        let param = {
          'operator': this.userName,
          'password': this.password,
          'verifyCode': this.loginGraphCode
        };
        let _this = this;
        this.ajax(this.apiName.login, this.serviceType().api, param, function (responseData) {
          _this.setSessionStorage("loginToken", responseData.loginToken);
          _this.setSessionStorage('operator', responseData.operator);
          _this.setSessionStorage('nickName', responseData.nickName);
          _this.$router.push({
            path: '/'
          });
        }, function (responseData) {
          _this.errorMessage(responseData);
          _this.getPermitGraphCode();
        });
      }
    }
  }</script>

<style rel="stylesheet/scss" lang="scss">
  @import "src/styles/mixin.scss";

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 5px;
  }

  .login-container {
    @include relative;
    height: 100vh;
    background-color: #2d3a4b;
    input:-webkit-autofill {
      -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
      -webkit-text-fill-color: #fff !important;
    }
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 8px;
      color: #eeeeee;
      height: 47px;
    }
    .el-input {
      display: inline-block;
      height: 47px;
      width: 80%;
      padding: 10px 0 10px 0;
    }
    .el-button {
      padding: 10px 15px;
    }
    .el-form-item__content {
      display: block;
    }
    .svg-container {
      display: inline-block;
      min-width: 36px;
      padding: 10px 5px 6px 15px;
      color: #889aa4;
      float: left;
      svg {
        float: initial;
        width: 18px;
        vertical-align: middle;
        height: 18px;
        margin-top: -3px;
      }
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 26px;
      font-weight: 400;
      color: #4891ee;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 400px;
      padding: 35px 35px 15px 35px;
      margin: 160px 0px 0px 848px;
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #fff;
    }
    .forget-pwd {
      color: #fff;
    }
  }
</style>
