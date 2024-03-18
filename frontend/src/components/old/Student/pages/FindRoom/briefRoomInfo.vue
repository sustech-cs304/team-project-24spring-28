<script setup>
  import {useRoute, useRouter} from "vue-router";
  import {ref, onMounted} from "vue";
  import axiosInstance from "@/utils/axios";

  const router = useRouter()
  const route = useRoute()

  const props = defineProps({
    room: {
      type: Object,
      required: true
    }
  });

  let briefUrl_huPan = ref('https://sustc.wiki/images/thumb/c/c2/%E4%BB%8E%E8%BF%8E%E6%96%B0%E6%A1%A5%E6%89%80%E8%A7%81%E7%9A%84%E6%B9%96%E7%95%94.jpg/1200px-%E4%BB%8E%E8%BF%8E%E6%96%B0%E6%A1%A5%E6%89%80%E8%A7%81%E7%9A%84%E6%B9%96%E7%95%94.jpg')
  let briefUrl_xueShengSuShe = ref('https://sustc.wiki/images/2/25/%E4%BA%8C%E6%9C%9F%E5%AE%BF%E8%88%8D.jpg')
  let briefUrl_liYuan = ref('https://sustc.wiki/images/thumb/c/ce/%E8%8D%94%E5%9B%AD%E9%97%A8%E5%8F%A3.jpg/450px-%E8%8D%94%E5%9B%AD%E9%97%A8%E5%8F%A3.jpg')
  let brief_xinYuan = ref('https://sustc.wiki/images/thumb/6/6e/%E6%96%B0%E5%9B%AD%E5%AE%BF%E8%88%8D.jpg/450px-%E6%96%B0%E5%9B%AD%E5%AE%BF%E8%88%8D.jpg')
  let realURL = ref('')
  const isStarOn = ref()

  onMounted(()=> {
    axiosInstance.get('/student/collect', {
      headers: {
        'Authorization': localStorage.getItem('token')
      },
      params: {
        roomId: props.room.id
      }
    }).then(response => {
        console.log("single room collect:", response.data);
        isStarOn.value = response.data.data
      }).catch(error => {
        console.error(error);
      });
    console.log("props.room.building.zoningName:", props.room.building.zoningName)
    if (props.room.building.zoningName === '湖畔') {
      realURL.value = briefUrl_huPan
    } else if (props.room.building.zoningName === '学生宿舍') {
      realURL.value = briefUrl_xueShengSuShe
    } else if (props.room.building.zoningName === '荔园') {
      realURL.value = briefUrl_liYuan
    } else if (props.room.building.zoningName === '欣园') {
      realURL.value = brief_xinYuan
    }
    console.log("realURL:", realURL.value)
  })

  function toDetailInfo(room) {
    router.push({path: '/detailInfo', query: {roomId: room.id}})
  }

  function toggleStar(room) {
    if (!isStarOn.value) {
      axiosInstance.post(`/student/collect?roomId=${room.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }
      ).then((res) => {
        console.log(res.data)
      }).catch((err) => {
        console.log(err)
      })

    } else {
      axiosInstance.delete(`/student/collect?roomId=${room.id}`, {
        headers: {
          'Authorization': localStorage.getItem('token')
        }
      }).then((res) => {
        console.log(res.data)
      }).catch((err) => {
        console.log(err)
      })
    }
  }

</script>

<template>
  <div class="roomInfo">
    <el-row>
      <el-col :span="1"></el-col>
      <el-col :span="8">
        <el-image
            style="width: 100%; height: 100%; border-radius: 5px;"
            :src="realURL.value" @click="toDetailInfo(room)">
        </el-image>
      </el-col>
      <el-col :span="1"></el-col>
      <el-col :span="11">
        <el-row @click="toDetailInfo(room)">
          <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{room.building.zoningName}}</p>
          <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{room.building.buildingName}}</p>
        </el-row>
        <el-row style="font-size: 15px; font-weight: bold; margin-left: 10px; margin-bottom: 12px">
          {{room.roomNumber}}
        </el-row>
        <el-row style="font-size: 15px; font-weight: bold; margin-left: 10px;">
          {{room.roomType}}
        </el-row>
      </el-col>
      <el-col :span="3">
          <input :id="`switch-${room.id}`" type="checkbox" style="opacity: 0" v-model="isStarOn">
          <label :for="`switch-${room.id}`" class="love-heart" @click.stop="toggleStar(room)">
            <i class="left"></i>
            <i class="right"></i>
            <i class="bottom"></i>
            <div class="round"></div>
          </label>
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>
.roomInfo {
  width: 100%;
  height: 150px;
  padding-top: 20px;
  border-radius: 30px;
  margin: 15px 5px;
  background: #efefe0;
  box-shadow: 15px 15px 30px #bebebe,
  -15px -15px 30px #ffffff;
}

.love-heart:before {
  display: none;
}

.love-heart, .love-heart::after {
  border: hsl(231deg 28% 86%);
  border: 1px solid;
  border-top-left-radius: 100px;
  border-top-right-radius: 100px;
  width: 10px;
  height: 8px;
  border-bottom: 0
}

.round {
  position: absolute;
  z-index: 1;
  width: 8px;
  height: 8px;
  background: hsl(0deg 0% 100%);
  box-shadow: rgb(0 0 0 / 24%) 0 0 4px 0;
  border-radius: 100%;
  left: 0;
  bottom: -1px;
  transition: all .5s ease;
  animation: check-animation2 .5s forwards;
}

input:checked + label .round {
  transform: translate(0px, 0px);
  animation: check-animation .5s forwards;
  background-color: hsl(0deg 0% 100%);
}

@keyframes check-animation {
  0% {
    transform: translate(0px, 0px);
  }

  50% {
    transform: translate(0px, 7px);
  }

  100% {
    transform: translate(7px, 7px);
  }
}

@keyframes check-animation2 {
  0% {
    transform: translate(7px, 7px);
  }

  50% {
    transform: translate(0px, 7px);
  }

  100% {
    transform: translate(0px, 0px);
  }
}

.love-heart {
  box-sizing: border-box;
  position: relative;
  transform: rotate(-45deg) translate(-50%, 0) scale(2);
  display: block;
  border-color: hsl(231deg 28% 86%);
  cursor: pointer;
  top: 0;
}

input:checked + .love-heart, input:checked + .love-heart::after, input:checked + .love-heart .bottom {
  border-color: hsl(347deg 81% 61%);
  box-shadow: inset 6px -5px 0 2px hsl(347deg 99% 72%);
}

.love-heart::after, .love-heart .bottom {
  content: "";
  display: block;
  box-sizing: border-box;
  position: absolute;
  border-color: hsl(231deg 28% 86%);
}

.love-heart::after {
  right: -9px;
  transform: rotate(90deg);
  top: 7px;
}

.love-heart .bottom {
  width: 11px;
  height: 11px;
  border-left: 1px solid;
  border-bottom: 1px solid;
  border-color: hsl(231deg 28% 86%);
  left: -1px;
  top: 5px;
  border-radius: 0 0 0 5px;
}


</style>