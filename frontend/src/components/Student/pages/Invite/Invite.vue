<script setup>
import {useRoute, useRouter} from "vue-router";
import axiosInstance from "@/utils/axios";
import {reactive, ref, onMounted} from "vue";
import HeaderBar from "@/components/Student/HeaderBar.vue";
import Avatar from "@/components/Student/Avatar.vue";

const router = useRouter()
const route = useRoute()



const userName = ref('')
const invite_word = ref('')
const userId = ref('')

function sendInvite() {
  let temp = new FormData()
  temp.append('id', Number(userId.value))
  temp.append('word', invite_word.value)
  axiosInstance.post('/team/invite', temp)
      .then(response => {
        console.log(response.data)
        let errorCode = response.data.data.errorCode
        let msg = response.data.data.message
        if (Number(errorCode) === 0) {
          alert('Invitation sent.')
        } else {
          alert('Failed! ' + msg)
        }

      })
      .catch(error => {
        console.error(error);
      });
}


onMounted(() => {
  userId.value = route.query.userId.toString()
  axiosInstance.get('/user/students', {
    params: {
      id: Number(userId.value)
    }
  }).then(response => {
    userName.value = response.data.data.name
  }).catch(error => {
    console.error(error);
  });
})

</script>

<template>
  <header-bar/>
  <div class="wrap">
    <div class="list">
      <div class="header">
        <p style="font-weight: bold; font-size: 25px">Inviting</p>
        <div class="person">
          <avatar :user-id="userId" :need-levi="false"/>
          <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{userName}}</p>
        </div>
      </div>
      <div>
        <p>Invitation words:</p>
        <el-input
            type="textarea"
            :autosize="{minRows: 3}"
            placeholder="Type your invitation word here."
            v-model="invite_word"
            style="width: 500px; margin-bottom: 20px"
        >
        </el-input>
      </div>
      <div>
        <el-button type="primary" @click="sendInvite">Send</el-button>
      </div>

    </div>
  </div>

</template>

<style scoped>
.wrap {
  display: flex;
  flex-direction: column;
  width: 95vw;
  justify-items: center;
  align-items: center;
  margin-top: 50px;
}

.list {
  display: flex;
  flex-direction: column;
  width: 1000px;
  justify-items: center;
  align-items: center;
}

.person {
  margin: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.header {
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 30px;
}
</style>