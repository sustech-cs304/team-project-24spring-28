<template>
    <div class="bg">
        <el-row :class="main-header">
            <el-col :span="24">
                <header-for-all/>
            </el-col>
        </el-row>
        <el-row :class="main-main" gutter="10">
            <section>
                <div class="sec_new">
                    <el-row>
                        <el-col span="8">
                            <div class="profile">
                                <el-avatar
                                    :size="250"
                                    :src="avatar"
                                    shape="square"
                                    style="margin-bottom: 8px; opacity: 1"
                                />
                                <avatar user-id="{{userID}}"/>
                                <h1>{{ userName }}</h1>
                                <p style="margin: 0; font-size: 14px; color: var(--el-color-info)">@{{ userID }}</p>
                                <p>{{ bio }}</p>
                            </div>
                        </el-col>
                        <el-col span="16">
                            <!-- 这里可以放其他内容 -->
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col span="12">
                            <el-scrollbar :noresize="false" style="width: 70vw">
                                <div class="scrollbar-flex-content">
                                    <SimplePost
                                        v-for="post in posts"
                                        :key="post.id + 'b'"
                                        :link="post.link"
                                        :title="post.title"
                                        :content="post.content"
                                        :image="post.image"
                                        :eventLink="post.eventLink"
                                        :eventSmallImage="post.eventSmallImage"
                                        :eventName="post.eventName"
                                        :eventId="post.eventId"
                                        :eventBio="post.eventBio"
                                    />
                                </div>
                            </el-scrollbar>
                        </el-col>
                        <el-col span="12">
                            <el-scrollbar style="width: 70vw">
                                <div class="scrollbar-flex-content">
                                    <p v-for="item in 50" :key="item" class="scrollbar-demo-item">
                                        {{ item }}
                                    </p>
                                </div>
                            </el-scrollbar>
                        </el-col>
                    </el-row>
                </div>
                <img src="@/components/User/pages/profile/images/stars.png" alt="" id="stars">
                <img src="@/components/User/pages/profile/images/moon.png" alt="" id="moon">
                <img src="@/components/User/pages/profile/images/mountains_behind.png" alt="" id="mountain_behind">
                <div id="text">Moon Light</div>
                <img src="@/components/User/pages/profile/images/mountains_front.png" alt="" id="mountain_front">
            </section>
        </el-row>
        <el-row :class="main-footer">footer</el-row>
    </div>
</template>


<script>
import { ref, onMounted, onBeforeMount } from "vue";
import axios from 'axios';
import SimplePost from "@/components/Modules/SimplePost.vue";
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";
import Avatar from "@/components/old/Student/Avatar.vue";
import { useRoute } from 'vue-router';

export default {
    components: {Avatar, HeaderForAll, SimplePost },
    setup() {
        const route = useRoute();
        const avatar = ref('');
        const userName = ref('');
        const userID = ref('');
        const bio = ref('');
        const posts = ref([]);

        onBeforeMount(() => {
            const userIdFromRoute = route.query.userID;
            if(userIdFromRoute) {
                fetchData(userIdFromRoute);
            } else {
                console.error('User ID not found in the route query parameters.');
            }
        });

        const fetchData = async (userId) => {
            try {
                const profileResponse = await axios.post('/api/profile/info/get', { userId }); // 使用传入的 userId
                const profileData = profileResponse.data;

                avatar.value = profileData.avatar;
                userName.value = profileData.name;
                userID.value = profileData.id;
                bio.value = profileData.bio;

                const postCollectionResponse = await axios.post('/api/profile/postCollection', { userId }); // 使用传入的 userId
                const postCollectionData = postCollectionResponse.data;

                for (const postId of postCollectionData.postIds) {
                    const postResponse = await axios.get(`/api/posts/${postId}`);
                    posts.value.push(postResponse.data);
                }
            } catch (error) {
                console.error('Error fetching profile or posts data:', error);
            }
        };

        return {
            avatar,
            userName,
            userID,
            bio,
            posts
        };
    }
};

</script>




<style scoped>
.id {

}


bg {
    width: 100vw;
    background-color: #000; /* 设置黑色背景 */
    color: #fff; /* 设置文本颜色为白色，以便于对比 */
}
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background: linear-gradient(#2b1055,#7597de);
    min-height: 100vh;

    overflow-x: hidden;
    scroll-behavior: smooth;
}
header{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    padding: 30px 100px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 100;
}
header #logo{
    color: #fff;
    text-decoration: none;
    font-size: 2em;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 2px;
}
header ul{
    display: flex;
    justify-content: center;
    align-items: center;
}
header ul li{
    list-style: none;
    margin-left: 20px;
}
header ul li a{
    text-decoration:none;
    color: #fff;
    padding: 6px 25px;
    border-radius: 20px;
}
header ul li a:hover,
header ul li a.active{
    background-color: #fff;
    color: #2b1065;
}
section{
    position: relative;
    width: 100vw;
    //height: 100vh;
    padding: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    background-color: #000000;
}
section::before{
    content: '';
    position: absolute;
    bottom: 0;
    height: 100px;
    width: 100%;
    background: linear-gradient(to top,#1c0522,transparent);
    z-index: 1000;

}
section img{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    pointer-events: none;
}
section img#moon{
    mix-blend-mode: screen;/*设置背景融合方式*/
}
section img#mountain_front{
    z-index: 15;
}
section #text{
    position: absolute;
    color: #fff;
    font-size: 7.5vw;
    white-space: nowrap;
    z-index: 10;
    transform: translateY(100px);
    right: -350px;
}
section #btn{
    text-decoration: none;
    color: #2b1055;
    padding: 8px 30px;
    border-radius: 20px;
    background-color: #fff;
    font-size: 1.5em;
    z-index: 10;
    display: inline-block;
}
.sec_new{
    position: relative;
    padding: 100px;
    background-color: #cccccc;
    z-index: 200;
    width: 85vw;
    height: 200vh;
    opacity: 0.9;
    border-radius: 10px;
}
.sec{
    position: relative;
    padding: 100px;
    background-color: #1c0522;
    z-index: 200;
}
.sec h2{
    font-size: 3.5em;
    color: #fff;
    margin-bottom: 10px;
}
.sec p{
    color: #fff;
    font-size: 1.2em;
}

.scrollbar-flex-content {
    display: flex;
}
.scrollbar-demo-item {
    flex-shrink: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 50px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-danger-light-9);
    color: var(--el-color-danger);
}
</style>
