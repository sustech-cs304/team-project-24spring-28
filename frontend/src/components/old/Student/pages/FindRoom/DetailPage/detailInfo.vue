<script setup>
import {useRoute, useRouter} from "vue-router";
import {ref, onMounted} from "vue";
import axiosInstance from "@/utils/axios";

import HeaderBar from "@/components/old/Student/HeaderBar.vue";
import comment from "@/components/old/Student/pages/FindRoom/DetailPage/comment.vue";

const router = useRouter()
const route = useRoute()

const props = defineProps({
  id: {
    type: Number,
  },
  building: {
    type: Object,
    default: () => ({
      id: Number,
      zoningName: String,
      buildingName: String,
      buildingDescription: String,
    })

  },
  roomType: {
    type: String,
  },
  roomNumber: {
    type: Number,
  },
  roomLayoutUrl: {
    type: String,
  },
  roomDescription: {
    type: String,
  },
  roomCapacity: {
    type: Number,
  },
})

const URLS = ref({
  huPan:['http://10.16.88.247:8084/hupan1.jpg','http://10.16.88.247:8084/hupan.jpg'],
  erQi:['http://10.16.88.247:8084/erqi1.jpg', 'http://10.16.88.247:8084/11dong.jpg'],
})


const roomId = route.query.roomId

const dorm = ref([])

const isStarOn = ref()

let zoningName = ref()
let buildingName = ref()
let roomNumber = ref()
let roomDescription = ref()
let buildingDescription = ref()
let roomType = ref()
let roomCapacity = ref()
let roomLayoutUrl = ref('')
let floorPlanUrl = ref('')

let collectNumber = ref(0)

let isSelected = ref(false)
let selectGroup = ref(null)

onMounted(()=>{
  axiosInstance.get(`/room?roomId=${roomId}`).then((res) => {
    dorm.value = res.data.data
    zoningName.value = dorm.value.building.zoningName
    buildingName.value = dorm.value.building.buildingName
    roomNumber.value = dorm.value.roomNumber
    roomDescription.value = dorm.value.roomDescription
    buildingDescription.value = dorm.value.building.buildingDescription
    roomType.value = dorm.value.roomType
    roomCapacity.value = dorm.value.roomCapacity
    if (dorm.value.studentTeam != null) {
      isSelected.value = true
      selectGroup.value = dorm.value.studentTeam
    }
    if(zoningName.value==='湖畔'){
      roomLayoutUrl.value = URLS.value.huPan[0]
      floorPlanUrl.value = URLS.value.huPan[1]
    }
    else if (zoningName.value==='学生宿舍'){
      roomLayoutUrl.value = URLS.value.erQi[0]
      floorPlanUrl.value = URLS.value.erQi[1]
    }
    else{
      roomLayoutUrl.value = URLS.value.huPan[0]
      floorPlanUrl.value = URLS.value.huPan[1]
    }
  }).catch((err) => {
    console.log(err)
  })
  axiosInstance.get('/student/collect', {
    headers: {
      'Authorization': localStorage.getItem('token')
    },
    params: {
      roomId: roomId
    }
  }).then(res => {
    isStarOn.value = res.data.data;
  }).catch(error => {
    console.error(error);
  });
  axiosInstance.get('/room',{
    params: {
      roomId: roomId
    }
  }).then((res) => {
    collectNumber.value = res.data.data.roomStatus
  }).catch((err) => {
    console.log(err)
  })
})

const toggleButton = () => {
  isStarOn.value = !isStarOn.value;
  if (isStarOn.value) {
    axiosInstance.post(`/student/collect?roomId=${route.query.roomId}`, {
      headers: {
        'Authorization': localStorage.getItem('token')
      },
    }).then((res) => {
      console.log(res.data)
    }).catch((err) => {
      console.log(err)
    })
  collectNumber.value += 1
  } else {
    axiosInstance.delete(`/student/collect?roomId=${route.query.roomId}`, {
      headers: {
        'Authorization': localStorage.getItem('token')
      },
    }).then((res) => {
      console.log(res.data)
    }).catch((err) => {
      console.log(err)
    })
    collectNumber.value -= 1
}
}

function toExchange() {
  if (isSelected.value) {
    console.log("selectGroup", selectGroup.value)
    router.push({
      path: '/exchange',
      query: {
        groupId: selectGroup.value.id,
        groupName: selectGroup.value.name,
      }
    })
  } else {
    alert("The room is not selected yet!")
  }
}

</script>

<template>
  <div>
    <el-col :span="24">
      <HeaderBar/>
    </el-col>
  </div>
  <div class="image">
    <el-row :gutter="5">
      <el-col :span="12">
        <el-image
            style="width: 100%; height: 400px; z-index: -1; border-radius: 10px;"
            :src="roomLayoutUrl">
        </el-image>
      </el-col>
      <el-col :span="12">
        <el-image
            style="width: 100%; height: 400px; z-index: -1; border-radius: 10px;"
            :src="floorPlanUrl">
        </el-image>
      </el-col>
      <el-col :span="8"></el-col>
    </el-row>

  </div>
  <el-row :gutter="10">
    <el-col :span="16">
      <el-row class="roomHeader">
        <span v-text="zoningName" style="color: #077EB1"></span>
        <el-divider direction="vertical"></el-divider>
        <span v-text="buildingName" style="color: #077EB1"></span>
        <el-divider direction="vertical"></el-divider>
        <span v-text="roomNumber"></span>
      </el-row>
      <el-row>
        <el-col :span="15">
          <span v-text="roomNumber" style="color: #4C4C4C;font-family: Microsoft YaHei,sans-serif; font-size: 40px; margin: 5px 20px;"></span>
        </el-col>
        <el-col :span="4" style="font-size: 18px;">
          <div style="padding-bottom: 5px">
            <span style="font-size: 30px; font-weight: bold">⭐ </span>
            <span style="font-weight: bold">&nbsp;&nbsp;&nbsp;{{collectNumber}}</span>
            <span style="font-weight: bold"> stars</span>
          </div>
        </el-col>
        <el-col :span="2"></el-col>
        <el-col :span="3">
          <div class="startArea">
            <span style="margin-right: 10px; font-weight: bold; font-size: 16px; color: #ff93c9">Star</span>
            <div class="heart-container" title="Like">
              <input type="checkbox" class="checkbox" id="Give-It-An-Id" @click="toggleButton" v-model="isStarOn">
              <div class="svg-container">
                <svg viewBox="0 0 24 24" class="svg-outline" xmlns="http://www.w3.org/2000/svg">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
                  </path>
                </svg>
                <svg viewBox="0 0 24 24" class="svg-filled" xmlns="http://www.w3.org/2000/svg">
                  <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
                  </path>
                </svg>
                <svg class="svg-celebrate" width="100" height="100" xmlns="http://www.w3.org/2000/svg">
                  <polygon points="10,10 20,20"></polygon>
                  <polygon points="10,50 20,50"></polygon>
                  <polygon points="20,80 30,70"></polygon>
                  <polygon points="90,10 80,20"></polygon>
                  <polygon points="90,50 80,50"></polygon>
                  <polygon points="80,80 70,70"></polygon>
                </svg>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
      <el-row class="basicInfo">
        <el-col :span="12">
          <div class="roomTypeContainer">
            <span style="color: #7382de; font-size: x-large; font-weight: bold">Room type</span>
            <p></p>
            <span v-text="roomType" style="color: black; font-size: 18px; font-weight: bold"></span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="roomCapacityContainer">
            <span style="color: #7382de; font-size: x-large; font-weight: bold">Capacity</span>
            <p></p>
            <span v-text="roomCapacity" style="color: black; font-size: 18px; font-weight: bold"></span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <comment />
      </el-row>
    </el-col>
    <el-col :span="8">
      <div class="rightBar">
        <form class="Description">
          <span class="title" style="text-align: center">Building Description</span>
          <p class="description" v-text="buildingDescription"></p>
        </form>
        <p></p>
        <form class="Description">
          <span class="title" style="text-align: center">Room Description</span>
          <p class="description" v-text="roomDescription"></p>
        </form>
        <div class="Exchange">
          <div v-if="isSelected" style="text-align: center">
            <span style="font-size: large; font-weight: bold">The room is selected by:</span>
            <p></p>
            <span style="color: orangered; font-size: large; font-weight: bold; text-align: center" v-text="selectGroup.name"></span>
            <p></p>
          </div>
          <div v-else>
            <span style="font-size: large; font-weight: bold">The room is not selected</span>
            <p></p>
          </div>
          <button v-if="isSelected" @click="toExchange()">
            Exchange
          </button>
        </div>
      </div>
    </el-col>
  </el-row>



</template>

<style scoped>
.image{
  margin: 20px 0;
  height: 100%;
  width: 100%;
}

.roomHeader{
  color: #4C4C4C;
  font-family: "Microsoft YaHei",sans-serif;
  font-size: 16px;
  margin: 5px 20px;
}

.startArea{
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-right: 15px;
  background-color: rgba(255, 20, 20, 0.05);
  border: lightpink solid 3px;
  border-radius: 10px;
  padding: 5px 10px;
}

.rightBar{
  position: sticky;
  top: 30px;
  z-index: 1000;
}


.basicInfo{
  width: 98%;
  margin-left: 10px;
  border-radius: 1rem;
  border: rgba(0,0,255,0.1) solid 2px;
  background: linear-gradient(to bottom, rgba(0,0,255,0.1) 0%, rgba(100,100,255,0.1) 50%, rgba(200,200,255,0.1) 90%, white 100%);
}

.roomTypeContainer{
  border-right: #E4E7ED solid 2px;
  margin: 10px 0;
  width: 100%;
  text-align: center;
}

.roomCapacityContainer{
  margin: 10px 0;
  width: 100%;
  text-align: center;
}


.heart-container {
  --heart-color: rgb(255, 91, 137);
  position: relative;
  width: 38px;
  height: 38px;
  transition: .3s;
  vertical-align: middle;
}

.heart-container .checkbox {
  position: absolute;
  width: 100%;
  height: 100%;
  opacity: 0;
  z-index: 20;
  cursor: pointer;
}

.heart-container .svg-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

}

.heart-container .svg-outline,
.heart-container .svg-filled {
  fill: var(--heart-color);
  position: absolute;
}

.heart-container .svg-filled {
  animation: keyframes-svg-filled 1s;
  display: none;
}

.heart-container .svg-celebrate {
  position: absolute;
  animation: keyframes-svg-celebrate .5s;
  animation-fill-mode: forwards;
  display: none;
  stroke: var(--heart-color);
  fill: var(--heart-color);
  stroke-width: 2px;
}

.heart-container .checkbox:checked~.svg-container .svg-filled {
  display: block
}

.heart-container .checkbox:checked~.svg-container .svg-celebrate {
  display: block
}

@keyframes keyframes-svg-filled {
  0% {
    transform: scale(0);
  }

  25% {
    transform: scale(1.2);
  }

  50% {
    transform: scale(1);
    filter: brightness(1.5);
  }
}

@keyframes keyframes-svg-celebrate {
  0% {
    transform: scale(0);
  }

  50% {
    opacity: 1;
    filter: brightness(1.5);
  }

  100% {
    transform: scale(1.4);
    opacity: 0;
    display: none;
  }
}

.Description {
  display: flex;
  flex-direction: column;
  background: #ffa7a7;
  padding: 20px;
  margin-left: 45px;
  border-radius: 10px;
  max-width: 360px;
}

.title {
  font-size: 2rem;
  line-height: 2rem;
  font-weight: 700;
  letter-spacing: -0.025em;
  color: #fff;
}

.description {
  line-height: 1.5rem;
  font-size: 1rem;
  margin-top: 1rem;
  color: white;
}

.form div {
  display: flex;
  max-width: 28rem;
  margin-top: 1rem;
  column-gap: 0.5rem;
}

.form div input {
  outline: none;
  line-height: 1.5rem;
  font-size: 0.875rem;
  color: rgb(255 255 255 );
  padding: 0.5rem 0.875rem;
  background-color: rgb(255 255 255 / 0.05);
  border: 1px solid rgba(253, 253, 253, 0.363);
  border-radius: 0.375rem;
  flex: 1 1 auto;
}

.form div input::placeholder {
  color: rgb(216, 212, 212);
}

.form div input:focus {
  border: 1px solid rgb(99 102 241);
}

.form div button {
  color: #fff;
  font-weight: 600;
  font-size: 0.875rem;
  line-height: 1.25rem;
  padding: 0.625rem 0.875rem;
  background-color: rgb(99 102 241);
  border-radius: 0.375rem;
  border: none;
  outline: none;
}

button {
  width: 150px;
  height: 60px;
  border: 3px solid palevioletred;
  border-radius: 45px;
  transition: all 0.3s;
  cursor: pointer;
  background: white;
  font-size: 1.2em;
  font-weight: 550;
  font-family: 'Montserrat', sans-serif;
}

button:hover {
  background: palevioletred;
  color: white;
  font-size: 1.5em;
}


.Exchange{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  border-radius: 10px;
  border: #faaaaa solid 1px;
  background: linear-gradient(#ffe6e6 10%, white);
  box-shadow: 0 5px 10px 0 #EAEAEA;
  padding: 20px;
  margin-left: 45px;
  max-width: 360px;
}

</style>