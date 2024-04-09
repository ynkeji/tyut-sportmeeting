<template>
  <el-card>
    <!-- 赛事名称查询框-->

    <!-- 用户列表区域  -->
    <el-table :data="tableData" style="width: 100%" max-height="250" border>
      <el-table-column label="名次" width="120" type="index"/>
      <el-table-column prop="id" label="选手学号" width="180"/>
      <el-table-column prop="name" label="选手姓名" width="180"/>
      <el-table-column prop="pro_name" label="项目名称" width="180"/>
      <el-table-column prop="pro_id" label="项目编号" width="180"/>
      <el-table-column prop="pro_type" label="项目类型" width="180"/>
      <el-table-column prop="pro_score" label="项目成绩" width="180"/>
      <el-table-column prop="pro_SumScore" label="项目得分" width="180" :sortable="true"/>
      <el-table-column prop="stu_institute" label="学院" width="240"/>
    </el-table>
  </el-card>


  <el-dialog v-model="setGameInfo" title="修改成绩" width="500px">
    <el-form :model="form_set"
             :label-position="'right'">
      <el-form-item label="选手学号">
        <el-input :disabled="true" v-model="form_set.stu_id" :value=this.id></el-input>
      </el-form-item>
      <el-form-item label="项目编号">
        <el-input :disabled="true" v-model="form_set.pro_id" :value=this.pro_id></el-input>
      </el-form-item>

      <el-form-item label="选手成绩">
        <el-input  v-model="form_set.score" :value=this.pro_score></el-input>
      </el-form-item>

      <el-form-item label="*项目得分">
        <el-select v-model="form_set.pro_score">
          <el-option
          v-for="item in score"
          :value="item.value"
          :key="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="setGameInfo = false">关闭窗口</el-button>
        <el-button type="primary" @click="setNewGame(form_set)">修改比赛</el-button>
      </span>
    </template>
  </el-dialog>



</template>
<script>

import {getCurrentInstance, onMounted} from "vue";
import api from "@/request/request";

export default {
  data() {
    return {
      find_stu:{
        name:'',
        id:""
      },

      id:'',
      pro_id:'',
      pro_score:'',
      //修改成绩对话框显示
      setGameInfo:false,
      tableData:[],

      //项目列表
      options: [],
      //修改赛事表单
      form_set: {
        stu_id:'',
        pro_id:'',
        score:'',
        pro_score:''
      },
    }
  },


  methods: {
    find_game($score_find) {
      console.log($score_find)
    },
    toExcel(){
      console.log("导出成功")
    },

  //
  // findByName($form){
  //     api.get("score?name=" + $form.name + "&id=" + $form.id).then((res)=>{
  //
  //     })
  // },
    score_set($row){
      this.setGameInfo = true
      this.pro_id = $row.pro_id
      this.id = $row.score_stu_id
      this.pro_score = $row.pro_score
      console.log($row)
    },

    setNewGame($form){
      console.log($form)
    },


  },
  setup() {

    const ins = getCurrentInstance();
    onMounted(() => {
      api.get("/score", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        try {
          let dataFromJson = res.data.data;
          ins.data.tableData = dataFromJson.map((item) => {
            return {
              id:item.sporterId,
              name:item.sporterName,
              pro_name:item.projectName,
              pro_id:item.projectId,
              pro_type:item.projecttype,
              pro_score:item.grade,
              pro_SumScore: item.score,
              stu_institute:item.colloge

            }
          })
        } catch (res) {
          console.log(res)
        }

      });
    })
  }
}

</script>
