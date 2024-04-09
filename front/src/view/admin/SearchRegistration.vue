<template>
  <div>
    <!--    本页面来源-->
    <!--    //https://blog.csdn.net/weixin_39237340/article/details/122180353-->
    <el-card>
      <el-form>
        <!-- 搜索与添加区域 -->
        <el-select v-model="option.name" class="m-2" placeholder="项目名称" size="small" clearable>
          <el-option
              v-for="item in optionsByName"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
        <el-select v-model="option.type" class="m-2" placeholder="项目类型" size="small" clearable>
          <el-option
              v-for="item in optionsByType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
        <el-button type="primary" size="small" @click="search(option)">
          <el-icon>
            <Search/>
          </el-icon>
        </el-button>
      </el-form>
      <!-- 用户列表区域  -->
      <el-table :data="tableData" style="width: 100%" max-height="600">
        <el-table-column prop="id" label="选手学号" width="180"/>
        <el-table-column prop="name" label="选手姓名" width="180"/>
        <el-table-column prop="colloge" label="学院" width="180"/>
        <el-table-column prop="pro_id" label="项目编号" width="180"/>
        <el-table-column prop="pro_name" label="项目名称" width="180"/>
        <el-table-column prop="pro_type" label="项目类型" width="180"/>
        <el-table-column fixed="right" label="Operations" width="180">
          <template v-slot="scope">
            <el-button
                link
                type="primary"
                size="default"
                @click="open_dialog(scope.row)"
            >
              录入成绩
            </el-button>

            <el-button
                link
                type="primary"
                size="default"
                @click="del(scope.row)"
            >
              退赛
            </el-button>
          </template>


        </el-table-column>
      </el-table>
    </el-card>


    <el-dialog v-model="dialogFormVisible" title="录入成绩" width="500px">
      <el-form :model="form"
               :label-position="'right'">
        <el-form-item label="*选手学号">
          <el-input v-model="form.stu_id" disabled/>
        </el-form-item>
        <el-form-item label="*项目编号">
          <el-input v-model="form.pro_id" disabled/>
        </el-form-item>
        <el-form-item label="*成绩">
          <el-input v-model="form.stu_grade"/>
        </el-form-item>
        <el-form-item label="*得分">
          <el-select v-model="form.pro_grade">
            <el-option value="7"/>
            <el-option value="5"/>
            <el-option value="3"/>
            <el-option value="2"/>
            <el-option value="1"/>
          </el-select>
        </el-form-item>

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭窗口</el-button>
        <el-button type="primary" @click="setGrade(form)">成绩录入</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>


<script>

import {getCurrentInstance, onMounted} from "vue";
import api from "@/request/request";
import {ElMessage} from "element-plus";
import router from "@/router";

export default {

  data() {
    return {
      option:{
        name:'',
        type:''
      },
      form: {
        stu_id: '',
        region: '',
        pro_id: '',
        stu_grade: '',
        pro_grade: ''
      },
      dialogFormVisible: false,
      tableData: [
        {}
      ],
      // 获取用户列表的参数对象


      //项目列表
      optionsByName: '',

      optionsByType: '',

    }
  },
  methods: {
    search($option){
      var name = "name="+ $option.name
      var type = "type="+ $option.type
      api.get("/selectByparam?" + name + "&" + type,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }})
          .then((res)=>
          {
            if (res.data.status === -2) {
              ElMessage.error("登录失效，请重新登录")
              router.push("/login")
              return;
            }
            if (res.data.status === 1){
              try {
                let dataFromJson = res.data.data;
                this.tableData = dataFromJson.map((item) => {
                  return {
                    id: item.sporterId,
                    name: item.sporterName,
                    colloge: item.colloge,
                    pro_id: item.projectId,
                    pro_name: item.projectName,
                    pro_type:item.projecttype
                  }
                })
              }catch (res){
                console.log(res)
              }
            }
          })



    },


    //open dialog
    open_dialog($row) {
      console.log($row)
      this.dialogFormVisible = true;
      this.form = {
        stu_id: $row.id,
        pro_id: $row.pro_id,
      };
    },

    del($row){
      var param = $row.id + "&ppid=" + $row.pro_id
      api.get("/del?uuid=" + param, {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res)=>{
        if (res.data.status === -2){
          ElMessage.error("登录失效，请重新登录")
          router.push("/login")
          return;
        }else if (res.data.status === 1){
          ElMessage.success("退赛成功！")

          api.get("/getSignAll", {
            headers: {
              "Content-Type": "application/json",
              "token": window.localStorage.getItem("token")
            }
          }).then((res) => {
            let dataFromJson2 = res.data.data;
            this.tableData = dataFromJson2.map((item) => {
              return {
                id: item.sporterId,
                name: item.sporterName,
                colloge: item.colloge,
                pro_id: item.projectId,
                pro_name: item.projectName,
                pro_type:item.projecttype
              }
            })
          })

          return;
        }else if (res.data.status === -1){
          ElMessage.error("参数错误，请检查后再提交")
          return;
        }
      })
    },

    setGrade($form){
      const jsonForm = JSON.stringify($form);
      api.post('/setGrade', jsonForm, {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res)=>{
        if (res.data.status === -2) {
          ElMessage.error("登录失效，请重新登录")
          router.push("/login")
          return;
        }
        if (res.data.status === 1) {
          ElMessage.success("录入成功！")
          return;
        }
      })

    }
  },

  setup() {

    const ins = getCurrentInstance();
    onMounted(() => {
      api.get("/GetProjectNameAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        try {
          let dataFromJson2 = res.data.data;
          ins.data.optionsByName = dataFromJson2.map((item) => {
            return {
              value: item.name,
              lable: item.name
            }
          })
        } catch (res) {
          console.log(res)
        }
      })

      api.get("/GetProjectTypeAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson2 = res.data.data;
        ins.data.optionsByType = dataFromJson2.map((item) => {
          return {
            value: item.name,
            lable: item.name
          }
        })
      })

      api.get("/getSignAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson2 = res.data.data;
        ins.data.tableData = dataFromJson2.map((item) => {
          return {
            id: item.sporterId,
            name: item.sporterName,
            colloge: item.colloge,
            pro_id: item.projectId,
            pro_name: item.projectName,
            pro_type:item.projecttype
          }
        })
      })

    })
  }
}

</script>

<style lang="less" scoped>

</style>
