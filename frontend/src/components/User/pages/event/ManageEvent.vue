<script setup>
import { ref, reactive } from 'vue'
import {useRouter} from "vue-router";
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";

const router = useRouter()

let eventList = ref([
  {
    id: 1,
    name: '活动1',
    type: '计数式',
    applyStartTime: '2024-04-01 00:00:00',
    applyEndTime: '2024-04-10 00:00:00',
    applyCnt: 100,
    status: '报名中',
  },
  {
    id: 2,
    name: '活动2',
    type: '选座式',
    applyStartTime: '2024-04-01 00:00:00',
    applyEndTime: '2024-04-10 00:00:00',
    applyCnt: 100,
    status: '未开始',
  },
  {
    id: 3,
    name: '活动3',
    type: '自定义报名',
    applyStartTime: '2024-04-01 00:00:00',
    applyEndTime: '2024-04-10 00:00:00',
    applyCnt: 100,
    status: '已结束',
  },
])


function createNewEvent() {
  router.push({path: '/event/create'})
}
function exportApplication(index) {
  // print the name
  console.log(eventList.value[index].name)
}

</script>

<template>
  <div>
    <HeaderForAll></HeaderForAll>
  </div>

  <div style="margin-left: 20px; margin-right: 20px; margin-top: 20px;"
  >
    <div style="margin-bottom: 30px; margin-top: 20px">
      <el-button @click="createNewEvent">创建新活动</el-button>
    </div>
    <div>
      <el-table :data="eventList" style="width: 100%">
        <el-table-column prop="name" label="活动名称"></el-table-column>
        <el-table-column prop="type" label="活动类型"></el-table-column>
        <el-table-column prop="applyStartTime" label="报名开始时间"></el-table-column>
        <el-table-column prop="applyEndTime" label="报名结束时间"></el-table-column>
        <el-table-column prop="applyCnt" label="报名人数"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template #default="row">
            <el-button @click="exportApplication(row.$index)">导出报名表</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>

</template>

<style scoped>

</style>