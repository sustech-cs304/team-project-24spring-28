<script setup>
import {ref, onMounted} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import axiosInstance from "@/utils/axios";
import Avatar from "@/components/old/Student/Avatar.vue";
import CommentEntity from "@/components/old/Student/pages/FindRoom/DetailPage/CommentEntity.vue";

const router = useRouter()
const route = useRoute()


let value = ref(0)
let textarea = ref('')

const user = ref(localStorage.getItem('userId'))

const roomId = route.query.roomId

const commentData = ref([])

onMounted(() => {
  axiosInstance.get(`/comment/room`, {params: {roomId: roomId}}).then((res) => {
    commentData.value = res.data.data
    for (let i = 0; i < commentData.value.length; i++) {
      axiosInstance.get(`/comment/under`, {
            params: {
              commentId: commentData.value[i].id
            }
          }
      ).then((res) => {
        commentData.value[i].subComments = res.data.data
        console.log('commentData:', commentData)
      }).catch((err) => {
        console.log(err)
      })
    }
  }).catch((err) => {
    console.log(err)
  })

})

function submitComment() {
  if (textarea.value === '') {
    return
  }
  axiosInstance.get('/student/comment', {
    headers: {
      'Authorization': localStorage.getItem('token')
    },
    data: null,
    params: {
      'roomId': roomId,
      'comment': textarea.value
    }
  }).then((res) => {
    console.log(res.data)
  }).catch((err) => {
    console.log(err)
  })
}


</script>
<template>
  <div class="mainPanel">
    <div class="commentTitle">
      <span style="font-weight: bold">Comments</span>
    </div>
    <div class="yourCommentContainer">
      <el-row>
        <el-col :span="2">
          <Avatar :user-id="user"/>
        </el-col>
        <el-col :span="19">
          <input type="text" placeholder="leave you comment here" name="text" class="input" v-model="textarea">
        </el-col>
        <el-col :span="2">
          <button @click="submitComment">
            <div class="svg-wrapper-1">
              <div class="svg-wrapper">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    width="24"
                    height="24"
                >
                  <path fill="none" d="M0 0h24v24H0z"></path>
                  <path
                      fill="currentColor"
                      d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"
                  ></path>
                </svg>
              </div>
            </div>
            <span>Send</span>
          </button>

        </el-col>
      </el-row>
    </div>
    <div class="CommentContainer">
      <CommentEntity
          v-for="comment in commentData"
          :key="comment.id"
          :id="comment.id"
          :commenterId="comment.studentId"
          :commenterName="comment.studentName"
          :roomId="comment.roomId"
          :commentContent="comment.comment"
          :subComments="comment.subComments"
      />
    </div>
  </div>

</template>

<style scoped>

.mainPanel {
  margin: 10px 10px;
  width: 100%;
  height: 100%;
  border-radius: 10px;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}

.commentTitle {
  color: #18191C;
  font-family: "Microsoft YaHei", sans-serif;
  font-size: 25px;
  margin: 10px 20px;
}

.yourCommentContainer {
  margin: 10px 20px;
}

.CommentContainer {
  margin: 10px 20px;
}


.input {
  border-radius: 10px;
  outline: 2px solid #3e55e1;
  border: 0;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background-color: #e2e2e2;
  outline-offset: 3px;
  padding: 10px 1rem;
  transition: 0.25s;
  width: 90%;
}

.input:focus {
  outline-offset: 5px;
  background-color: #fff
}

button {
  transform: scale(0.9);
  font-family: inherit;
  font-size: 18px;
  background: royalblue;
  color: white;
  padding: 0.7em 1em;
  margin-top: -5px;
  margin-left: -20px;
  display: flex;
  align-items: center;
  border: none;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.2s;
  cursor: pointer;
}

button span {
  display: block;
  margin-left: 0.3em;
  transition: all 0.3s ease-in-out;
}

button svg {
  display: block;
  transform-origin: center center;
  transition: transform 0.3s ease-in-out;
}

button:hover .svg-wrapper {
  animation: fly-1 0.6s ease-in-out infinite alternate;
}

button:hover svg {
  transform: translateX(1.2em) rotate(45deg) scale(1.1);
}

button:hover span {
  transform: translateX(5em);
}

button:active {
  transform: scale(0.95);
}

@keyframes fly-1 {
  from {
    transform: translateY(0.1em);
  }

  to {
    transform: translateY(-0.1em);
  }
}


</style>