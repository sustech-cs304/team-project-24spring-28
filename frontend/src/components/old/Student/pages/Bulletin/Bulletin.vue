<script setup>
import {useRoute, useRouter} from "vue-router";
import {ref, onMounted,defineExpose} from "vue";
import HeaderBar from "@/components/old/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import BulletinCard from "@/components/old/Student/pages/StudentEnd/bulletin/BulletinCard.vue";
import BriefStudentInfo from "@/components/old/Student/pages/Bulletin/briefStudentInfo.vue";
import Avatar from "@/components/old/Student/Avatar.vue";


const router = useRouter()
const route = useRoute()

const gender = ref('')
const degree = ref('')
const content = ref('')

const studentList = ref([])
const selectedStudent = ref(null)
const groupInfo = ref(null)
const groupMemberList = ref([])


function clickSearch(){
  if (content.value === '' && gender.value === '' && degree.value === '') {
    axiosInstance.get('/student/random', {
    })
      .then(function (res) {
        let tmp = res.data.data
        console.log("tmp:", tmp)
        studentList.value = []
        for (let i = 0; i < tmp.length; i++) {
          axiosInstance.get('/profile', {
            params: {
              profileId: tmp[i].studentProfileId
            }
          }).then((res) => {
            studentList.value.push(
                {
                  userId: tmp[i].id,
                  userName: tmp[i].name,
                  sex: res.data.data.sex,
                  degree: res.data.data.type,
                  tags: res.data.data.tags==null ? [] : res.data.data.tags.split(';'),
                  introduction: res.data.data.selfIntroduction,
                  studentTeamId: tmp[i].studentTeamId,
                })
          }).catch((err) => {
            console.log(err)
          })
        }
        console.log("studentList:", studentList.value)
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  else {
    console.log("content:", content.value)
    console.log("sex:",gender.value)
    console.log("type:",degree.value)
    axiosInstance.get('/student/search',{
      params:{
        content: content.value,
        sex: gender.value,
        type: degree.value
      }
    }).then((res) => {
      let tmp = res.data.data
      console.log("tmp:", tmp)
      studentList.value = []
      for (let i = 0; i < tmp.length; i++) {
        axiosInstance.get('/profile', {
          params: {
            profileId: tmp[i].studentProfileId
          }
        }).then((res) => {
          studentList.value.push(
              {
                userId: tmp[i].id,
                userName: tmp[i].name,
                sex: res.data.data.sex,
                degree: res.data.data.type,
                tags: res.data.data.tags==null ? [] : res.data.data.tags.split(';'),
                introduction: res.data.data.selfIntroduction,
                studentTeamId: tmp[i].studentTeamId,
              })
        }).catch((err) => {
            console.log(err)
          })
      }
      console.log("studentList:", studentList.value)
    }).catch((err) => {
      console.log(err)
    })
  }
}


onMounted(() => {
  if (route.query.tag) {
    console.log("tag:", route.query.tag)
    axiosInstance.get('/student/search', {
      params: {
        content: route.query.tag,
        sex:'',
        type:''
      }
    }).then(function (response) {
        let tmp = response.data.data
        console.log("tmp:", tmp)
        studentList.value = []
        for (let i = 0; i < tmp.length; i++) {
          axiosInstance.get('/profile', {
            params: {
              profileId: tmp[i].studentProfileId
            }
          }).then((res) => {
            studentList.value.push(
              {
                userId: tmp[i].id,
                userName: tmp[i].name,
                sex: res.data.data.sex,
                degree: res.data.data.type,
                tags: res.data.data.tags==null ? [] : res.data.data.tags.split(';'),
                introduction: res.data.data.selfIntroduction,
                studentTeamId: tmp[i].studentTeamId,
            })
          })
            .catch((err) => {
              console.log(err)
            })
        }
        content.value = route.query.tag
        // console.log("studentList:", studentList.value)
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  else {
    axiosInstance.get('/student/random', {
    }).then(function (response) {
        let tmp = response.data.data
        console.log("tmp:", tmp)
        studentList.value = []
        for (let i = 0; i < tmp.length; i++) {
          axiosInstance.get('/profile', {
            params: {
              profileId: tmp[i].studentProfileId
            }
          }).then((res) => {
            studentList.value.push(
                {
                  userId: tmp[i].id,
                  userName: tmp[i].name,
                  sex: res.data.data.sex,
                  degree: res.data.data.type,
                  tags: res.data.data.tags==null ? [] : res.data.data.tags.split(';'),
                  introduction: res.data.data.selfIntroduction,
                  studentTeamId: tmp[i].studentTeamId,
                })
            console.log("studentList:", studentList.value[i].degree==='Master')
          }).catch((err) => {
              console.log(err)
            })
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  }
})

// let profile_sex = ref('')
// let profile_degree = ref('')
// let profile_tags = ref([])
// let profile_introduction = ref('')

function selectStudent(student) {
  selectedStudent.value = student

  // axiosInstance.get('/profile', {
  //   params: {
  //     profileId: student.studentProfileId
  //   }
  // }).then((res) => {
  //   profile.value = res.data.data
  //   console.log("profile:", profile.value)
  //   if (profile.value !== null) {
  //     profile_sex.value = profile.value.sex;
  //     profile_degree.value = profile.value.type;
  //     profile_tags.value = profile.value.tags.split(';');
  //     profile_introduction.value = profile.value.selfIntroduction;
  //   }
  // })
  //   .catch((err) => {
  //     console.log(err)
  //   })

  axiosInstance.get('/team', {
    params: {
      id: student.studentTeamId
    }
  }).then((res) => {
    groupInfo.value = res.data.data
  }).catch((err) => {
    console.log(err)
  })
  axiosInstance.get('/team/member', {
    params: {
      id: student.studentTeamId
    }
  }).then((res) => {
      groupMemberList.value = res.data.data
    })
    .catch((err) => {
      console.log(err)
    })
}

const toggleFilter = () => {
  let filter = document.querySelector('.filter');
  let studentList = document.querySelector('.studentList');
  let filterHeight = filter.offsetHeight;
  studentList.style.maxHeight = (400 + filterHeight) + 'px';
};

defineExpose({
  toggleFilter
});


const colors = [
  ["#d6e6ff", "#a1c7ff"],
  ["#d7f9f8", "#87d7d1"],
  ["#d9ffdb", "#84d386"],
  ["#fffede", "#e0ce7f"],
  ["#fff0d4", "#eec281"],
  ["#fbe0e0", "#faa9a9"],
  ["#e5d4ef", "#c5aaea"],
]
let last = -1
function genColor() {
  let index = last
  while (index === last) {
    index = Math.floor(Math.random() * 7)
  }
  last = index
  return colors[index]
}


</script>

<template>
  <div class="HeaderBar">
    <HeaderBar/>
  </div>
  <div class="wrap">
    <el-row :gutter="5">
      <el-col :span="12">
        <div class="searchWrap">
          <div class="InputContainer">
            <input placeholder="name, sid or tags(use ';' to split multiple tags)" id="input" class="input" name="text" type="text" v-model="content" @keyup.enter="clickSearch()">
          </div>
          <button id="firstButton" @click="clickSearch()">
            search
          </button>
        </div>
        <div class="filter">
          <div>
            <div>
              <el-row>
                <el-col :span="12">
                  <el-select v-model="gender" placeholder="select gender" clearable>
                    <el-option label="Male" value="Male"></el-option>
                    <el-option label="Female" value="Female"></el-option>
                  </el-select>
                </el-col>
                <el-col :span="12">
                  <el-select v-model="degree" placeholder="select degree" clearable>
                    <el-option label="Master" value="Master"></el-option>
                    <el-option label="Doctoral" value="Doctoral"></el-option>
                  </el-select>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
        <div class="studentList">
          <div v-for="item in studentList" :key="item.userId" @click="selectStudent(item)">
            <BriefStudentInfo :color="genColor()" :user-id="item.userId.toString()" :user-name="item.userName"
                              :sex="item.sex" :degree="item.degree">
            </BriefStudentInfo>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="detailWrap">
          <div class="studentIntro">
            <div v-if="selectedStudent">
              <bulletin-card :user-id="selectedStudent.userId.toString()" :user-name="selectedStudent.userName"
                             :sex="selectedStudent.sex" :degree="selectedStudent.degree"
                             :tags="selectedStudent.tags" :introduction="selectedStudent.introduction" :color="colors[0]"/>
            </div>
            <div v-else>
              <el-empty description="暂无数据"></el-empty>
            </div>
          </div>
          <div class="groupInfoWrap">
            <div v-if="groupInfo && groupMemberList">
              <div class="groupInfo">
                <p class="groupName">{{ groupInfo.name }}</p>
                <p class="groupDescription">{{ groupInfo.description }}</p>
                <div class="members-wrap">
                  <div v-for="member in groupMemberList" class="person">
                    <avatar :user-id="member.id.toString()"/>
                    <p style="font-size: 15px; font-weight: bold; margin-left: 10px; color: lightcoral">{{member.username}}</p>
                    <p style="font-size: 15px; font-weight: bold; margin-left: 10px;">{{member.name}}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>

html, body {
  height: 100%;
  margin: 0;
}

.wrap{
  width: calc(100% - 20px);
  height: 670px;
  margin-left: 10px;
  overflow: hidden;
  z-index: -3;
}

.searchWrap{
  margin-top: 10px;
  width: 100%;
  display: flex;
  align-items: center;
  padding-left: 10px;
}

.filter{
  width: 89%;
  margin-top: 5px;
  margin-left: 10px;
  padding: 10px;
}

.InputContainer {
  width: 550px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom,rgb(227, 213, 255),rgb(255, 231, 231));
  border-radius: 30px;
  overflow: hidden;
  cursor: pointer;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.075);
  margin-right: 30px;
}

.input {
  width: 540px;
  height: 40px;
  border: none;
  outline: none;
  caret-color: rgb(255, 81, 0);
  background-color: rgb(255, 255, 255);
  border-radius: 30px;
  padding-left: 15px;
  letter-spacing: 1px;
  color: rgb(19, 19, 19);
  font-size: 13px;
}

#firstButton {
  padding: 13px 30px;
  border: 0;
  border-radius: 10px;
  background-color: #2ba8fb;
  color: #ffffff;
  font-weight: Bold;
  transition: all 0.5s;
  -webkit-transition: all 0.5s;
}

#firstButton :hover {
  background-color: #6fc5ff;
  box-shadow: 0 0 20px #6fc5ff50;
  transform: scale(1.1);
}

#firstButton :active {
  background-color: #3d94cf;
  transition: all 0.25s;
  -webkit-transition: all 0.25s;
  box-shadow: none;
  transform: scale(0.98);
}


.studentList{
  display: flex;
  flex-direction: column;
  width: 93%;
  height: 100%;
  margin-top: 10px;
  margin-left: 10px;
  border-radius: 10px;
  padding-top: 10px;
  max-height: 530px;
  overflow-y: scroll;
  overflow-x: hidden;
}

.studentIntro{
  padding-top: 10px;
  z-index: -2;
}

.detailWrap{
  margin-top: 20px;
  margin-right: 20px;
  border-radius: 10px;
  height: 100%;
  z-index: -2;
}

.groupInfoWrap{
  padding-top: 10px;
}

.groupInfo{
  border: #c7c7c7 solid 1px;
  border-radius: 10px;
  margin-top: 10px;
  background: linear-gradient(#f1f5ff 5%, white);
  box-shadow: 0 5px 10px 0 #EAEAEA;
  margin-left: 10px;
  margin-right: 10px;
}

.groupName{
  font-size: 20px;
  font-weight: bold;
  margin-left: 15px;
  margin-top: 15px;
  margin-bottom: 10px;
  color: #4e97af;
}

.groupDescription{
  font-size: 15px;
  margin: 0 20px;
}

.members-wrap {
  margin-left: 15px;
}

.person {
  margin: 10px 10px 10px 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}



</style>