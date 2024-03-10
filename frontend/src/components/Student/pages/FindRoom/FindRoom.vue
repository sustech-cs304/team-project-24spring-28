<script setup >
import { ref, computed, onMounted} from 'vue';
import {useRoute, useRouter} from "vue-router";

import HeaderBar from "@/components/Student/HeaderBar.vue";
import MapContainer from "@/components/Student/pages/FindRoom/map/MapContainer.vue";
import briefRoomInfo from "@/components/Student/pages/FindRoom/briefRoomInfo.vue";
import axiosInstance from "@/utils/axios";

const router = useRouter()
const route = useRoute()

let zonings = ref(new Set())
let buildings = ref(new Set())
let floors = ref(new Set())
let roomTypes = ref(new Set())
let dorms = ref([]);

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



onMounted(()=> {
  axiosInstance.get('/room').then((res) => {
    dorms.value = res.data.data;
    console.log('dorms:', dorms.value)
    initializeFilter();
    floors.value = Array.from(floors.value).sort((a, b) => a - b);
  }).catch((err) => {
    console.log(err)
  })
})


function initializeFilter() {
  for (let i = 0; i < dorms.value.length; i++) {
    let zoning = dorms.value[i].building.zoningName;
    zonings.value.add(zoning);

    let building = dorms.value[i].building.buildingName;
    buildings.value.add(building);

    let floor = dorms.value[i].roomFloor;
    floors.value.add(floor);

    let roomTypeName = dorms.value[i].roomType;
    roomTypes.value.add(roomTypeName);
  }
}

let selectedZoning = ref('');
let selectedBuilding = ref('');
let selectedRoomFloor = ref('')
let selectedRoomType = ref('')




const filteredDorms = computed(() => {
  if (dorms.value !== undefined) {
    return dorms.value.filter(dorm => {
      return (!selectedZoning.value || dorm.building.zoningName === selectedZoning.value) &&
          (!selectedBuilding.value || dorm.building.buildingName === selectedBuilding.value) &&
          (!selectedRoomFloor.value || dorm.roomFloor === selectedRoomFloor.value) &&
          (!selectedRoomType.value || dorm.roomType === selectedRoomType.value);
    });
  } else {
    return [];
  }
});

</script>

<template>
  <HeaderBar/>
  <div class="mainPanel">
    <div class="header">
      <h2> 🏠Room Information</h2>
    </div>
    <div class="filter">
      <el-select v-model="selectedZoning" clearable placeholder="请选择区域" style="margin-left: 5px; margin-right: 5px">
        <el-option
            v-for="zoning in zonings"
            :key="zoning"
            :label="zoning"
            :value="zoning">
        </el-option>
      </el-select>
      <el-select v-model="selectedBuilding" clearable placeholder="请选择楼栋" style="margin-left: 5px; margin-right: 5px">
        <el-option
            v-for="building in buildings"
            :key="building"
            :label="building"
            :value="building">
        </el-option>
      </el-select>
      <el-select v-model="selectedRoomFloor" clearable placeholder="请选择楼层" style="margin-left: 5px; margin-right: 5px">
        <el-option
            v-for="roomFloor in floors"
            :key="roomFloor"
            :label="roomFloor"
            :value="roomFloor">
        </el-option>
      </el-select>
      <el-select v-model="selectedRoomType" clearable placeholder="请选择房型" style="margin-left: 5px; margin-right: 5px">
        <el-option
            v-for="roomType in roomTypes"
            :key="roomType"
            :label="roomType"
            :value="roomType">
        </el-option>
      </el-select>
    </div>
    <el-row>
      <el-col :span="15" class="map">
        <MapContainer @selectedZoning="selectedZoning = $event"/>
      </el-col>
      <el-col :span="9" class="infoList">
        <div v-if="filteredDorms && filteredDorms.length > 0">
          <el-row v-for="room in filteredDorms" :key="room.id">
            <briefRoomInfo :room="room"></briefRoomInfo>
          </el-row>
        </div>
        <div v-else>
          <el-empty description="暂无数据"></el-empty>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.mainPanel {
  overflow: hidden;
}

.header{
  background-color: #FFFFFF;
  line-height: 50px;
  font-family: "Times New Roman" ,sans-serif;
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}

.filter {
  background-color: transparent;
  line-height: 60px;
  position: sticky;
  top: 0;
  margin-bottom: 15px;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}
.map {
  background: white;
  height: 655px;
  padding-right: 10px;
  width: 50%;
}
.infoList{
  background: white;
  padding-left: 10px;
  height: 655px;
  width: 50%;
  box-sizing: border-box;
  border-radius: 5px;
  overflow: scroll;
}


</style>



