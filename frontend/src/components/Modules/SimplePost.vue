<script setup>
import { Pointer, Share, StarFilled } from "@element-plus/icons";
import { ChatDotSquare } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import InfoBox from "@/components/User/pages/post/components/infoBox.vue";
import EventCard from "@/components/User/pages/post/components/postDetail/eventCard.vue";
import {defineProps} from "vue";

const router = useRouter();

const ChatDotSquareIcon = ChatDotSquare;
const PointerIcon = Pointer;
const ShareIcon = Share;
const StarFilledIcon = StarFilled;

function goToPost() {
    // router.push({ path: '/square/post' });

    let url = router.resolve({path: '/square/post'}).href;
    window.open(url, '_blank');
}

const props = defineProps({
    link: { // 帖子的链接
        type: String,
        default: '/square/post'
    },
    title: { // 帖子的标题
        type: String,
        default: 'Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title'
    },
    content: { // 帖子的内容
        type: String,
        default: 'content content content content content content content content'
    },
    image: { // 帖子的图片
        type: String,
        default: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png'
    },
    eventLink: { // 帖子关联的活动的链接
        type: String,
        default: '/event'
    },
    eventSmallImage: { // 帖子关联的活动的缩略图
        type: String,
        default: ''
    },
    eventName: { // 帖子关联的活动名
        type: String,
        default: 'Default Event Name'
    },
    eventId: {
        type: String,
        default: '666666'
    },
    eventBio: {
        type: String,
        default: 'no bio'
    }

});
</script>

<template>
    <!--    <el-card class="card-box" shadow="never">-->
    <el-card style="width: 25vw; max-height: 30vh; margin-bottom: 10px; border-radius: 0.5vw" shadow="hover" @click="goToPost">
        <!--            <template #header>Yummy hamburger</template>-->
        <el-row>
            <el-col :span="24">
                <el-row style="margin-bottom: 10px;">
                    <el-col :span="22">
                            <span class="title">
                                {{props.title}}
                            </span>
                    </el-col>
                    <el-col>
                        <event-card poster="" :name="props.eventName"></event-card>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="8">
                        <el-row style="margin-bottom: 5px">
<!--                            <el-tag type="primary">Event</el-tag>-->
                        </el-row>
                        <el-row>
                            <img
                                src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                style="width: 6vw"/>
                        </el-row>
                    </el-col>
                    <el-col :span="16">
                    <span class="content">
                        {{ props.content }}
                    </span>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </el-card>

    <el-row>

    </el-row>
</template>

<style scoped>
.card-box{
//border: none;
    padding: 0 !important;

    margin: 0 !important;
}
.button-left {
    width: 100px;
}
.button-right {
    width: 50px;
}
.title {
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    font-weight: bold;
    font-size: 20px;
}
.box {
    height: 8vh;
    background-color: #ccc;
    margin: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
}
.number {
    position: absolute;
    color: white;
    font-family: 'Arial Rounded MT Bold', sans-serif;
}
</style>
