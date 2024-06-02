<template>
  <div class="table-container">
    <div class="button-container">
      <button @click="showTable(1)" :class="{ active: activeTable === 1 }">切换活动表格</button>
      <button @click="showTable(2)" :class="{ active: activeTable === 2 }">切换帖子表格</button>
      <button @click="showTable(3)" :class="{ active: activeTable === 3 }">切换用户表格</button>
    </div>

    <div v-show="activeTable === 1">
      <el-table :data="events" style="width: 100%">
        <el-table-column prop="eventId" label="id" width="180"></el-table-column>
        <el-table-column prop="eventName" label="标题/活动" width="280"></el-table-column>
        <el-table-column prop="authorName" label="作者/用户" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button @click="deleteEvent(scope.row.eventId)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-show="activeTable === 2">
      <el-table :data="posts" style="width: 100%">
        <el-table-column prop="postID" label="id" width="180"></el-table-column>
        <el-table-column prop="postTitle" label="标题/帖子" width="380"></el-table-column>
        <el-table-column prop="username" label="作者/用户" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button @click="deletePost(scope.row.postID)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-show="activeTable === 3">
      <el-table :data="userData" style="width: 100%">
        <el-table-column prop="postID" label="id" width="180"></el-table-column>
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
const events = ref([]);
const posts = ref([]);
const users = ref([]);



const userData = [
  { date: '2021-12-01', name: '用户1', permission: '权限1', password: '密码1' },
  { date: '2021-12-02', name: '用户2', permission: '权限2', password: '密码2' },
  { date: '2021-12-03', name: '用户3', permission: '权限3', password: '密码3' },
];

onMounted(() => {
  fetchData();
});

async function fetchData() {
  events.value = [];
  posts.value = [];
  eventIds.value = [];
  eventIds.value = await adminApi.getAllEvents();
  posts.value = await adminApi.getAllPosts();
  for (const id of eventIds.value) {
    events.value.push(await adminApi.getBriefEvent(id));
  }
  users.value = await adminApi.getAllUser();
  // console.log(events);
  // console.log(posts);
  // console.log(eventIds);
  console.log(users)
}

async function deleteEvent(eventId) {
  await adminApi.deleteEvent(eventId);
  await fetchData();
}

async function deletePost(postId) {
  await adminApi.deletePost(postId);
  await fetchData();
  // console.log(postId);
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
