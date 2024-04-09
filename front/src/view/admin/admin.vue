<template>
  <el-container class="home_container">
    <!-- 头部区域 -->
    <el-header>
      <div>
        <img src="../../assets/logo.png" alt="" class="my-logo"/>
      </div>
      <div class="user">
        <el-text class="mx-1" type="primary">用户名:{{ username }} 身份:{{ ID }}</el-text>
        <el-button type="primary" @click="logout">退出登录</el-button>
      </div>
    </el-header>
    <!-- 页面主体区域 -->
    <el-container style="height: 100vh">
      <myaside></myaside>
    </el-container>
  </el-container>
</template>
<script>
import myaside from '../../components/aside/AdminAside.vue';
import {getCurrentInstance, onMounted} from "vue";
import api from "@/request/request";
import router from "@/router";
import {ElMessage} from "element-plus";

export default {

  data() {
    return {
      username:'',
      ID:''
    }
  },
  components: {
    myaside
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
      api.get("/logout",{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }})
    },

  },

  setup(){
    console.log("进入了admin界面")
    const ins = getCurrentInstance();
    onMounted(()=>{
      api.get("/admin",{headers:{
            "Content-Type":"application/json",
            "token":window.localStorage.getItem("token")
          }}).then((res)=>{
      if (res.data.status !== -2){

        try {
          ins.data.username = res.data.data.name;
          if (res.data.data.identity === 1){
            ins.data.ID = "管理员"
          }
        }catch (res){
          console.log(res)
        }


      }else {
        ElMessage.error('登录信息出错，请重新登录！')
        router.push("/login")
      }
      })
    });
  }
}
</script>


<style lang="less" scoped>
.home_container {
  height: 100%;
}

.el-header {
  background-color: #363d40;
  // 给头部设置一下弹性布局
  display: flex;
  // 让它贴标左右对齐
  justify-content: space-between;
  // 清空图片左侧padding
  padding-left: 0;
  // 按钮居中
  align-items: center;
  // 文本颜色
  color: #fff;
  // 设置文本字体大小
  font-size: 20px;
  // 嵌套
  > div {
    // 弹性布局
    display: flex;
    // 纵向上居中对齐
    align-items: center;
    // 给文本和图片添加间距，使用类选择器
    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  background-color: #313743;

  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #e9edf1;

}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  // 添加背景颜色
  background-color: #4A5064;
  // 设置文本大小
  font-size: 10px;
  // 设置文本行高
  line-height: 24px;
  // 设置文本颜色
  color: #fff;
  // 设置文本居中
  text-align: center;
  // 设置文本间距
  letter-spacing: 0.2em;
  // 设置鼠标悬浮变小手效果
  cursor: pointer;
}


.my-logo {
  width: 200px;
  height: 50px;
}
</style>
