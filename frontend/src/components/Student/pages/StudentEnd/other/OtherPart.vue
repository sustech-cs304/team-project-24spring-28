<script setup>
import {useRoute, useRouter} from "vue-router";
import {reactive, ref, onMounted} from "vue";
import HeaderBar from "@/components/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import {formatTime, nowDate} from "@/components/Student/pages/Message/utils";

const router = useRouter()
const route = useRoute()


const stageData = ref({})

const stages = ref([])

// stageData.value = {
//   currentStage: 2,
//   beginTime: '',
//   endTime: '',
// }

stages.value = [
  {
    id: 1,
    isCurrent: false,
    isPassed: false,
    title: 'Group up',
    msg: 'Please group up, check the rooms, and add the rooms you want to the selection list. It is a good idea to find roommates on the bulletin board.'
  },
  {
    id: 2,
    isCurrent: false,
    isPassed: false,
    title: 'Selection ongoing',
    msg: 'Please do the selection in time. You can do the quick select by hitting the button below.'
  },
  {
    id: 3,
    isCurrent: false,
    isPassed: false,
    title: 'Selection ended',
    msg: 'The selection has ended. Please check the results.'
  }
]

function quickSelect() {
  axiosInstance.get('student/select/quick')
      .then(response => {
        let temp = response.data
        if (Number(temp.code) === 0) {
          if (Number(temp.data.errorCode) === 0) {
            let roomName = JSON.parse(temp.data.message).roomName
            alert('Select room ' + roomName + ' successfully!')
          }
          else {
            console.log(temp.data.message)
          }

        } else {
          console.log("Failed!")
        }

      })
      .catch(error => {
        console.error(error);
      });
}

onMounted(() => {
  axiosInstance.get('/student/schedule/stage')
      .then(response => {
        stageData.value.currentStage = Number(response.data.data.stage)
        if (stageData.value.currentStage === 1 || stageData.value.currentStage === 2) {
          stageData.value.beginTime = formatTime(response.data.data.startTime)
          stageData.value.endTime = formatTime(response.data.data.endTime)
        }


        for (let i = 0; i < stages.value.length; i++) {
          stages.value[i].isCurrent = (stages.value[i].id === stageData.value.currentStage)
          stages.value[i].isPassed = (stages.value[i].id < stageData.value.currentStage)
        }
      })
      .catch(error => {
        console.error(error);
      })


})


</script>

<template>
  <div class="wrap">
    <div class="main">

      <div v-if="stageData.currentStage === 0" class="not-start-wrap">
        <p> Selection is not yet started. </p>
      </div>

      <div v-else class="timeline-wrap">
        <div class="timeline">
          <p class="timeline-title"> Time Line </p>

          <div v-for="item in stages" :key="item.id">

            <div v-if="!item.isCurrent" class="not-current-wrap">
              <div class="dot" :style="'background-color: ' + (item.isPassed ? '#ff5a5a' : '#12bdff')"></div>
              <p class="not-current-title"> {{ item.title }} </p>
            </div>

            <div v-else class="current-whole">
              <div class="current-wrap">
                <div class="current-dot"></div>
                <p class="not-current-title"> {{ item.title }} </p>
              </div>
              <div v-show="stageData.currentStage === 1 || stageData.currentStage === 2">
                <p class="time">Begin at: {{ stageData.beginTime }}</p>
                <p class="time">End at: {{ stageData.endTime }}</p>
              </div>
              <p class="msg"> {{ item.msg }} </p>

            </div>

          </div>
        </div>
      </div>

      <div v-show="stageData.currentStage === 1 || stageData.currentStage === 2" class="count-down-wrap">

      </div>

      <div v-show="stageData.currentStage === 1 || stageData.currentStage === 2" class="quick-select-whole">
        <p class="quick-select-prompt">
          Hit the button to quick select!
        </p>
        <div class="quick-select-wrap">
          <button class="learn-more" @click="quickSelect">
            <p style="color:white; margin: 0">
              ↓ ↘ → ↓ ↘ → 👊
            </p>
          </button>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>

.wrap {
  width: 360px;
  height: 100%;
  //background-color: #ffa6a6;
  border-radius: 10px;
  margin: 10px;
  display: flex;
  flex-direction: column;
}

.main {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: stretch;
  justify-content: space-evenly;
}

.not-start-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

.timeline-wrap {
  width: 100%;
  border: #c7c7c7 solid 1px;
  border-radius: 10px;
  margin-top: 10px;
  background: linear-gradient(#f1f5ff 5%, white);
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.timeline {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: flex-start;
  margin-left: 10px;
  margin-right: 15px;
  margin-bottom: 15px;
}

.timeline-title {
  font-size: 20px;
  font-weight: bold;
  margin-left: 10px;
}

.not-current-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 20px;
  margin-bottom: 20px;
}

.not-current-title {
  font-size: 15px;
  font-weight: bold;
  margin-top: 0;
  margin-bottom: 0;
}

.dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-right: 10px;
}

.current-whole {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 20px;
  margin-bottom: 10px;
}

.current-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 10px;
}

.current-dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-right: 10px;
  background: linear-gradient(#5be75b, #7dff7d);
}

.time {
  font-size: 12px;
  margin-top: 0;
  margin-bottom: 5px;
  margin-left: 35px;
}

.msg {
  font-size: 15px;
  margin: 0 10px 0 25px;
}












.quick-select-whole {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  border-radius: 10px;
  border: #ee6464 solid 1px;
  //background: linear-gradient(#ffcccc, #ffffff);
  background: #ffdbe3;
  box-shadow: 0 5px 10px 0 #EAEAEA;
}

.quick-select-prompt {
  font-size: 17px;
  font-weight: bold;
  color: #ff6480;
  cursor: default;
  width: 282px;
  text-align: center;
}

.quick-select-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-bottom: 50px;
}

button {
  position: relative;
  display: inline-block;
  cursor: pointer;
  outline: none;
  border: 0;
  vertical-align: middle;
  text-decoration: none;
  font-family: inherit;
  font-size: 15px;
  color: white;
}

button.learn-more {
  font-weight: 600;
  color: #382b22;
  text-transform: uppercase;
  padding: 1.25em 2em;
  background: #ff6c6c;
  border: 2px solid #000000;
  border-radius: 0.75em;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  -webkit-transition: background 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
  transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), background 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
}

button.learn-more::before {
  position: absolute;
  content: '';
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #e35959;
  border-radius: inherit;
  -webkit-box-shadow: 0 0 0 2px #000000, 0 0.625em 0 0 #ff6c6c;
  box-shadow: 0 0 0 2px #000000, 0 0.625em 0 0 #c7c7c700;
  -webkit-transform: translate3d(0, 0.75em, -1em);
  transform: translate3d(0, 0.75em, -1em);
  transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), box-shadow 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-box-shadow 150ms cubic-bezier(0, 0, 0.58, 1);
}

button.learn-more:hover {
  background: #ff6c6c;
  -webkit-transform: translate(0, 0.25em);
  transform: translate(0, 0.25em);
}

button.learn-more:hover::before {
  -webkit-box-shadow: 0 0 0 2px #000000, 0 0.5em 0 0 #ff6c6c;
  box-shadow: 0 0 0 2px #000000, 0 0.5em 0 0 #c7c7c700;
  -webkit-transform: translate3d(0, 0.5em, -1em);
  transform: translate3d(0, 0.5em, -1em);
}

button.learn-more:active {
  background: #ff6c6c;
  -webkit-transform: translate(0em, 0.75em);
  transform: translate(0em, 0.75em);
}

button.learn-more:active::before {
  -webkit-box-shadow: 0 0 0 2px #000000, 0 0 #ff6c6c;
  box-shadow: 0 0 0 2px #000000, 0 0 #c7c7c700;
  -webkit-transform: translate3d(0, 0, -1em);
  transform: translate3d(0, 0, -1em);
}

</style>