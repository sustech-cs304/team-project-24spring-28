<script setup>
import { ref } from 'vue'
import postCard from '@/components/User/pages/post/components/postsSquare/postCard.vue'
import {
    ArrowLeft,
    ArrowRight,
    Delete,
    Edit,
    Search,
    Share,
} from "@element-plus/icons";
import MarkdownEdit from "@/components/User/pages/post/components/postsSquare/markdownEdit.vue";
import SimplePost from "@/components/Modules/SimplePost.vue";

const count = ref(0)
const editDialogVisible = ref(false)

const load = () => {
    count.value += 10
}

const handleEditPost = () => {
    editDialogVisible.value = true;
}
const markdownText = ref('')
</script>

<template>
    <el-dialog
        v-model="editDialogVisible"
        title="Start mine!"
        width="1300"
        :before-close="handleClose"
    >
<!--        <span>This is a message</span>-->
        <markdown-edit v-model="markdownText"></markdown-edit>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="editDialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="editDialogVisible = false">
                    Confirm
                </el-button>
            </div>
        </template>
    </el-dialog>


    <div class="common-layout-all">
        <el-row :class="main-header">
            header
            <el-backtop :right="10" :bottom="10" />
        </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="3">
                aside
            </el-col>
            <el-col :span="15">
                <!--subheader-->
                <el-row :justify="space-between">
                    <el-col>
                        <el-card>
                            <el-row>
                                <el-col :span="4">
                                    <el-button-group class="ml-4">
                                        <el-button type="primary" :icon="Edit" @click="handleEditPost"/>
                                        <el-button type="primary" :icon="Share"/>
                                        <el-button type="primary" :icon="Delete"/>
                                    </el-button-group>
                                </el-col>
                                <el-col :span="20">
                                    <el-button :icon="Search" round style="width: 100%">Search</el-button>
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
                <!--main-->
                <el-row>
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
                <!--翻页-->
                <el-row>
                    <el-col>
                        <el-affix offset="5" position="bottom">
                            <el-card style="border: none; display: flex; justify-content: center; align-items: center;"
                                     shadow="never">
                                <el-pagination background layout="prev, pager, next" :total="1000"/>
                            </el-card>
                        </el-affix>
                    </el-col>
                </el-row>
            </el-col>
            <!--aside-right-->
            <el-col :span="6">
                <el-affix :offset="10">
                    <el-row gutter="10">
                        <el-col>
                            <el-card>
                                <div class="common-layout" style="height: 20vh">
                                    <el-container>
                                        <el-header :height="10">Announcement</el-header>
                                        <el-main>

                                        </el-main>
                                    </el-container>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-card style="border-radius: 0.5vw">
                                <el-carousel height="30vh" motion-blur interval="6000">
                                    <el-carousel-item v-for="item in 4" :key="item">
                                        <h3 class="small justify-center" text="2xl">{{ item }}</h3>
                                    </el-carousel-item>
                                </el-carousel>
                            </el-card>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <simple-post></simple-post>
                        </el-col>
                    </el-row>
                </el-affix>
            </el-col>
        </el-row>
        <el-row :class="main-footer">
            footer

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

</style>
