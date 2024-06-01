<script setup>
import PostComments from "@/components/User/pages/post/components/postDetail/postComments.vue";
import ProfileCard from "@/components/User/pages/post/components/profileCard.vue";
import { ArrowLeft, ArrowRight, MoreFilled, Open, Pointer, Share, StarFilled } from "@element-plus/icons";
import { ChatDotSquare } from "@element-plus/icons-vue";
import Comment from '@/components/Modules/comment/Comment.vue';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import { ref, onMounted } from 'vue';
import EventCard from "@/components/User/pages/post/components/postDetail/eventCard.vue";
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
import AvatarWithName from "@/components/Modules/avatar/AvatarWithName.vue";
import axios from 'axios';
import {useRoute} from "vue-router";

const route = useRoute(); // 初始化 route

// API call to get post data
const postID = route.query.id; // Example post ID
const postData = ref({});
const userData = ref({});
const eventData = ref({});

onMounted(async () => {
    try {
        const response = await axios.post('/api/post/getFullPost', { postID });
        postData.value = response.data;
        userData.value = {
            userID: response.data.userID,
            username: response.data.username,
            userBio: response.data.userBio,
            userAvatar: response.data.userAvatar,
        };
        eventData.value = {
            eventID: response.data.postRelevantEventID,
            // Add other event data here if necessary
        };
    } catch (error) {
        console.error('Failed to fetch post data:', error);
    }
});

// Right side buttons functionality
const isStarred = ref(false);
const isLiked = ref(false);
const toggleStar = () => {
    isStarred.value = !isStarred.value;
};
const toggleLike = () => {
    isLiked.value = !isLiked.value;
};

const commentSectionRef = ref(null);
const scrollToCommentSection = () => {
    commentSectionRef.value.$el.scrollIntoView({ behavior: 'smooth' });
};

const activeNames = ref(['contentFold']);
const handleChange = (val) => {
    console.log(val);
};
const toggleCollapse = () => {
    activeNames.value = activeNames.value.length ? [] : ['contentFold'];
};
</script>

<template>
    <div class="common-layout-all">
        <el-row :class="main-header">
            <el-col :span="24">
                <header-for-all />
            </el-col>

            <el-backtop :right="10" :bottom="10" />
        </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="3">
                aside
            </el-col>
            <el-col :span="20">
                <!--subheader-->
                <el-affix offset="10">
                    <el-row :justify="space-between">
                        <el-col>
                            <el-card>
                                <el-row>
                                    <el-col>
                                        <span class="Title">{{ postData.postTitle }}</span>
                                    </el-col>
                                    <el-divider />
                                    <el-col>
                                        <el-row>
                                            <!--profile-->
                                            <el-col :span="3">
                                                <profile-card :name="name"></profile-card>
                                                <AvatarWithName :user-id="userData.userID" :name="userData.username" :avatar="userData.userAvatar"></AvatarWithName>
                                            </el-col>
                                            <!--activity-->
                                            <el-col :span="4" />
                                            <el-col :span="8">
                                                <event-card :name="eventData.eventID"></event-card>
                                            </el-col>
                                        </el-row>
                                    </el-col>
                                </el-row>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-affix>
                <!--main-->
                <el-row>
                    <el-col>
                        <el-row>
                            <!--gap-->
                        </el-row>
                        <el-row>
                            <!--theme-->
                            <el-col :span="24">
                                <el-card>
                                    <el-collapse v-model="activeNames">
                                        <el-collapse-item title="Content" name="contentFold">
                                            <v-md-preview :text="postData.postContent"></v-md-preview>
                                        </el-collapse-item>
                                    </el-collapse>
                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row></el-row>
                        <el-row>
                            <!--comment-->
                            <el-col>
                                <comment ref="commentSection" />
                                <el-card style="height: 1000px"></el-card>
                                <el-card></el-card>
                                <el-card></el-card>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <!--buttons-->
                <el-row></el-row>
            </el-col>
            <!--aside-right-->
            <el-col :span="1">
                <el-affix :offset="10">
                    <el-row gutter="10">
                        <el-col style="margin-bottom: 4px">
                            <el-button type="info" class="button-left" style="width: 100%;">?</el-button>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <div v-show="!isLiked">
                                <el-button type="primary" :icon="Pointer" @click="toggleLike" class="button-left" style="width: 100%;" plain />
                            </div>
                            <div v-show="isLiked">
                                <el-button type="primary" :icon="Pointer" @click="toggleLike" class="button-left" style="width: 100%;" />
                            </div>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <div v-show="!isStarred">
                                <el-button type="primary" :icon="StarFilled" @click="toggleStar" class="button-left" style="width: 100%;" plain />
                            </div>
                            <div v-show="isStarred">
                                <el-button type="primary" :icon="StarFilled" @click="toggleStar" class="button-left" style="width: 100%;" />
                            </div>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="Share" class="button-left" style="width: 100%;" plain />
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ChatDotSquare" class="button-left" style="width: 100%;" plain @click="scrollToCommentSection" />
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ArrowLeft" class="button-left" style="width: 100%;" plain />
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ArrowRight" class="button-left" style="width: 100%;" plain />
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="Open" @click="toggleCollapse" class="button-left" style="width: 100%;" plain />
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="MoreFilled" class="button-left" style="width: 100%;" plain />
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

.Title {
    font-weight: bold;
    font-size: 20px;
}

.thumb-button {
    height: 40px;
}

/* 整个页面的设置 */
/* 弹出提示 */
.el-popper.is-customized .el-popper__content {
    /* 设置渐变色作为背景 */
    background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
    /* Set padding to ensure the height is 32px */
    padding: 6px 12px;
}

.el-popper.is-customized .el-popper__arrow::before {
    /* 设置箭头的渐变色 */
    background: linear-gradient(45deg, #b2e68d, #bce689);
    right: 0;
}

/* 弹出提示 */
</style>
