<script setup>
import {useRoute, useRouter} from "vue-router";
import {computed, ref} from "vue";
import {onMounted} from "vue";
import {ElForm, ElFormItem, ElInput, ElButton} from "element-plus";
import axiosInstance from "@/utils/axios";
import HeaderBar from "@/components/Student/HeaderBar.vue";

const selfInfo = ref({
  id:1,
  sex: 'Male',
  type: 'Master',
  selfIntroduction: '',
  tags: '',
  birthday:null,
});

let name = ref('');

const user = ref(localStorage.getItem('userId'))

const isEditing = ref(false);
const avatar_url = computed(() => {
  return 'http://10.16.88.247:8084/' + user.value + '.png'
})

function fetchSelfInfo() {
  console.log('fetchSelfInfo');
  axiosInstance.get('/student/profile',{
    headers: {
      'Authorization': localStorage.getItem('token')
    }
  }).then(response => {
        selfInfo.value = response.data.data;
        console.log('selfInfo:', selfInfo.value);
      })
      .catch(error => {
        console.error(error);
      });
  axiosInstance.get('/user',{
    headers: {
      'Authorization': localStorage.getItem('token')
    },
  }).then(response => {
      name.value = response.data.data.name;
      console.log('name:', name.value);
    })
    .catch(error => {
      console.error(error);
    });


}

function handleRevise() {
  console.log('handleRevise');
  isEditing.value = true;
}

function handleApply() {
  console.log('handleApply:',selfInfo.value);
  axiosInstance.put('/student/profile', selfInfo.value)
      .then(
          response => {
            console.log("apply:", response);
          }
      ).catch(error => {
    console.error(error);
  });
  isEditing.value = false;
}

function changeAvatar() {
  document.getElementById('myAvatarInputButton').click();
}

function onChangeAvatar(params) {
  let formData = new FormData()
  formData.append('image', params.file)
  console.log()
  return axiosInstance.post('/avatar', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

onMounted(() => {
  fetchSelfInfo();
});

</script>

<template>
  <div class="HeaderBar">
    <HeaderBar/>
  </div>
  <div class="wrap">
    <div class="form-container">
      <div>
        <h1 style="text-align: center">Self Information</h1>
      </div>
      <el-row :gutter="10">
        <el-col :span="6">
          <div class="Avatar" @click="changeAvatar">
            <el-upload type="primary" :http-request="onChangeAvatar" :show-file-list="false" id="myAvatarInput" ref="uploadRef">
              <el-button type="primary" id="myAvatarInputButton">Import</el-button>
            </el-upload>
            <img :src="avatar_url" alt="avatar">
            <div class="Mask-content">
              <svg width="36" height="36" viewBox="0 0 24 24" class="Zi Zi--Camera UserAvatarEditor-cameraIcon" fill="currentColor"><path fill-rule="evenodd" d="M7.394 4.814A2.41 2.41 0 0 1 9.65 3.25h4.7a2.41 2.41 0 0 1 2.256 1.564l.539 1.436h2.195a2.41 2.41 0 0 1 2.41 2.41v9.68a2.41 2.41 0 0 1-2.41 2.41H4.66a2.41 2.41 0 0 1-2.41-2.41V8.66a2.41 2.41 0 0 1 2.41-2.41h2.195l.539-1.436ZM13.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0Zm1.5 0a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" clip-rule="evenodd"></path></svg>
              <div class="UserAvatarEditor-maskInnerText">修改我的头像</div>
            </div>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="detailInfo">
            <span style="font-weight: bold">Name:&nbsp;</span>
            <span>{{name}}</span>
            <p></p>
            <span style="font-weight: bold">Sex:&nbsp;</span>
            <span>{{selfInfo.sex}}</span>
            <p></p>
            <span style="font-weight: bold">Degree:&nbsp;</span>
            <span>{{selfInfo.type}}</span>
          </div>
          <div class="changeableInfo">
            <span style="font-weight: bold">Self Introduction:</span>
            <p></p>
            <el-input
                type="textarea"
                :rows="2"
                v-model="selfInfo.selfIntroduction"
                :disabled="!isEditing"
                style="max-width: 80%; margin-left: 20px">
            </el-input>
            <p></p>
            <span style="font-weight: bold">Tags:</span>
            <p></p>
            <el-input
                type="textarea"
                :rows="2"
                v-model="selfInfo.tags"
                :disabled="!isEditing"
                style="max-width: 80%; margin-left: 20px">
            </el-input>
            <p></p>
            <el-button v-if="!isEditing" @click="handleRevise">Revise</el-button>
            <el-button v-else @click="handleApply">Apply</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<style scoped>
.wrap {
  display: flex;
  align-self: center;
  align-content: center;
  flex-direction: column;
  background-color: rgba(25,27,31,0.1);
  border-radius: 4px;
  margin: 14px auto;
  overflow: hidden;
}

.form-container {
  align-self: center;
  height: 650px;
  width: 1000px;
  margin: 10px auto;
  border-radius: 4px;
  background-color: white;
}

.Avatar{
  position: relative;
  top: 10%;  /* 调整这一行来改变Avatar组件的垂直位置 */
  left: 10%;  /* 调整这一行来改变Avatar组件的水平位置 */
  width: 150px;  /* 添加这一行 */
  height: 150px; /* 添加这一行 */
}

.Avatar img {
  width: 100%;
  height: 100%;
}

.Mask-content{
  color: #fff;
  left: 50%;
  position: absolute;
  text-align: center;
  top: 50%;
  transform: translate(-50%,-50%);
}

.UserAvatarEditor-maskInnerText {
  white-space: nowrap;
}

.detailInfo{
  position: relative;
  top: 10%;  /* 调整这一行来改变Avatar组件的垂直位置 */
  width: 100%;  /* 添加这一行 */
  height: 50%; /* 添加这一行 */
  font-size: 18px;
}

.changeableInfo{
  width: 100%;  /* 添加这一行 */
  height: 100%; /* 添加这一行 */
  font-size: 18px;
}

#myAvatarInput {
  display: none;
}

</style>