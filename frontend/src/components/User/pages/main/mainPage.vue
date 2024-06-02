<script>
import {ref} from 'vue'
import postCardForM from "@/components/User/pages/main/components/postCardForM.vue";
import EventCard from "@/components/User/pages/main/components/EventCard.vue";
import * as API from "@/components/User/pages/main/mainApi"
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
import EventCardBig from "@/components/Modules/event/EventCardBig.vue";

import {
    ArrowLeft,
    ArrowRight,
    Delete,
    Edit, Search,
    Share,
} from "@element-plus/icons";

// const count = ref(0)
// const load = () => {
//     count.value += 10
// }
export default {
    computed: {
        Search() {
            return Search
        },
        Delete() {
            return Delete
        },
        Share() {
            return Share
        },
        Edit() {
            return Edit
        },
        ArrowLeft() {
            return ArrowLeft
        }
    },
    components: {
      EventCardBig,
      // eslint-disable-next-line vue/no-unused-components
      EventCard,
      HeaderForAll,
      postCardForM
    },

  data() {
    return {
      input: '' ,// 绑定搜索框的输入值
      events: [],
      eventIds:[],
      postItems: [],
      event:{}
    };
  },

  async created() {
    await this.loadEventItems();
    // await this.loadEventItems()
  },

  methods: {
      async loadEventItems() {
      const ids = [1, 2];
      for (const id of ids) {
        // const res = await API.getBriefEvent(id);
        // this.events.push(res);
        this.eventIds.push(id);
      }
    },

    async loadPostlItems() {
      const ids = [1, 2, 3, 4];
      for (const id of ids) {
        const res = await API.getPost(id);
        this.postItems.push(res.data);
      }
    },

    onClear() {
      // 清除搜索框的输入值
      this.input = '';
    },
    search() {
      if (this.input.trim() !== '') {

      } else {
        alert('请输入搜索内容');
      }
    }
  }
}
</script>

<template>
    <div class="common-layout-all">
      <HeaderForAll>
      </HeaderForAll>
      <el-row>
        <div class="container">
          <h1>校园活动平台</h1>
          <p>欢迎来到校园活动平台，发现最新最热门的校园活动！</p>
          <el-input
              v-model="input"
              placeholder="搜索你感兴趣的活动！"
              clearable
              @clear="onClear"
          >
            <template #prepend><el-button @click="search" type="primary">搜索</el-button></template>
          </el-input>
        </div>
      </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="24">
                    <el-row>
                        <el-col>
                            <el-card style="border-radius: 0.5vw">
                                <el-carousel height="40vh" motion-blur interval="6000">
<!--                                    <el-carousel-item v-for="item in events" :key="item.id">-->
<!--                                      <div class="event-card-wrapper">-->
<!--                                        <event-card-big :id="item.eventId" />-->
<!--                                      </div>-->
<!--                                    </el-carousel-item>-->
                                  <el-carousel-item v-for="item in eventIds" :key="item.id">
                                    <div class="event-card-wrapper">
                                      <event-card-big :id="item" />
                                    </div>
                                  </el-carousel-item>
                                </el-carousel>
                            </el-card>
                        </el-col>
                    </el-row>
            </el-col>
        </el-row>
      <el-row>
        <el-col :span="24">
          <div>
            <postCardForM></postCardForM>
            <postCardForM></postCardForM>

          </div>
        </el-col>
      </el-row>
    </div>
</template>

<style scoped>
/* scoped */
.common-layout-all {
  height: 100vh;
}

.common-layout {
  height: 80vh;
}

.el-row {
  border-radius: 0.5vw;
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 0.5vw;
}

.el-carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
}

.event-card-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}




</style>
