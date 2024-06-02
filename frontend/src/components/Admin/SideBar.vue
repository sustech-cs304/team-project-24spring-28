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
          <span>活动帖子管理</span>
        </template>
        <el-menu-item index="admin?setting=Post">
          <span>帖子管理</span>
        </el-menu-item>
        <el-menu-item index="admin?setting=Event">
          <span>活动管理</span>
        </el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="1">
        <template #title>
          <el-icon>
            <setting/>
          </el-icon>
          <span>用户管理</span>
        </template>
        <el-menu-item-group title="用户信息">
          <el-menu-item index="admin?setting=user">用户信息</el-menu-item>
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
