<script setup>
import {useRoute, useRouter} from "vue-router";
import {reactive, ref, onMounted} from "vue";
import HeaderBar from "@/components/old/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import BulletinCard from "@/components/old/Student/pages/StudentEnd/bulletin/BulletinCard.vue";

const router = useRouter()
const route = useRoute()


function toBulletin() {
  router.push({path: '/bulletin'})
}


let last = -1
const colors = [
  ["#d6e6ff", "#a1c7ff"],
  ["#d7f9f8", "#87d7d1"],
  ["#d9ffdb", "#84d386"],
  ["#fffede", "#e0ce7f"],
  ["#fff0d4", "#eec281"],
  ["#fbe0e0", "#faa9a9"],
  ["#e5d4ef", "#c5aaea"],
]
function genColor() {
  let index = last
  while (index === last) {
    index = Math.floor(Math.random() * 7)
  }
  last = index
  return colors[index]
}

const bulletinData = ref([])

// const bulletinData = ref([
//   {
//     userId: 123456,
//     userName: 'LampTales',
//     sex: 'Male',
//     degree: 'Master',
//     tags: ['原神高手', '熬夜冠军', '夜鹿什么时候有新歌'],
//     introduction: 'We use both first and third-party cookies to personalise web content, analyse visits to our websites and tailor advertisements. Some of these cookies are necessary for the website to function, whilst others require your consent. More detail can be found in our cookie policy and you can tailor your choices in the preference centre.',
//   },
//   {
//     userId: 234567,
//     userName: '说的道理',
//     sex: 'Male',
//     degree: 'Doctoral',
//     tags: ['古神', '哇袄', '可爱捏', '卡拉苏'],
//     introduction: '起初人们无法理解神究竟想说什么，但神还是一遍遍地诉说着。终于有一天，人们从神的话语中分辨出了简短的一句话：啊↓-↑~~~米↓浴↑说↓的↑道↓~~理↑↑↑~~~',
//   },
//   {
//     userId: 345678,
//     userName: 'AAAA',
//     sex: 'Female',
//     degree: 'Master',
//     tags: ['123', '234', '345', '456', '7893453564', '345', '456', '7893453564',
//       '123', '234', '345', '456', '7893453564', '345', '456', '7893453564',
//       '123', '234', '345', '456', '7893453564', '345', '456', '7893453564',],
//     introduction: 'English texts for beginners to practice reading and comprehension online and for free. Practicing your comprehension of written English will both improve your vocabulary and understanding of grammar and word order. The texts below are designed to help you develop while giving you an instant evaluation of your progress.'
//   },
//   {
//     userId: 45678,
//     userName: 'AAAA',
//     sex: 'Female',
//     degree: 'Doctoral',
//     tags: ['123', '234', '345', '456'],
//     introduction: 'English texts for beginners to practice reading and comprehension online and for free. Practicing your comprehension of written English will both improve your vocabulary and understanding of grammar and word order. The texts below are designed to help you develop while giving you an instant evaluation of your progress.'
//   },
//   {
//     userId: 678,
//     userName: 'AAAA',
//     sex: 'Female',
//     degree: 'Master',
//     tags: ['123', '234', '345', '456'],
//     introduction: 'English texts for beginners to practice reading and comprehension online and for free. Practicing your comprehension of written English will both improve your vocabulary and understanding of grammar and word order. The texts below are designed to help you develop while giving you an instant evaluation of your progress.'
//   },
//
// ])


function refresh() {
  bulletinData.value = []
  axiosInstance.get('/student/random')
      .then(response => {

        let temp = response.data.data
        for (let i = 0; i < temp.length; i++) {
          axiosInstance.get('/profile', {
            params: {
              profileId: temp[i].studentProfileId

            }
          }).then(response => {
            let temp2 = response.data.data
            bulletinData.value.push({
                  userId: temp[i].id,
                  userName: temp[i].name,
                  sex: temp2.sex,
                  degree: temp2.type,
                  introduction: (temp2.selfIntroduction == null ? '' : temp2.selfIntroduction),
                  tags: (temp2.tags == null ? [] : temp2.tags.split(';')),
                })
          }).catch(error => {
                console.error(error);
              });

        }

      })
      .catch(error => {
        console.error(error);
      })
}

onMounted(() => {
  refresh();
})

</script>

<template>
  <div class="wrap">
    <div class="main">
      <div class="header">
        <div class="title-warp">
          <p class="title" @click="toBulletin">Bulletin Board</p>
        </div>

        <div class="refresh-wrap" @click="refresh">
          <svg viewBox="0 0 30 30" width="30" height="30" class="icon">
            <image href="@/assets/svgs/refresh.svg" width="30" height="30"></image>
          </svg>
        </div>
      </div>

      <div class="bulletinCards">
        <div v-for="item in bulletinData" :key="item">
          <bulletin-card :user-id="item.userId.toString()" :user-name="item.userName"
                         :sex="item.sex" :degree="item.degree"
                         :tags="item.tags" :introduction="item.introduction" :color="genColor()"/>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>

.wrap {
  flex: 1;
  //background-color: #f3fbff;
  max-height: 88vh;
  margin-top: 10px;
  //border-left: darkgray solid 1px;
  //border-right: darkgray solid 1px;
}

.main {
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin-left: 5px;
  margin-right: 5px;
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
  width: 180px;
  align-items: center;
  justify-content: center;
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.title:hover {
  box-shadow: #d8f2ff 0 0 10px 0;
  color: #59b4e8;
}

.refresh-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 0 10px 5px;
  cursor: pointer;
  height: 40px;
  width: 40px;
  border-radius: 5px;
  //background-color: #ffc0ff;
  background: linear-gradient(#ffc0cb 10%, white);
  border: #ef868f solid 1px;
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.refresh-wrap:hover {
  box-shadow: #ffc6c7 0 0 10px 0;
  background: linear-gradient(#ffcfd3 10%, white);

}

.bulletinCards {
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: scroll;
  margin-left: 5px;
  margin-right: 5px;
}

</style>