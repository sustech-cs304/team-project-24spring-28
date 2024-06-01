<template>
    <el-card style="width: 25vw; max-height: 30vh; margin-bottom: 10px; border-radius: 0.5vw" shadow="hover" @click="goToPost">
        <el-row>
            <el-col :span="24">
                <el-row style="margin-bottom: 10px;">
                    <el-col :span="22">
                        <span class="title">
                            {{ postTitle }}
                        </span>
                    </el-col>
                    <el-col>
                        <event-card :poster="eventSmallImage" :name="eventName"></event-card>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="8">
                        <el-row style="margin-bottom: 5px"></el-row>
                        <el-row>
                            <img :src="postImage" style="width: 6vw"/>
                        </el-row>
                    </el-col>
                    <el-col :span="16">
                        <span class="content">
                            {{ postContent }}
                        </span>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </el-card>
</template>

<script setup>
import { Pointer, Share, StarFilled } from "@element-plus/icons";
import { ChatDotSquare } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { defineProps, ref, onMounted } from "vue";
import axios from 'axios';
import EventCard from "@/components/User/pages/post/components/postDetail/eventCard.vue";

const router = useRouter();

const ChatDotSquareIcon = ChatDotSquare;
const PointerIcon = Pointer;
const ShareIcon = Share;
const StarFilledIcon = StarFilled;

function goToPost() {
    let url = router.resolve({ path: '/square/post' }).href;
    window.open(url, '_blank');
}

const props = defineProps({
    postID: {
        type: String,
        default: '0'
    }
});

// Create refs for post data
const postTitle = ref('Loading...');
const postContent = ref('Loading content...');
const postImage = ref('https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png');
const eventLink = ref('');
const eventSmallImage = ref('');
const eventName = ref('Loading event...');
const eventId = ref('');
const eventBio = ref('');

// Fetch post data from API
onMounted(async () => {
    try {
        const response = await axios.post('/api/post/getFullPost', { postID: props.postID });
        const data = response.data;

        // Update refs with fetched data
        postTitle.value = data.title;
        postContent.value = data.content;
        postImage.value = data.image;
        eventLink.value = data.eventLink;
        eventSmallImage.value = data.eventSmallImage;
        eventName.value = data.eventName;
        eventId.value = data.eventId;
        eventBio.value = data.eventBio;
        console.log(response.data)
    } catch (error) {
        console.error('Error fetching post data:', error);
    }
});
</script>

<style scoped>
.card-box{
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
