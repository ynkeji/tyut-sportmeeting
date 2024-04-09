<template>
  <div>
    <!--    本页面来源-->
    <!--    //https://blog.csdn.net/weixin_39237340/article/details/122180353-->
    <el-card>
      <!-- 赛事查询框表单-->
      <el-form :model="game_find">
        <!--比赛名称查询-->
        <el-select  clearable v-model="game_find.gameName" class="m-2" placeholder="项目名称" size="small" id="game_name">
          <el-option
              v-for="item in optionsByName"
              :key="item.value"
              :label="item.value"
              :value="item.value"
          />
        </el-select>
        <!-- 赛事类型查询框-->
        <el-select  clearable v-model="game_find.gameType" class="m-2" placeholder="项目类型" size="small" id="game_type">
          <el-option
              v-for="item in optionsByType"
              :key="item.value"
              :label="item.value"
              :value="item.value"
          />
        </el-select>
        <el-button type="primary" @click="find_game(game_find)">查询比赛</el-button>
        <el-button type="primary" @click="openGameAdd()">添加比赛</el-button>
      </el-form>

      <!-- 用户列表区域  -->
      <el-table :data="tableData" style="width: 100%" max-height="700">
        <el-table-column prop="id" label="项目编号" width="120"/>
        <el-table-column prop="name" label="项目名称" width="180"/>
        <el-table-column prop="type" label="项目类型" width="180"/>
        <el-table-column prop="participants" label="参赛人数" width="180"/>
        <el-table-column prop="startTime" label="开始时间" width="240"/>
        <el-table-column prop="location" label="项目地点" width="180"/>
        <el-table-column prop="status" label="项目状态" width="180"/>
        <el-table-column prop="referee" label="项目裁判" width="180"/>
        <el-table-column fixed="right" label="操作" width="180">
          <template v-slot="scope">
            <!--修改user信息-->
            <el-tooltip effect="light" content="修改信息" placement="top">
              <el-button
                  link
                  type="primary"
                  size="default"
                  @click="openGameSet(scope.row)"
              >
                <el-icon>
                  <Edit/>
                </el-icon>
              </el-button>
            </el-tooltip>

            <!--            查询报名人数-->
            <el-tooltip effect="light" content="查询报名详情" placement="top">
              <el-button
                  link
                  type="primary"
                  size="default"
                  @click="OpenSingUpList(scope.row)"
              >
                <el-icon>
                  <Monitor/>
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

          </template>
        </el-table-column>
      </el-table>

    </el-card>


    <!--添加比赛弹出窗口-->
    <el-dialog v-model="addGame" title="添加比赛" width="500px">
      <el-form :model="form"
               :label-position="'right'">
        <el-form-item label="*项目名称">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="*项目类型">
          <el-select v-model="form.type">
            <el-option key="男子比赛" value="男子比赛">男子比赛</el-option>
            <el-option key="女子比赛" value="女子比赛">女子比赛</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="*参赛人数">
          <el-input v-model="form.participants"/>
        </el-form-item>

        <el-form-item label="*开始时间">
          <el-input v-model="form.startTime"/>
        </el-form-item>

        <el-form-item label="*项目地点">
          <el-input v-model="form.location"/>
        </el-form-item>

        <el-form-item label="*项目状态">
          <el-select v-model="form.status">
            <el-option key="已开始" value="已开始">已开始</el-option>
            <el-option key="未开始" value="未开始">未开始</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="*项目裁判">
          <el-select v-model="form.referee"  clearable>
            <el-option key="魏灿日" value="魏灿日">魏灿日</el-option>
            <el-option key="丁渝劫" value="丁渝劫">丁渝劫</el-option>
            <el-option key="窦来柳" value="窦来柳">窦来柳</el-option>
            <el-option key="段城渝" value="段城渝">段城渝</el-option>
            <el-option key="姜明泳" value="姜明泳">姜明泳</el-option>
            <el-option key="卓琰楷" value="卓琰楷">卓琰楷</el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="addGame = false">关闭窗口</el-button>
        <el-button type="primary" @click="setNewGame(form)">添加比赛</el-button>
      </span>
      </template>
    </el-dialog>

    <!--    查看报名人数-->

    <el-dialog v-model="SingUpList_Find" title="参赛队员查看与处理" width="800px">

      <el-form inline="true">

        <el-form-item>
          <el-select>
            <el-option value="打印检录表"></el-option>
            <el-option value="打印分组表"></el-option>
          </el-select>

          <el-button type="primary">
            <el-icon>
              <Printer/>
            </el-icon>
            打印
          </el-button>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="setByone">
            <el-icon>
              <Odometer/>
            </el-icon>
            一键编排
          </el-button>
        </el-form-item>
      </el-form>

      <el-table :data="SingUpList" height="400px">
        <el-table-column prop="ProId" label="项目编号"></el-table-column>
        <el-table-column prop="ProName" label="项目名称"></el-table-column>
        <el-table-column prop="StuName" label="学生姓名"></el-table-column>
        <el-table-column prop="StuId" label="学生学号"></el-table-column>
        <el-table-column prop="Group" label="项目分组"></el-table-column>
        <el-table-column prop="ProGroupId" label="小组分道"></el-table-column>
      </el-table>
    </el-dialog>

    <!--修改用户弹出窗口-->
    <el-dialog v-model="setGameInfo" title="修改赛事" width="500px">

      <el-form :model="form_set"
               :label-position="'right'">
        <el-form-item label="项目编号">
          <el-input :disabled="true" v-model="form_set.id" :value=this.id></el-input>
        </el-form-item>
        <el-form-item label="*项目名称">
          <el-input v-model="form_set.name"/>
        </el-form-item>
        <el-form-item label="*项目类型">
          <el-select v-model="form_set.type">
            <el-option key="男子比赛" value="男子比赛">男子比赛</el-option>
            <el-option key="女子比赛" value="女子比赛">女子比赛</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="*参赛人数">
          <el-input v-model="form_set.participants"/>
        </el-form-item>

        <el-form-item label="*开始时间">
          <el-input v-model="form_set.startTime"/>
        </el-form-item>

        <el-form-item label="*项目地点">
          <el-input v-model="form_set.location"/>
        </el-form-item>

        <el-form-item label="*项目状态">
          <el-select v-model="form_set.status">
            <el-option key="已开始" value="已开始">已开始</el-option>
            <el-option key="未开始" value="未开始">未开始</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="*项目裁判">
          <el-select v-model="form_set.referee">
            <el-option key="魏灿日" value="魏灿日">魏灿日</el-option>
            <el-option key="丁渝劫" value="丁渝劫">丁渝劫</el-option>
            <el-option key="窦来柳" value="窦来柳">窦来柳</el-option>
            <el-option key="段城渝" value="段城渝">段城渝</el-option>
            <el-option key="姜明泳" value="姜明泳">姜明泳</el-option>
            <el-option key="卓琰楷" value="卓琰楷">卓琰楷</el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="setGameInfo = false">关闭窗口</el-button>
        <el-button type="primary" @click="updataGame(form_set)">修改比赛</el-button>
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
      //添加用户窗口展示
      proID:'',
      SingUpList_Find: false,
      addGame: false,
      setGameInfo: false,

      id: '',
      game_find: {
        gameName: '',
        gameType: ''
      },
      //添加赛事表单
      form: {},
      //修改赛事表单
      form_set: {},
      //列表项
      tableData:[],

      //项目列表
      optionsByName: '',

      optionsByType: '',

      SingUpList: [{
        ProId: '',
        ProName: '',
        StuName: '',
        StuId: '',
        Group: '',
        ProGroupId: ''
      }]

    }
  },
  methods: {
  //一件编排！
    setByone(){
      api.get('/setByOne?ppid=' + this.proId,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }}).then((res)=>{
          if (res.data.status === -2){
            ElMessage.error('登录信息出错，请重新登录！')
            router.push("/login")
          }
          if (res.data.status === 1){
            api.get("/SignUpList?ppid=" + this.proId,{headers:{
                "Content-Type":"application/json",
                "token":window.localStorage.getItem("token")
              }}).then((res)=>{
              if (res.data.status === -2){
                ElMessage.error('登录信息出错，请重新登录！')
                router.push("/login")
              }
              if (res.data.status === 1){
                let dataFromJson = res.data.data;
                this.SingUpList = dataFromJson.map((item) => {
                  return {
                    ProId: item.projectId,
                    ProName: item.projectName,
                    StuName: item.sporterName,
                    StuId:item.sporterId,

                    ProGroupId: item.which,
                    Group: item.grou,
                  }
                })
                return;
              }
              if (res.data.status === -1){
                ElMessage.error("您输入的信息有误，请核对后再提交！")
              }
            })

            return;
          }
          if (res.data.status === -1){
            ElMessage.error("您输入的信息有误，请核对后再提交！")
          }
        })
    },


    //查询比赛的表单
    find_game(form) {
      var name = "name="
      var type = "type="
      if (form.gameName === "")
        name = "";
      else name = name + form.gameName
      if (form.gameType === "")
        type = ""
      else type = type + form.gameType
      api.get("/getByparam?" + name + "&" + type,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }})
          .then((res)=>{
            try {
              let dataFromJson = res.data.data;
              this.tableData = dataFromJson.map((item) => {
                return {
                  id: item.id,
                  name: item.name,
                  type: item.type,
                  participants: item.participants,
                  startTime: item.startTime,
                  location: item.location,
                  status: item.status,
                  referee: item.referee
                }
              })
            }catch (res){
              console.log(res)
            }
          })
    },

    //打开添加比赛的表单窗口
    openGameAdd() {
      this.addGame = true
    },

    //打开修改比赛   列表一行
    openGameSet($row) {
      this.setGameInfo = true
      this.id = $row.id
      this.form_set.id = $row.id
      this.form_set.participants = $row.participants
      this.form_set.name = $row.name
      this.form_set.location = $row.location
      this.form_set.type = $row.type
      this.form_set.startTime = $row.startTime
      this.form_set.status = $row.status
      this.form_set.referee = $row.referee
    },


    selectAll(){
      api.get("/getAllproject", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        let dataFromJson = res.data.data;
        this.tableData = dataFromJson.map((item) => {
          return {
            id: item.id,
            name: item.name,
            type: item.type,
            participants: item.participants,
            startTime: item.startTime,
            location: item.location,
            status: item.status,
            referee: item.referee
          }
        })
      })
    },

    //添加比赛
    setNewGame($form) {
      const jsonForm = JSON.stringify($form);
      api.post("/addProject",jsonForm,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }}).then((res)=>{
          if (res.data.status === -2){
            ElMessage.error('登录信息出错，请重新登录！')
            router.push("/login")
          }
          if (res.data.status === 1){
            ElMessage.success("添加成功")
            this.selectAll()
            return;
          }
          if (res.data.status === -1){
            ElMessage.error("您输入的信息有误，请核对后再提交！")
          }
      })
    },

    updataGame($form_set){
      const jsonForm = JSON.stringify($form_set);
      api.post("/updateProject",jsonForm,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }})
          .then((res)=>{
            if (res.data.status === -2){
              ElMessage.error('登录信息出错，请重新登录！')
              router.push("/login")
            }
            if (res.data.status === 1){
              ElMessage.success("修改成功")
              this.selectAll()
              return;
            }
            if (res.data.status === -1){
              ElMessage.error("您输入的信息有误，请核对后再提交！")
            }
      })
    },


    user_delete($row){
      api.get("/deleteProject?id=" + $row.id,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }})
          .then((res)=>{
            if (res.data.status === -2){
              ElMessage.error('登录信息出错，请重新登录！')
              router.push("/login")
            }
            if (res.data.status === 1){
              ElMessage.success("删除成功")
              this.selectAll()
              return;
            }
            if (res.data.status === -1){
              ElMessage.error("您输入的信息有误，请核对后再提交！")
            }
          })
    },

    //打开已报名列表
    OpenSingUpList($row) {
      this.proId = $row.id
      this.SingUpList_Find = true
      api.get("/SignUpList?ppid=" + $row.id,{headers:{
          "Content-Type":"application/json",
          "token":window.localStorage.getItem("token")
        }}).then((res)=>{
        if (res.data.status === -2){
          ElMessage.error('登录信息出错，请重新登录！')
          router.push("/login")
        }
        if (res.data.status === 1){
          let dataFromJson = res.data.data;
          this.SingUpList = dataFromJson.map((item) => {
            return {
              ProId: item.projectId,
              ProName: item.projectName,
              StuName: item.sporterName,
              StuId:item.sporterId,
              Group: item.grou,
              ProGroupId: item.which
            }
          })
          return;
        }
        if (res.data.status === -1){
          ElMessage.error("您输入的信息有误，请核对后再提交！")
        }
      })
    },

  },


  setup() {
    const ins = getCurrentInstance();
    onMounted(() => {
      api.get("/getAllproject", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res) => {
        try {
          let dataFromJson = res.data.data;
          ins.data.tableData = dataFromJson.map((item) => {
            return {
              id: item.id,
              name: item.name,
              type: item.type,
              participants: item.participants,
              startTime: item.startTime,
              location: item.location,
              status: item.status,
              referee: item.referee
            }
          })
        }catch (res){
          console.log(res)
        }

      });

      api.get("/GetProjectNameAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res)=>{
        try
        {
          let dataFromJson2 = res.data.data;
          ins.data.optionsByName = dataFromJson2.map((item)=>{
            return{
              value : item.name,
              lable : item.name
            }
          })
        }catch (res){
          console.log(res)
        }
      })

      api.get("/GetProjectTypeAll", {
        headers: {
          "Content-Type": "application/json",
          "token": window.localStorage.getItem("token")
        }
      }).then((res)=>{
        let dataFromJson2 = res.data.data;
        ins.data.optionsByType = dataFromJson2.map((item)=>{
          return{
            value : item.name,
            lable : item.name
          }
        })
      })

    });
  }
}

</script>

