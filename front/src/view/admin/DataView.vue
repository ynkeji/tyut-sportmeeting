<template>
  <el-card style="height: 100vh">

    <div class="table-container">
      <el-card>
        <el-form inline>
          <el-form-item label="奖牌榜">
            <el-select>
              <el-option value="男子一千"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">奖牌总榜</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData1" style="width: 500px">
          <el-table-column prop="date" label="日期" width="180px"></el-table-column>
          <el-table-column prop="name" label="姓名" width="180px"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
      </el-card>
      <el-card>

        <el-form inline>
          <el-form-item label="奖牌榜">
            <el-select>
              <el-option value="男子一千"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">记录总榜</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData2" style="width: 500px">
          <el-table-column prop="date" label="日期" width="180"></el-table-column>
          <el-table-column prop="name" label="姓名" width="180"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
      </el-card>
    </div>
  </el-card>
</template>


<script>
export default {
  data() {
    return {
      tableData1: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }],
      tableData2: [{
        date: '2016-05-01',
        name: '张小狮',
        address: '北京市朝阳区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        name: '张小狮',
        address: '北京市朝阳区金沙江路 1517 弄'
      }]
    }
  }
}
</script>

<style scoped>
.table-container {
  display: flex;
  justify-content: space-evenly;
}
</style>
