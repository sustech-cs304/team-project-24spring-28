<script setup>
  import {useRoute, useRouter} from "vue-router";
  import {reactive, ref, getCurrentInstance, computed, onMounted} from "vue";
  import Avatar from "@/components/Student/Avatar.vue";
  import {globalStore} from "@/main";
  import axiosInstance from "@/utils/axios";
  const router = useRouter()
  const proxy = getCurrentInstance().proxy;


  const showDot = ref(false)

  function toSearch() {
    router.push({path: '/findroom'})
  }

  function toHome() {
    router.push({path: '/student'})
  }

  function toMessage() {
    globalStore.notification = false
    router.push({path: '/message'})
  }

  function send() {
    proxy.$socket.send('hello world');
  }

  const user = ref(localStorage.getItem('userId'))


  onMounted(() => {
    axiosInstance.get('/message/hasAnyUnread')
        .then(response => {
          showDot.value = response.data.data
        })
        .catch(error => {
          console.error(error);
        });
  })
</script>

<template>
  <div class="header-bar">
<!--    <button @click="send">send</button>-->
    <p class="left-top-title">Dormitory Selection System</p>

    <div class="center-search-container">
<!--      <button class="center-search-button" @click="toSearch">-->
<!--        Search Room-->
<!--      </button>-->
      <div class="InputContainer" @click="toSearch">
        <svg viewBox="0 0 24 24" width="24" height="24" class="icon">
          <image href="@/assets/svgs/search.svg" width="24" height="24"></image>
        </svg>
        <p>&nbsp;</p>
        <p style="font-size: 15px; font-weight: bold; color: #ffa6b5;">Search Room</p>
      </div>
    </div>

    <div class="right-group">
      <div class="header-to-message-wrap">
        <svg viewBox="0 0 24 24" width="24" height="24" class="icon" @click="toMessage">
          <image href="@/assets/svgs/message.svg" width="24" height="24"></image>
        </svg>
        <i :class="showDot ? 'dot_show':'dot_unshow'"></i>
      </div>

      <div class="header-to-home-wrap">
        <svg viewBox="0 0 24 24" width="24" height="24" class="icon" @click="toHome">
          <image href="@/assets/svgs/home.svg" width="24" height="24"></image>
        </svg>
      </div>

      <div class="header-avatar-wrap">
        <Avatar :userId="user"/>
      </div>
    </div>

  </div>
</template>

<style scoped>
ul {
  list-style-type: none;
  justify-content: space-between;
}

.left-top-title {
  font-size: 20px;
  font-weight: bold;
  color: #ffffff;
  cursor: default;
  width: 282px;
}

.header-bar {
  top: 0;
  z-index: 1002;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0 24px;
  right: 0;
  height: 64px;
  box-sizing: border-box;
  background: linear-gradient(pink, #ffa6b5);
  box-shadow: 0 5px 10px 0 #EAEAEA;
  border-radius: 10px 10px 0 0;
  margin-left: 10px;
  margin-right: 10px;
  margin-top: 10px;
  justify-content: space-between;
}

.center-search-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  align-self: center;
  width: 250px;
}

.InputContainer {
  width: 240px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, rgb(255, 215, 215), rgb(255, 224, 240));
  border-radius: 18px;
  overflow: hidden;
  cursor: pointer;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.075);
}

.InputContainer:hover {
  box-shadow: 5px 5px 10px rgb(255, 209, 209);
}


.right-group {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  width: 282px;
}

.header-to-message-wrap {
  display: flex;
  flex-direction: row;
  margin-top: 2px;
  margin-right: 12px;
  text-align: center;
  cursor: pointer;
}

.header-to-home-wrap {
  margin-top: 4px;
  text-align: center;
  cursor: pointer;
}

.dot_show {
  background-color: red;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  display: block;
}

.dot_unshow {
  background-color: #FFFFFF00;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  display: block;
}

.header-avatar-wrap {
  flex: 0 0 auto;
  margin-left: 16px;
  margin-right: 16px;
}


</style>