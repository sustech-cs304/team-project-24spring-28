<script setup>
import {ref} from "vue";
import {
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'

const isCollapsed = ref(true)


function toggleFold() {
  isCollapsed.value = !isCollapsed.value
}
</script>
<template>
  <el-aside>
    <el-menu router unique-opened :collapse="isCollapsed">
      <button class="collapse-button" @click="toggleFold">
        <el-icon>
          <icon-menu/>
        </el-icon>
      </button>
      <el-sub-menu index="0">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>学生管理</span>
        </template>
        <el-menu-item index="admin?setting=studentAcc">
          <span>学生账号管理</span>
        </el-menu-item>
        <el-menu-item index="admin?setting=studentTeam">
          <span>学生组队管理</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>宿舍管理</span>
        </template>
        <el-menu-item-group title="宿舍信息">
          <el-menu-item index="admin?setting=dormSelect">宿舍选择明细</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="楼栋信息">
          <el-menu-item index="admin?setting=building">楼栋信息</el-menu-item>
          <el-menu-item index="admin?setting=zoning">分区信息</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>公告管理</span>
        </template>
        <el-menu-item-group title="公告信息">
          <el-menu-item index="admin?setting=sendNotice">公告发布</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="选择时间">
          <el-menu-item index="admin?setting=selectTime">选择时间管理</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </el-menu>
  </el-aside>

</template>

<style scoped>
.collapse-button {
  width: 100%;
  height: 60px;
  background-color: #304156;
  border: none;
  outline: none;
}


.el-menu {
  --el-menu-bg-color: #304156;
  --el-menu-text-color: #bfcbd9;
  border-right: none;
  width: 200px;

  &.el-menu--collapse {
    width: 60px;
  }
}
</style>
