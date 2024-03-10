<script setup>
import Avatar from "@/components/Student/Avatar.vue";
import axiosInstance from "@/utils/axios";
import {defineProps, ref, onMounted} from "vue";

const props = defineProps({
  userId: {
    type: String,
    required: true
  },
  userName: {
    type: String,
    required: true
  },
  sex: {
    type: String,
    required: true
  },
  degree: {
    type: String,
    required: true
  },
  color: {
    type: Array,
    required: true
  }
})

let sex = ref('')
let degree = ref('')

onMounted(() => {
})

</script>

<template>
  <div class="entity"
       :style="`background: linear-gradient(${props.color[0]}, 10%, white);
        border: 1px solid ${props.color[1]};
        box-shadow: 0 5px 10px 0 #EAEAEA`">
    <div class="header-and-info">
      <div class="header">
        <div class="person">
          <avatar :user-id="props.userId"/>
          <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{props.userName}}</p>
        </div>
      </div>

      <div class="sub-info-warp">
        <p class="sub-info" :style="props.sex === 'Male' ? 'color: blue' : 'color: hotpink'">{{props.sex}}&nbsp;</p>
        <p class="sub-info" :style="props.degree === 'Master' ? 'color: blueviolet' : 'color: forestgreen'">{{props.degree}}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>

.entity {
display: flex;
flex-direction: column;
justify-self: stretch;
align-items: flex-start;
border-radius: 10px;
margin: 10px;
}

.header-and-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header {
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.person {
  margin: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.sub-info-warp {
  display: flex;
  align-items: center;
  margin-left: 12px;
  margin-top: 5px;
}

.sub-info {
  font-size: 15px;
  cursor: default;
  margin-bottom: 0;
  margin-top: 5px;
  font-weight: bold;
}

</style>