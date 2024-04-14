<template>
  <div class="container">
    <!-- Header -->
    <HeaderForAll>
    </HeaderForAll>

    <!-- Search Bar -->
    <div class="search-bar">
      <el-input
          v-model="searchInput"
          placeholder="搜索你感兴趣的活动"
          clearable
          @clear="clearSearch"
      >
        <template #prepend>搜索</template>
      </el-input>
    </div>

    <div>
      <postCard></postCard>
      <postCard></postCard>
      <postCard></postCard>
      <postCard></postCard>
      <postCard></postCard>
      <postCard></postCard>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
import postCard from "@/components/User/pages/post/components/postsGround/postCard.vue";


const searchInput = ref('');


const clearSearch = () => {
  console.log('Search input cleared!');
};
</script>

<style scoped>
/* Styles for Header, Search Bar, and Information Cards */
/* Customize according to your design */
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.search-bar {
  margin-top: 20px;
}
</style>
