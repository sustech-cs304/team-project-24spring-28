<script setup>
import {useRoute, useRouter} from "vue-router";
import axiosInstance from "@/utils/axios";
import {reactive, ref, onMounted} from "vue";
import HeaderBar from "@/components/old/Student/HeaderBar.vue";
import Avatar from "@/components/old/Student/Avatar.vue";

const router = useRouter()
const route = useRoute()


const groupId = ref('')
const groupName = ref('')
const exchangeWord = ref('')

function sendExchange() {
  let temp = new FormData()
  temp.append('id', Number(groupId.value))
  temp.append('word', exchangeWord.value)
  axiosInstance.post('/student/room/exchange', temp)
      .then(response => {
        console.log(response.data)
        let errorCode = JSON.parse(response.data.data).errorCode
        let msg = JSON.parse(response.data.data).message
        if (Number(errorCode) === 0) {
          alert('Exchange sent.')
        } else {
          alert('Failed! ' + msg)
        }

      })
      .catch(error => {
        console.error(error);
      });
}


onMounted(() => {
  groupId.value = route.query.groupId
  groupName.value = route.query.groupName
})

</script>

<template>
  <header-bar/>
  <div class="wrap">
    <div class="list">
      <div class="header">
        <p style="font-weight: bold; font-size: 25px">Sending exchange to&nbsp;</p>
        <div>
          <p style="font-weight: bold; font-size: 25px; color: #ffa6b5">{{groupName}}</p>
        </div>
      </div>
      <div>
        <p>Exchange words:</p>
        <el-input
            type="textarea"
            :autosize="{minRows: 3}"
            placeholder="Type your Exchange word here."
            v-model="exchangeWord"
            style="width: 500px; margin-bottom: 20px"
        >
        </el-input>
      </div>
      <div>
        <el-button type="primary" @click="sendExchange">Send</el-button>
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

.header {
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 30px;
}
</style>