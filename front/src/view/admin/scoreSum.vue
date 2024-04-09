<template>
  <el-card>


      <!--//根据学院查-->
    <el-form v-model="form_findByGroup" :inline="true">
      <el-form-item>
        <!--根据学院分类-->
        <el-select v-model="form_findByGroup.xueyuan" placeholder="学院分类">
          <el-option
              v-for="item in xueyuan"
              :key="item.where"
              :value="item.where"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-select v-model="form_findByGroup.sex" placeholder="性别分类">
          <el-option key="男" value="男"></el-option>
          <el-option key="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="SearchWithXueyuan(form_findByGroup)">
          <el-icon>
            <Search/>
          </el-icon>
        </el-button>
      </el-form-item>
    </el-form>

    <el-form v-model="form_FindByProType">
      <el-form-item>
        <el-select v-model="form_FindByProType.item">
          <el-option key="男子项目" value="男子项目"></el-option>
          <el-option key="女子项目" value="女子项目"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button>

        </el-button>
      </el-form-item>

    </el-form>



  </el-card>
</template>

<script>
export default {
  data() {
    return {
      xueyuan: [
        {
          where: '软件学院',
        },
        {
          where: "信计学院"
        }
      ],
      form_findByGroup: {
        xueyuan: '',
        sex: ''
      },
      form_FindByProType:{
        item:""
      }
    }
  },


  methods:{
    SearchWithXueyuan($form){
      console.log($form)
    }
  }


}
</script>

<style scoped>

</style>
