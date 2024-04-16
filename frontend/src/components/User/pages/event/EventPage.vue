<script setup>
import { ref } from 'vue'
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import Comment from '@/components/Modules/comment/Comment.vue'
import Avatar from '@/components/Modules/avatar/Avatar.vue'
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";

import {useRoute, useRouter} from "vue-router";
const router = useRouter()
const route = useRoute()

const eventId = route.query.id
console.log(eventId)

let title = ref('')
let eventName = ref('')
let authorId = ref('')
let authorName = ref('')
let applyStartTime = ref('')
let applyEndTime = ref('')
let startTime = ref('')
let endTime = ref('')
let score = ref(0)
let grade = ref(2)
let posterUrl = ref('')
let text = ref('')
let postList = ref([])

// just for test
title = '某某活动马上就要开始了！'
eventName = '活动某某'
authorId = '123456'
authorName = 'Lamptales'
applyStartTime = '2024-4-4 00:00:00'
applyEndTime = '2024-4-14 00:00:00'
startTime = '2024-4-16 00:00:00'
endTime = '2024-4-26 00:00:00'
score = '4'
posterUrl = 'https://static.fotor.com.cn/assets/projects/pages/c3000361e65b4048ab8dd18e8c076c0e/fotor-86b1e566f1d74bf1870ac2c2a624390f.jpg'

let stars = ref("")
stars = '⭐'
for (let i = 1; i < score; i++) {
    stars = stars + '⭐'
}

text = 'sdf\n' +
    '### Title\n' +
    '\n' +
    '![Description](https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg){{{width="200" height="auto"}}}'

let test_text = ref('')
test_text = '<p align="left">\n' +
    '    English ｜ <a href="README.md">中文</a>\n' +
    '</p>\n' +
    '<br>\n' +
    '\n' +
    '<h1 align="center">\n' +
    '  Llama-Chinese\n' +
    '</h1>\n' +
    '<p align="center" width="100%">\n' +
    '  <img src="https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg" alt="Llama" style="width: 20%; display: block; margin: auto;"></a>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <font face="黑体" color=orange size="6"> The Best Chinese Llama Large Language Model </font>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <a href="https://llama.family">Online: llama.family</a>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <a href="https://huggingface.co/FlagAlpha/Atom-7B-Chat">Open-source Chinese Pre-trained LLM Atom based on Llama2</a>\n' +
    '</p>\n' +
    '\n'

postList = [
  {
    title: 'Title1',
    author: 'Author1',
    time: '2024-4-4',
  },
  {
    title: 'Title2',
    author: 'Author2',
    time: '2024-4-4',
  },
  {
    title: 'Title3',
    author: 'Author3',
    time: '2024-4-4',
  }
]

function showGrade(newGrade) {
  console.log(newGrade)
}
</script>

<template>
  <div>
    <header-for-all/>
  </div>
  <div class="main">
    <div class="left-body">
      <div>
        <h1 class="event-title">{{ title }}</h1>
      </div>

      <div class="name-time-wrap">
        <p style="margin-top: 5px; margin-bottom: 5px; color: #3abbff;">{{ eventName }}</p>
      </div>

      <div style="margin-top: 10px; margin-left: 5px; font-size: 12px">
        报名时间: {{applyStartTime}} - {{applyEndTime}}
      </div>

      <div style="margin-top: 10px; margin-left: 5px; font-size: 12px">
        活动时间: {{applyStartTime}} - {{applyEndTime}}
      </div>

      <div>
        <p style="margin-top: 10px"
        >{{ stars }}</p>
      </div>

      <div>
        <img :src="posterUrl"/>
      </div>

      <div>
        <v-md-preview :text="test_text"></v-md-preview>
      </div>

      <comment comment-block-id="1"></comment>


    </div>

    <div class="right-panel">
      <div class="author-wrap">
        <Avatar :user-id="authorId" :need-small="true" size-small="60px" name="LampTales"></Avatar>
        <p style="margin-left: 40px; font-size: 18px;"
        >{{ authorName }}</p>
      </div>
      <div>
        <p class="event-title">Related Posts</p>
      </div>

      <div>
        <el-card v-for="post in postList" :key="post.title" style="margin-top: 20px">
          <el-row>
            <el-col>
              <p>{{ post.title }}</p>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <p>{{ post.author }}</p>
            </el-col>
            <el-col>
              <p>{{ post.time }}</p>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>

  </div>

  <div class="bottom-button">
    <el-button type="primary" @click="handleClick">我要参加</el-button>
    <el-button type="primary"
               @click="handleClick"
               style="margin-left: 20px;"
    >我想发帖</el-button>

    <div class="rating" style="margin-left: 20px; margin-right: 20px">
      <input value="5" name="rate" id="star5" type="radio" v-model="grade" @click="showGrade(5)"/>
      <label title="text" for="star5"></label>
      <input value="4" name="rate" id="star4" type="radio" v-model="grade" @click="showGrade(4)"/>
      <label title="text" for="star4"></label>
      <input value="3" name="rate" id="star3" type="radio" v-model="grade" @click="showGrade(3)"/>
      <label title="text" for="star3"></label>
      <input value="2" name="rate" id="star2" type="radio" v-model="grade" @click="showGrade(2)"/>
      <label title="text" for="star2"></label>
      <input value="1" name="rate" id="star1" type="radio" v-model="grade" @click="showGrade(1)"/>
      <label title="text" for="star1"></label>
    </div>
  </div>
</template>

<style scoped>

.main {
  width: 99vw;
  display: flex;
  flex-direction: row;
  overflow-y: scroll;
  height: 82vh;
}

.event-title {
  font-size: 20px;
}

.name-time-wrap {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-top: 5px;
  margin-bottom: 5px;
  margin-left: 5px;
}

.left-body {
  width: 70%;
  margin-left: 20px;
}

.right-panel {
  margin-left: 20px;
  width: 25%;
}

.author-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: auto;
  margin-bottom: 60px;
}

.bottom-button {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  height: 7vh;
  margin-right: 100px;
  margin-left: 50px;
}

.rating:not(:checked) > input {
  position: absolute;
  appearance: none;
}

.rating:not(:checked) > label {
  float: right;
  cursor: pointer;
  font-size: 30px;
  color: #666;
}

.rating:not(:checked) > label:before {
  content: '★';
}

.rating > input:checked + label:hover,
.rating > input:checked + label:hover ~ label,
.rating > input:checked ~ label:hover,
.rating > input:checked ~ label:hover ~ label,
.rating > label:hover ~ input:checked ~ label {
  color: #e58e09;
}

.rating:not(:checked) > label:hover,
.rating:not(:checked) > label:hover ~ label {
  color: #ff9e0b;
}

.rating > input:checked ~ label {
  color: #ffa723;
}
</style>