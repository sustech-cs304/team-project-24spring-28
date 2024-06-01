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
          @input="handleSearch"
      >
        <template #prepend>搜索</template>
      </el-input>
    </div>

    <!-- Search Results -->
    <div v-if="searchResults.length > 0">
      <postCard
          v-for="post in searchResults"
          :key="post.id"
          :postData="post"
      />
    </div>
    <div v-else>
      <p>没有找到相关内容</p>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
import postCard from "@/components/User/pages/post/components/postsSquare/postCard.vue";
import * as searchApi from '@/components/User/pages/main/searchApi.js';


const searchInput = ref('');
const eventResults = ref([]);
const postResults = ref([]);

const clearSearch = () => {
  console.log('Search input cleared!');
  searchInput.value = '';
  eventResults.value = [];
  postResults.value = [];
};

const handleSearch = async () => {
  if (searchInput.value.trim() === '') {
    eventResults.value = [];
    postResults.value = [];
    return;
  }

  // Call search API to get event IDs and post IDs
  const eventIds = await searchApi.searchEvent(searchInput.value);
  const postIds = await searchApi.searchPost(searchInput.value);

  // Fetch detailed post information for each event ID and post ID
  const posts = [];
  for (let id of [...postIds]) {
    const post = await searchApi.getPost(id);
    posts.push(post);
  }
  const events = [];
  for (let id of [...eventIds]) {
    const event = await searchApi.getBriefEvent(id);
    events.push(event);
  }

  eventResults.value = events;
  postResults.value = posts;
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
