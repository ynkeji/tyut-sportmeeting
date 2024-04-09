<template>
  <div class="login-container">
    <div class="login-form">
      <h1>欢迎登录</h1>
      <el-form :model="loginForm" label-width="80px" >
        <el-form-item label="用户名" prop="username" >
          <div class="input-with-link" style="white-space: nowrap;width: 300px" >
            <el-input v-model="loginForm.username" autocomplete="off"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="密&nbsp&nbsp&nbsp&nbsp码" prop="password">
          <div class="input-with-link" style="white-space: nowrap;width: 300px">
            <el-input type="password" v-model="loginForm.password" autocomplete="new-password"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="类&nbsp&nbsp&nbsp&nbsp型" prop="password">
          <div class="input-with-link" style="width: 300px">
            <el-radio-group v-model="loginForm.type">
              <el-radio label="管理员"></el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(loginForm)" style="width: 265px">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

  <el-alert title="success alert" type="success" />

</template>


<script>


import api from "@/request/request";
import {ElMessage} from "element-plus";
export default {
  data() {
    return {
      error:'',
      loginForm: {
        username: '',
        password: '',
        type:'',
      },
    }
  },
  methods: {
    submitForm(formName) {
      let that = this
      if (formName.username == "" || formName.password == "" || formName.captcha == "") {
        alert("您的输入内容有误，请核对后再进行登录");
      } else {
        const jsonForm = JSON.stringify(formName);
        console.log("请求登录")

        api.post('/login', jsonForm).then((res)=>{
          if (res.data.status === 1){
           console.log("登录成功")
            window.localStorage.setItem("token",res.data.data)
            console.log(res.data.data)
            that.$router.push('/admin-index')
          }else{
              ElMessage.error('登录失败，请检查账号密码是否正确！！！')
          }
        }).catch((err)=>{
          console.log(err)
        })
      }
    },
  },
}
</script>

<style scoped>
.login-container {
  background-image: url('@/assets/0001.jpg');
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form {
  background: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  width: 430px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-link {
  margin-left: 10px;
}

.captcha-image {
  width: 70px;
  height: 30px;
  margin-left: 10px;
}


</style>
