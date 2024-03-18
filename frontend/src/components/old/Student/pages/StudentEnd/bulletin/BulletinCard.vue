<script setup>

import Avatar from "@/components/old/Student/Avatar.vue";
import {useRoute, useRouter} from "vue-router";
import {reactive, ref, onMounted} from "vue";

const router = useRouter()
const route = useRoute()

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
  tags: {
    type: Array,
    required: true
  },
  introduction: {
    type: String,
    required: true
  },
  color: {
    type: Array,
    required: true
  }
})



function searchProfilesByTag(tag) {
  router.push({path: 'bulletin', query: {tag: tag}})
}

let last = -1
const colors = [
  '#ec111a',
  '#fb6330',
  '#ffd42f',
  '#1bd25b',
  '#009dd6',
  '#7849b8',
  '#f2609e']
function genColor(tag) {
  let index = 0;
  for (let i = 0; i < tag.length; i++) {
    index += tag.charCodeAt(i)
  }
  index = index % 7
  return colors[index]
}



</script>

<template>
<!--  <div :style="'background-color: ' + color[0] + '; border: 2px solid ' + color[1] " class="wrap"></div>-->
  <div class="entity"
       :style="`background: linear-gradient(${color[0]}, 10%, white);
        border: 1px solid ${color[1]};
        box-shadow: 0 5px 10px 0 #EAEAEA`">
    <div class="header">
      <div class="person">
        <avatar :user-id="userId"/>
        <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{userName}}</p>
      </div>
    </div>

    <div class="sub-info-warp">
      <p class="sub-info" :style="sex === 'Male' ? 'color: blue' : 'color: hotpink'">{{sex}}&nbsp;</p>
      <p class="sub-info" :style="degree === 'Master' ? 'color: blueviolet' : 'color: forestgreen'">{{degree}}</p>
    </div>

    <div class="tags-wrap">
      <div v-for="item in tags" :key="item">
        <div class="tag" @click="searchProfilesByTag(item)" :style="'background-color: ' + genColor(item)">
          <p class="tag_text">{{item}}</p>
        </div>
      </div>
    </div>

    <div class="content">
      <p class="comment_content">{{introduction}}</p>
    </div>

  </div>

</template>

<style scoped>

.entity {
  //background-color: white;
  display: flex;
  flex-direction: column;
  justify-self: stretch;
  align-items: flex-start;
  border-radius: 10px;
  margin: 10px;
}

.header {
  margin-top: 15px;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 30px;
}

.person {
  margin: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.sub-info-warp {
  display: flex;
  align-items: flex-start;
  margin-left: 12px;
  margin-top: 5px;
}

.sub-info {
  font-size: 12px;
  cursor: default;
  margin-bottom: 0;
  margin-top: 5px;
  font-weight: bold;
}

.tags-wrap {
  margin-top: 3px;
  margin-left: 5px;
  max-width: 40%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  cursor: pointer;
}

.tag {
  display: flex;
  align-items: center;
  border-radius: 8px;
  height: 16px;
  margin-left: 5px;
  margin-top: 2px;
}

.tag_text {
  margin-left: 5px;
  margin-right: 5px;
  font-size: 8px;
  color: white;
}

.content {
  margin: 5px 10px 10px 20px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.comment_content {
  text-align: left;
  font-size: 16px;
  margin-top: 5px;
  margin-bottom: 5px;
}

</style>