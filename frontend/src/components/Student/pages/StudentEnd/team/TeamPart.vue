<script setup>
import {useRoute, useRouter} from "vue-router";
import {reactive, ref, onMounted} from "vue";
import HeaderBar from "@/components/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import Avatar from "@/components/Student/Avatar.vue";

const router = useRouter()
const route = useRoute()

const hasTeam = ref(0)

const groupData = ref({})

const selectData = ref([])


function toTeam() {
  router.push({path: '/team'})
}

onMounted(() => {
  axiosInstance.get('/student/team').then(response => {
    let temp = response.data.data;
    hasTeam.value = Number(response.data.code)
    if (hasTeam.value !== 0) {
      return
    }
    let groupId = temp.id
    groupData.value.groupId = groupId
    groupData.value.groupName = temp.name
    axiosInstance.get('/team/member', {
      params: {
        id: Number(groupId)
      }
    }).then(response => {
      let temp = response.data.data;
      groupData.value.groupMembers = []
      for (let i = 0; i < temp.length; i++) {
        groupData.value.groupMembers.push({
          userId: temp[i].id,
          userName: temp[i].name,
        })
      }
    }).catch(error => {
      console.error(error);
    })
  }).catch(error => {
    console.error(error);
  });

  axiosInstance.get('/student/collect').then(response => {
    let temp = response.data.data;
    selectData.value = []
    for (let i = 0; i < temp.length; i++) {
      let roomName = temp[i].building.buildingName + temp[i].roomFloor.toString() + "楼" + temp[i].roomNumber.toString()
      selectData.value.push({
        roomId: temp[i].id,
        roomName: roomName,
        top5Count: temp[i].roomStatus
      })
    }
  }).catch(error => {
    console.error(error);
  });
})

</script>

<template>
  <div class="wrap">
    <div class="main">

      <div class="title-warp">
        <p class="title" @click="toTeam">Team</p>
      </div>

      <div class="group-wrap" v-if="hasTeam === 0">
        <p class="group-title">{{groupData.groupName}}</p>

        <div v-for="member in groupData.groupMembers" class="members-wrap">
          <div class="person">
            <avatar :user-id="member.userId.toString()"/>
            <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{member.userName}}</p>
          </div>
        </div>
      </div>

      <div class="selected-wrap"  v-if="hasTeam === 0">
        <p class="selected-title">Selection list</p>

        <div v-for="item in selectData" class="rooms-warp">
          <div class="room">
            <p class="room-name">{{item.roomName}}</p>
            <p class="top5-count">⭐ {{item.top5Count}}</p>
          </div>

        </div>
      </div>

    </div>

  </div>
</template>

<style scoped>

.wrap {
  width: 300px;
  height: 100%;
  //background-color: aliceblue;
  //border-radius: 10px;
  margin: 10px;
  //border-right: #c7c7c7 solid 1px;
}

.main {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.title-warp {
  margin: 0 5px 5px 10px;
  cursor: pointer;
}

.title {
  display: flex;
  flex-direction: row;
  font-size: 20px;
  margin-top: 5px;
  margin-bottom: 5px;
  border-radius: 5px;
  background: linear-gradient(#e9f7ff 10%, white);
  border: #88cddc solid 1px;
  color: #5e7d91;
  height: 40px;
  width: 80px;
  align-items: center;
  justify-content: center;
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.title:hover {
  box-shadow: #d8f2ff 0 0 10px 0;
  color: #59b4e8;
}

.group-wrap {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 5px;
  margin-top: 5px;
  border-radius: 10px;
  border: #88cddc solid 1px;
  background: #eff5ff;
  box-shadow: 0 5px 10px 0 #EAEAEA;
  //background: linear-gradient(#e9f7ff 10%, white);
}

.group-title {
  font-size: 20px;
  font-weight: bold;
  margin-left: 15px;
  margin-top: 15px;
  margin-bottom: 10px;
  color: #4e97af;
}

.members-wrap {
  margin-left: 15px;
}

.person {
  margin: 0 10px 10px 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.selected-wrap {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 5px;
  margin-top: 20px;
  border-radius: 10px;
  border: #faaaaa solid 1px;
  background: linear-gradient(#ffe6e6 10%, white);
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.selected-title {
  font-size: 20px;
  font-weight: bold;
  margin-left: 15px;
  margin-top: 15px;
  margin-bottom: 10px;
  color: #ff6868;
}

.rooms-warp {
  margin-left: 15px;
  width: 88%;
  overflow-y: auto;
}

.room {
  margin: 0 10px 10px 10px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.room-name {
  font-size: 15px;
  font-weight: bold;
  margin-top: 0;
  margin-bottom: 0;
  cursor: pointer;
}

.top5-count {
  font-size: 15px;
  font-weight: bold;
  margin-top: 0;
  margin-bottom: 0;
  color: #ff6868;
}




</style>