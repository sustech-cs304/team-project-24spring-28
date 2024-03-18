<script setup>
import {useRoute, useRouter} from "vue-router";

import {ref, reactive, onMounted} from "vue";
import HeaderBar from "@/components/old/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import TeamForm from "@/components/old/Student/pages/Team/TeamForm.vue";
import Avatar from "@/components/old/Student/Avatar.vue";
import {stringify} from "qs";
import {Select} from "@element-plus/icons-vue";

const router = useRouter();
const route = useRoute();
const isCreateFormAvailable = ref(false);
const allTeams = ref([]);
const myTeamId = ref(0);
const myTeamMembers = ref([]);
const myTeam = ref({
  name: '',
  description: '',
});
const hasTeam = ref(false);
const teamCollectedRooms = ref([]);

function createTeam() {
  isCreateFormAvailable.value = true;
}

function closeCreateTeamDialog() {
  isCreateFormAvailable.value = false;
}

function fetchTeamByTeamId() {
  if (myTeamId.value === 0) {
    return;
  }
  axiosInstance.get('team', {
    params: {
      id: myTeamId.value
    }
  }).then(response => {
    myTeam.value= response.data.data
    console.log("myTeam", myTeam);
  }).catch(error => {
    console.error(error);
  });
}
function fetchMembersByTeamId() {
  axiosInstance.get('team/member', {
    params: {
      id: myTeamId.value
    }
  }).then(response => {
    myTeamMembers.value = response.data.data;
    console.log(response.data.data);
  }).catch(error => {
    console.error(error);
  });
}

async function fetchMyTeam() {
  await axiosInstance.get('/student')
      .then(response => {
        myTeamId.value = response.data.data.studentTeamId;
      })
      .catch(error => {
        console.error(error);
      });
  if (myTeamId.value === 0) {
    hasTeam.value = false;
  } else {
    hasTeam.value = true;
    await fetchMembersByTeamId()
  }
}

function fetchTeamCollectedRoom() {
  axiosInstance.get('/student/collect').then(response => {
    console.log("fetchTeamCollectedRoom", response.data.data)
    teamCollectedRooms.value = response.data.data;
  }).catch(error => {
    console.error(error);
  });
}

async function submitTeamForm() {
  console.log(myTeam.value)
  await axiosInstance.post('/student/team', {
    name: myTeam.value.name,
    description: myTeam.value.description,
  })
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.error(error);
      });
  await fetchMyTeam();
  await fetchTeamByTeamId();
  await fetchTeamCollectedRoom();
}
async function quitTeam() {
  await axiosInstance.get('/student/team/quit')
      .then(response => {
        console.log(response);
        if (response.status === 200) {
          if (response.data.code === 0) {
            alert("Quit team successfully!")
          }
          else {
            alert(response.data.msg)
          }
        }
        else {
          alert("Quit team failed!")
        }
      })
      .catch(error => {
        console.error(error);
      });
  await fetchMyTeam();
  await fetchTeamByTeamId();
  await fetchTeamCollectedRoom();
}

function selectCollectedRoom(room) {
  axiosInstance.get('/student/select/room', {
    params: {
      roomId: room.id
    }
  }).then(response => {
    console.log(response);
    fetchTeamCollectedRoom();
    fetchMyTeam();
  }).catch(error => {
    console.error(error);
  });
}

function deleteCollectedRoom(room) {
  axiosInstance.delete('/student/collect', {
    params: {
      roomId: room.id
    }
  }).then(response => {
    console.log(response);
    fetchTeamCollectedRoom();
  }).catch(error => {
    console.error(error);
  });
}

function exchangeCollectedRoom(room) {
  router.push({
    path: '/exchange', query: {
      groupId: room.studentTeam.id,
      groupName: room.studentTeam.name,
    }
  })
}

async function cancelSelectedRoom(room) {
  await axiosInstance.post('/team/checkout').then(response => {
    console.log(response);
    fetchTeamCollectedRoom();
    fetchMyTeam();
  }).catch(error => {
    console.error(error);
  });
  await fetchMyTeam();
  await fetchTeamByTeamId();
  await fetchTeamCollectedRoom();
}

onMounted(async() => {
  await fetchMyTeam();
  await fetchTeamByTeamId();
  await fetchTeamCollectedRoom();
})
</script>

<template>
  <HeaderBar/>
  <div class="team-page-wrapper">
    <div class="team-status-wrap wrap1">
      <h1 v-if="!hasTeam">You don't have team right now</h1>

      <div v-else class="team-members-wrap">
        <h1> Team members</h1>
        <div class="team-members-list-wrap">
          <div v-for="member in myTeamMembers" class="person">
            <avatar :user-id="member.id.toString()" size-mini="100px" size-wrapper="100px"/>
            <p style="font-size: 15px; font-weight: bold;">{{member.name}}</p>
          </div>
        </div>
      </div>
    </div>


    <div class="team-form-wrap wrap1">
      <el-form>
        <h1> Team info </h1>
        <el-form-item label="Team Name" prop="teamName">
          <el-input v-model="myTeam.name"></el-input>
        </el-form-item>
        <el-form-item label="Team Description" prop="teamDescription">
          <el-input type="textarea" autosize v-model="myTeam.description"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button v-if="!hasTeam" type="primary" @click="submitTeamForm()">Create</el-button>
          <el-button v-else type="primary" @click="submitTeamForm()">Update</el-button>
          <el-button v-if="hasTeam" type="primary" @click="quitTeam()">Quit</el-button>

        </el-form-item>
      </el-form>
    </div>
  </div>
  <div v-if="hasTeam && myTeam.room !== null" class="selected-room-wrap">
    <h1>Selected Room</h1>
    <el-table align="center" :data="[myTeam.room]">
      <el-table-column prop="roomNumber" label="Room Number" width="150"></el-table-column>
      <el-table-column prop="roomCapacity" label="Room Capacity" width="150"></el-table-column>
      <el-table-column prop="roomType" label="Room Type" width="150"></el-table-column>
<!--      <el-table-column label="Building" width="150">-->
<!--        <template #default="scope">-->
<!--          <el-tag type="success">{{scope.row.building.name}}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="Operation" width="150">
        <template #default="scope">
          <el-button type="danger" @click="cancelSelectedRoom(scope.row)">Cancel</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div class="collected-room-wrap">
    <h1 v-if="hasTeam">Collected Rooms</h1>
    <el-table v-if="hasTeam" :data="teamCollectedRooms" align="center">
      <el-table-column prop="roomNumber" label="Room Number" width="150"></el-table-column>
      <el-table-column prop="roomCapacity" label="Room Capacity" width="150"></el-table-column>
      <el-table-column prop="roomType" label="Room Type" width="150"></el-table-column>
      <el-table-column label="Room Status" width="150">
        <template #default="scope">
          <el-tag v-if="scope.row.studentTeam" type="danger">OCCUPIED</el-tag>
          <el-tag v-else type="success">AVAILABLE</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Operation" width="320">
        <template #default="scope">
          <el-button v-if="!scope.row.studentTeam" type="primary" @click="selectCollectedRoom(scope.row)" :disabled="hasTeam && myTeam.room !== null">Select</el-button>
          <el-button v-else type="primary" @click="exchangeCollectedRoom(scope.row)" :disabled="myTeam.room === null">Exchange</el-button>
          <el-button type="danger" @click="deleteCollectedRoom(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<style scoped>
  .team-page-wrapper {
    margin: 20px;
    display: flex;
    flex-direction: row;
  }
  .person {
    margin: 0 10px 10px 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .team-status-wrap {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    margin-left: 5px;
    margin-top: 20px;
    border-radius: 10px;
    border: #faaaaa solid 1px;
    background: linear-gradient(#ffe6e6 10%, white);
    box-shadow: 0 5px 10px 0 #EAEAEA;
  }
  .team-members-wrap {
    display: flex;
    flex-direction: column;
  }
  .team-members-list-wrap {
    display: flex;
    flex-direction: row;
  }
  .team-form-wrap {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    margin-left: 5px;
    margin-top: 5px;
    border-radius: 10px;
    border: #88cddc solid 1px;
    background: #eff5ff;
    box-shadow: 0 5px 10px 0 #EAEAEA;
  //background: linear-gradient(#e9f7ff 10%, white);
  }
  .collected-room-wrap {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: flex-start;
    margin-left: 5px;
    margin-top: 20px;
    border-radius: 10px;
    border: #faaaaa solid 1px;
    background: linear-gradient(#ffe6e6 10%, white);
    box-shadow: 0 5px 10px 0 #EAEAEA;
  }
  .selected-room-wrap {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: flex-start;
    margin-left: 5px;
    margin-top: 20px;
    border-radius: 10px;
    border: #faaaaa solid 1px;
    background: linear-gradient(#ffe6e6 10%, white);
    box-shadow: 0 5px 10px 0 #EAEAEA;
  }
  .selected-room-wrap h1 {
    align-self: center;
  }
  .selected-room-wrap .el-table{
    margin: 0 auto;
    --el-table-bg-color: transparent;
    border-radius: 5px;
  }
  .collected-room-wrap h1 {
    align-self: center;
  }
  .collected-room-wrap .el-table{
    margin: 0 auto;
    --el-table-bg-color: transparent;
    border-radius: 5px;
  }
  team-form {
    align-self: center;
  }
  .wrap1 {
    margin-top: 20px;
    display: flex;
    //flex-direction: row;
  }
  button:disabled {
    background-color: #696969;
    border: #304156;
  }
  button:disabled:hover {
    background-color: rgba(105, 105, 105, 0.75);
    border: #304156;
  }
</style>