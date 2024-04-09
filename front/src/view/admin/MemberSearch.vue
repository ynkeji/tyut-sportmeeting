<template>
  <div>
    <!--    本页面来源-->
    <!--    //https://blog.csdn.net/weixin_39237340/article/details/122180353-->
    <el-card style="height: 100vh">

      <!--学号查询框-->
      <el-form inline v-model="findStudentById">
        <el-form-item >
          <el-input v-model="findStudentById" style="width: 200px" size="small" placeholder="请输入学号" clearable @clear="handleclear"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="findStu(findStudentById)">查询用户</el-button>
          <el-button type="primary" @click="add_user()">添加用户</el-button>
        </el-form-item>

      </el-form>

      <!-- 用户列表区域  -->
      <el-table :data="tableData" style="width: 100%" max-height="600">
        <el-table-column prop="id" label="选手学号" width="180"/>
        <el-table-column prop="name" label="选手姓名" width="180"/>
        <el-table-column prop="gender" label="性别" width="180"/>
        <el-table-column prop="college" label="学院" width="180"/>
        <el-table-column prop="phone" label="电话" width="180"/>
        <el-table-column prop="email" label="邮箱" width="180"/>
        <el-table-column fixed="right" label="操作" width="180">
          <template v-slot="scope">
            <!--修改user信息-->
            <el-tooltip effect="light" content="修改信息" placement="top">
              <el-button
                  link
                  type="primary"
                  size="default"
                  @click="user_set(scope.row)"
              >
                <el-icon>
                  <Edit/>
                </el-icon>
              </el-button>
            </el-tooltip>
            <!--删除user信息按钮-->
            <el-tooltip effect="light" content="删除成员" placement="top">
              <el-button
                  link
                  type="primary"
                  size="default"
                  @click="user_delete(scope.row)"
              >
                <el-icon>
                  <Delete/>
                </el-icon>
              </el-button>
            </el-tooltip>

            <el-tooltip effect="light" content="报名项目" placement="top">
              <el-button
                  link
                  type="primary"
                  size="default"
                  @click="openSign(scope.row)"
              >
                <el-icon>
                  <Promotion/>
                </el-icon>
              </el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加用户弹出窗口-->
    <el-dialog v-model="dialogFormVisible" title="添加运动员" width="500px">
      <el-form :model="form"
               :label-position="'right'">
        <el-form-item label="*选手学号">
          <el-input v-model="form.id"/>
        </el-form-item>
        <el-form-item label="*选手姓名">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="*选手性别">
          <el-radio-group v-model="form.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="*选手学院">
          <el-input v-model="form.college"/>
        </el-form-item>

        <el-form-item label="*选手电话">
          <el-input v-model="form.phone"/>
        </el-form-item>

        <el-form-item label="*选手邮箱">
          <el-input v-model="form.email"/>
        </el-form-item>

        <el-form-item label="*选手密码">
          <el-input v-model="form.password"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭窗口</el-button>
        <el-button type="primary" @click="check(form)">添加用户</el-button>
      </span>
      </template>
    </el-dialog>


    <!--修改用户弹出窗口-->
    <el-dialog v-model="setUserInfo" title="修改运动员弹出信息" width="500px">
      <el-form :model="form_set"
               :label-position="'right'">
        <el-form-item label="*选手学号">
          <el-input v-model="form_set.id"/>
        </el-form-item>
        <el-form-item label="*选手姓名">
          <el-input v-model="form_set.name"/>
        </el-form-item>
        <el-form-item label="*选手性别">
          <el-radio-group v-model="form_set.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="*选手学院">
          <el-input v-model="form_set.college"/>
        </el-form-item>

        <el-form-item label="*选手电话">
          <el-input v-model="form_set.phone"/>
        </el-form-item>

        <el-form-item label="*选手邮箱">
          <el-input v-model="form_set.email"/>
        </el-form-item>

        <el-form-item label="*选手密码">
          <el-input v-model="form_set.password"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="setUserInfo = false">关闭窗口</el-button>
        <el-button type="primary" @click="check(form_set)">修改信息</el-button>
      </span>
      </template>
    </el-dialog>

    <el-dialog v-model="signFormdia" title="报名系统" width="500px">
      <el-form :model="signForm"
               :label-position="'right'">
        <el-form-item label="*选手学号">
          <el-input v-model="signForm.sporterId" disabled/>
        </el-form-item>
        <el-form-item label="*选手姓名">
          <el-input v-model="signForm.sporterName" disabled/>
        </el-form-item>

        <el-form-item label="项目选择">
          <el-select v-model="signForm.projectName" clearable>
            <el-option
                v-for="item in signOption"
                :key="item.name"
                :value="item.name"
                :label="item.name">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="signFormdia = false">关闭窗口</el-button>
        <el-button type="primary" @click="sign(signForm)">确认报名</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>


<script>

import {ElMessage} from "element-plus";
import router from "@/router";
import api from "@/request/request";
import {getCurrentInstance, onMounted} from "vue";

export default {

  data() {
    return {


      findStudentById:'',

      //添加用户表单
      form: {
        name: '',
        college: '',
        email: '',
        id: '',
        phone: '',
        gender: '',
        password: ''
      },
      signForm: {
        sporterId: '',
        sporterName: '',
        projectName: ''
      },
      //修改用户表单
      form_set: {
        id: '',
        name: '',
        college: '',
        phone: '',
        email: '',
        gender: '',
        password: ''
      },
      //添加用户窗口展示
      signFormdia: false,
      dialogFormVisible: false,
      setUserInfo: false,
      //列表项
      tableData: [
        {
          name: '',
          college: '',
          email: '',
          id: '',
          phone: '',
          gender: '',
        }
      ],
      signOption: [{
        name: "",
        id: ''
      },],

      optionsByName: '',

      optionsByType: '',


    }
  },
  methods: {
    //添加用户事件
    add_user() {
      this.dialogFormVisible = true
    },


    //user信息修改
    user_set($row) {
      this.setUserInfo = true;
      this.form_set = {
        id: $row.id,
        name: $row.name,
        college: $row.college,
        phone: $row.phone,
        email: $row.email,
        password: $row.password,
        gender: $row.gender
      }
    },

    //user删除方法
    user_delete($row) {
      //delsporter
      api.get("/delsporter?id=" + $row.id, {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        if (res.data.status === -2) {
          ElMessage.error('登录信息出错，请重新登录！')
          router.push("/login")
          return;
        }
        if (res.data.status === -1) {
          ElMessage.error("您填写的信息有误，请核对后再输入")
          return;
        }
        if (res.data.status === 1) {
          ElMessage.success("删除成功！")
          this.selectAll();
          return;
        }
      })

      console.log($row.id)
    },

    //表单验证
    validateForm($form) {
      console.log($form)
      for (const key in $form) {
        if (!$form[key]) {
          ElMessage.error("您填写的表格内容不全")
          return false;
        }
      }

      // Check email format
      const emailPattern = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+(com|cn|net|org)$/;
      if (!emailPattern.test(this.form.email)) {
        ElMessage.error("您填写的邮件不符合要求")
        return false;
      }

      // Check phone format
      const phonePattern = /^1[3-9]\d{9}$/;
      if (!phonePattern.test(this.form.phone)) {
        ElMessage.error("您填写的手机号不符合要求")
        return false;
      }
      return true;
    },

    //添加user信息
    check($user) {
      if (!this.validateForm($user)) return;
      //验证成功，进行后端交互
      const jsonForm = JSON.stringify($user);
      api.post('/addSporter', jsonForm, {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        if (res.data.status === -2) {
          ElMessage.error('登录信息出错，请重新登录！')
          router.push("/login")
          return;
        } else if (res.data.status === 1) {
          ElMessage.success("添加成功");
          this.tableData = [];
          this.selectAll();
          return;
        } else if (res.data.status === -1) {
          ElMessage.error("您填写的信息有误，请核对后再输入")
          return;
        } else if (res.data.status === 0) {
          ElMessage.error(res.data.data);
          return;
        }
      })
    },

    selectAll() {
      api.get("/getAllSporter", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson = res.data.data;
        this.tableData = dataFromJson.map((item) => {
          return {
            id: item.id.toString(),
            name: item.name,
            college: item.college,
            phone: item.phone,
            email: item.email,
            gender: item.gender
          }
        });

      })
    },

    openSign($row) {
      this.signFormdia = true

      api.get("/GetProjectNameAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {

        if (res.data.status === -2){
          ElMessage.error("登录失效，请重新登录")
        }

        let dataFromJson2 = res.data.data;
        this.signForm.sporterId = $row.id
        this.signForm.sporterName = $row.name
        try {
          this.signOption = dataFromJson2.map((item) => {
            return {
              id: item.id,
              name: item.name
            }
          })
        } catch (res) {
          return;
        }

      })
    },
    sign($form) {
      const jsonForm = JSON.stringify($form);
      api.post("/addSign",jsonForm,{
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
          ElMessage.success("报名成功！")
          return;
        }else if (res.data.status === -1){
          ElMessage.error("重复报名，请重试")
          return;
        }
      })
    },
    //根据学号查询学生
    findStu($id){
      if ($id === "") {ElMessage.error("您还没有输入id");return;}
      api.get("/SelectById?id=" + $id,{
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res)=>{
        if (res.data.status === -2){
          ElMessage.error("登录失效，请重新登录")
          router.push("/login")
          return;
        }if (res.data.status === -1){
          ElMessage.success("未找到该学生，请核对后再提交");
        }
        if(res.data.status === 1){
          this.tableData = [res.data.data];
        }
      })
    },

    handleclear(){
      api.get("/getAllSporter", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson = res.data.data;
        this.tableData = dataFromJson.map((item) => {
          return {
            id: item.id.toString(),
            name: item.name,
            college: item.college,
            phone: item.phone,
            email: item.email,
            gender: item.gender
          }
        });
      })
    }


  },
  setup() {
    const ins = getCurrentInstance();
    onMounted(() => {
      api.get("/getAllSporter", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson = res.data.data;
        ins.data.tableData = dataFromJson.map((item) => {
          return {
            id: item.id.toString(),
            name: item.name,
            college: item.college,
            phone: item.phone,
            email: item.email,
            gender: item.gender
          }
        });
      })


    })
  }


}

</script>

<style lang="less" scoped>

</style>
