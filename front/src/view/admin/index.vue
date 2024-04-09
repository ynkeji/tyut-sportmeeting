<template>
  <el-text type="success" style="font-size: 40px;position: absolute;top: 20%;left: 40%;">欢迎进入运动会管理系统</el-text>
  <div class="Home">


    <el-carousel :interval="4000" type="card" height="300px" style="top: 200px">
      <el-carousel-item v-for="item in carouseData" :key="item">
        <img :src="item.url" alt="" height="400px"/>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "home",
  components: {},
  data() {
    return {
      //定义跑马灯的图片路径
      carouseData: [
        { url: require("../../assets/img1.jpg") },
        { url: require("../../assets/img5.jpg") },
        { url: require("../../assets/img2.jpg") },
        { url: require("../../assets/img3.jpg") },
        { url: require("../../assets/img4.jpg") },
      ],
    };
  },
};
</script>

<style>
.Home {
  margin-top: 20px;
}
</style>
