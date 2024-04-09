<template>
  <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      @open="handleOpen"
      @close="handleClose"
  >
    <el-menu-item @click="setroute('/admin-index')" index="1">
      <el-icon>
        <HomeFilled/>
      </el-icon>
      <template #title><span>首页</span></template>
    </el-menu-item>

    <el-menu-item @click="setroute('/admin-search')" index="2">
      <el-icon><DataAnalysis /></el-icon>
      <template #title><span>报名查询</span></template>
    </el-menu-item>


    <el-sub-menu index="3">
      <template #title>
        <el-icon><Platform /></el-icon>
        <span>信息汇总</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="3-1" @click="setroute('/admin-MemberSearch')"><el-icon><Pointer /></el-icon>队员查询</el-menu-item>
        <el-menu-item index="3-2" @click="setroute('/admin-GameSet')"><el-icon><Pointer /></el-icon>赛事查询</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>

    <el-sub-menu index="4">
      <template #title>
        <el-icon><BellFilled /></el-icon>
        <span>成绩查询</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="4-1" @click="setroute('/admin-ScoreFindeByOne')"><el-icon><Pointer /></el-icon>单项查询</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>

  </el-menu>

  <el-main>
    <router-view></router-view>
  </el-main>

</template>

<script setup>

//设置跳转路由
import { useRouter } from 'vue-router'
const route = useRouter();
function setroute($rout)
{

  route.push($rout);
}

</script>
