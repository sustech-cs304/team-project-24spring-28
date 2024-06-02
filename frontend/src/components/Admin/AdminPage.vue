<template>
  <div class="table-container">
    <div class="button-container">
      <button @click="showTable(1)" :class="{ active: activeTable === 1 }">切换活动表格</button>
      <button @click="showTable(2)" :class="{ active: activeTable === 2 }">切换帖子表格</button>
      <button @click="showTable(3)" :class="{ active: activeTable === 3 }">切换用户表格</button>
    </div>

    <div v-show="activeTable === 1">
      <el-table :data="activityData" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="title" label="标题/活动" width="180"></el-table-column>
        <el-table-column prop="author" label="作者/用户" width="180"></el-table-column>
      </el-table>
    </div>
    <div v-show="activeTable === 2">
      <el-table :data="postData" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="title" label="标题/帖子" width="180"></el-table-column>
        <el-table-column prop="author" label="作者/用户" width="180"></el-table-column>
      </el-table>
    </div>
    <div v-show="activeTable === 3">
      <el-table :data="userData" style="width: 100%">
        <el-table-column prop="id" label="id" width="180"></el-table-column>
        <el-table-column prop="name" label="名字/用户" width="180"></el-table-column>
        <el-table-column prop="permission" label="权限" width="180"></el-table-column>
        <el-table-column prop="password" label="密码" width="180"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import * as adminApi from '@/components/Admin/admin.js';

const activeTable = ref(1);
const eventIds = ref([]);
const eventList = ref([]);
const postIds = ref([]);
const postList = ref([]);
const userList = ref([]);


const activityData = [
  { date: '2021-10-01', title: '活动1', author: '用户1' },
  { date: '2021-10-02', title: '活动2', author: '用户2' },
  { date: '2021-10-03', title: '活动3', author: '用户3' },
];

const postData = [
  { date: '2021-11-01', title: '帖子1', author: '用户4' },
  { date: '2021-11-02', title: '帖子2', author: '用户5' },
  { date: '2021-11-03', title: '帖子3', author: '用户6' },
];

const userData = [
  { date: '2021-12-01', name: '用户1', permission: '权限1', password: '密码1' },
  { date: '2021-12-02', name: '用户2', permission: '权限2', password: '密码2' },
  { date: '2021-12-03', name: '用户3', permission: '权限3', password: '密码3' },
];

onMounted(() => {
  fetchData();
});

async function fetchData() {
  eventIds.value = await adminApi.getAllEvents();
  postIds.value = await adminApi.getAllPosts();
  userList.value = await adminApi.getUserList();
}

function showTable(index) {
  activeTable.value = index;
}
</script>

<style scoped>
.table-container {
  position: relative;
}

.button-container {
  position: fixed;
  top: 20px;
  left: 20px;
}

.button-container button {
  margin-right: 10px;
}

.el-table {
  margin-top: 50px; /* 调整距离根据按钮高度 */
}

.active {
  font-weight: bold;
}
</style>
