<script>
import {ref} from 'vue'
import postCardForM from "@/components/User/pages/main/components/postCardForM.vue";
import EventCard from "@/components/User/pages/main/components/EventCard.vue";
import * as API from "@/components/User/pages/main/mainApi"
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
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
      // eslint-disable-next-line vue/no-unused-components
      EventCard,
      HeaderForAll,
      postCardForM
    },

  data() {
    return {
      input: '' ,// 绑定搜索框的输入值
      eventItems: [],
      postItems: [],
      event:{}
    };
  },

  async created() {
    await this.loadEventlItems();
    await this.loadEventlItems()
  },

  methods: {
      async loadEventlItems() {
      const ids = [1, 2, 3, 4];
      for (const id of ids) {
        const res = await API.getBriefEvent(id);
        this.eventItems.push(res.data);
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
      // 执行搜索跳转逻辑，这里只是一个示例
      // 替换为你实际的跳转逻辑
      // if (this.input.trim() !== '') {
      //   window.location.href = `/search?query=${encodeURIComponent(this.input)}`;
      // } else {
      //   alert('请输入搜索内容');
      // }
      window.location.href = '/search'
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
                                <el-carousel height="30vh" motion-blur interval="6000">
                                    <el-carousel-item v-for="item in eventItems" :key="item.id">
                                        <h3 class="small justify-center" text="2xl">{{ item.name }}</h3>
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
            <postCardForM></postCardForM>
            <postCardForM></postCardForM>
            <postCardForM></postCardForM>
            <postCardForM></postCardForM>
          </div>
        </el-col>
      </el-row>
    </div>
</template>

<style scoped>
/* 整个页面的设置 */
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

/* 整个页面的设置 */
/* 走马灯 */
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

/* 走马灯 */
/* 无限滚动列表 */
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

.container {
  background-image: url('../../../../assets/star.png');
  /* 可以添加其他背景图片的样式，比如平铺、大小等 */
  /* background-size: cover; */
  /* background-repeat: no-repeat; */
  /* background-position: center center; */
}

</style>
