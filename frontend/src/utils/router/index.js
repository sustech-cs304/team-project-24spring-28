import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import Square from "@/components/User/pages/post/postSquare.vue"
import Post from "@/components/User/pages/post/postDetail.vue"
import Message from "@/components/User/pages/message/Message.vue"

import EventPage from "@/components/User/pages/event/EventPage.vue"
import CreateEvent from "@/components/User/pages/event/CreateEvent.vue";
import ManageEvent from "@/components/User/pages/event/ManageEvent.vue";
import MyEvent from "@/components/User/pages/event/MyEvent.vue";
import ExamplePage from "@/components/Modules/event/ExamplePage.vue";

import mainPage from "@/components/User/pages/main/mainPage.vue"
import searchPage from "@/components/User/pages/main/searchPage.vue";
import signUp from "@/components/User/pages/main/signUp.vue";
import Profile from "@/components/User/pages/profile/profile.vue";
import ProfileExample from "@/components/User/pages/profile/profileExample.vue";

const routes = [
    {path: '/', component: Login},
    {path: '/signup', component: signUp},
    {path: '/square', component: Square},
    {path: '/square/post', component: Post},

    {path: '/profile', component: Profile},
    {path: '/profileTest', component: ProfileExample},

    {path: '/event', component: EventPage},
    {path: '/event/create', component: CreateEvent},
    {path: '/event/manage', component: ManageEvent},
    {path: '/event/cardTest', component: ExamplePage},
    {path: '/event/my', component: MyEvent},

    {path: '/main', component: mainPage},
    {path: '/search', component: searchPage},
    {path: '/message', component: Message}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
