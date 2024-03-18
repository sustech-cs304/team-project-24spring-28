<template>
  <div>
    <button @click="toLogin">
      Login
    </button>
    <button @click="toMessage">
      Message
    </button>
    <input type="text" v-model="toSwitch">
    <button @click="toFindRoom">
      FindRoom
    </button>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {ref} from "vue";
const router = useRouter()

const toSwitch = ref('chats')

function toLogin() {
  router.push({path: '/message'})
}
function toMessage() {
  router.push({path: '/message', query: {messageType: toSwitch.value}})
  // router.push({name: 'message', params: {id: 1}})
}

function toFindRoom() {
  router.push({path: '/findroom'})
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
