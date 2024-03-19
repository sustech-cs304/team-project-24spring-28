<script>
import {ref} from 'vue'
import postCard from '@/components/User/pages/post/components/postsGround/postCard.vue'
import {ArrowLeft,
    ArrowRight,
    Delete,
    Edit,
    Share,} from "@element-plus/icons";
const count = ref(0)
const load = () => {
    count.value += 10
}
export default {
    computed: {
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
        postCard
    }
}
</script>

<template>
    <div class="common-layout-all">
        <el-container>
            <el-header>Header</el-header>
            <el-container>
                <el-aside width="10vw">Aside</el-aside>
                <el-container>
                    <el-main>
                        <div class="common-layout">
                            <el-container>
                                <el-header>
                                    <!--                                    subheader-->
                                    <el-card>
                                        <el-row :gutter="1">
                                            <el-col :span="6">
                                                <el-button-group>
                                                    <el-button type="primary" :icon="ArrowLeft">Previous Page</el-button>
                                                    <el-button type="primary">
                                                        Next Page
                                                        <el-icon class="el-icon--right">
                                                            <ArrowRight/>
                                                        </el-icon>
                                                    </el-button>
                                                </el-button-group>
                                            </el-col>
                                            <el-col :span="6">
                                                <el-button-group class="ml-4">
                                                    <el-button type="primary" :icon="Edit"/>
                                                    <el-button type="primary" :icon="Share"/>
                                                    <el-button type="primary" :icon="Delete"/>
                                                </el-button-group>
                                            </el-col>
                                            <el-col :span="4">
                                                <el-button type="primary">搜索</el-button>
                                            </el-col>
                                        </el-row>
                                    </el-card>
                                </el-header>
                                <!-- 主内容 -->
                                <el-main>
                                    <el-row :gutter="10">
                                        <el-col :span="17">
                                            <el-row >
                                                <el-col :span="24">
                                                    <div>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                        <postCard></postCard>
                                                    </div>
                                                </el-col>
                                            </el-row>
                                            <el-row>
                                                <el-col>
                                                    <el-card style="border: none; display: flex; justify-content: center; align-items: center;" shadow="never">
                                                        <el-pagination background layout="prev, pager, next" :total="1000"/>
                                                    </el-card>
                                                </el-col>
                                            </el-row>
                                        </el-col>
                                        <el-col :span="7">
                                            <el-affix :offset="145">
                                                <el-card style="border-radius: 0.5vw" shadow="hover">
                                                    <el-carousel height="200px" motion-blur interval="6000">
                                                        <el-carousel-item v-for="item in 4" :key="item">
                                                            <h3 class="small justify-center" text="2xl">{{ item }}</h3>
                                                        </el-carousel-item>
                                                    </el-carousel>
                                                </el-card>
                                            </el-affix>

                                        </el-col>
                                    </el-row>
                                </el-main>


                            </el-container>
                        </div>
                    </el-main>
<!--                    <el-footer>Footer</el-footer>-->
                </el-container>
            </el-container>
        </el-container>
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
    border-radius: 4px;
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

</style>
