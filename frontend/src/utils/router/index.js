import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import Square from "@/components/User/pages/post/postSquare.vue"
import Post from "@/components/User/pages/post/postDetail.vue"

import EventPage from "@/components/User/pages/event/EventPage.vue"
import CreateEvent from "@/components/User/pages/event/CreateEvent.vue";
import ManageEvent from "@/components/User/pages/event/ManageEvent.vue";

import mainPage from "@/components/User/pages/main/mainPage.vue"
import searchPage from "@/components/User/pages/main/searchPage.vue";
import signUp from "@/components/User/pages/main/signUp.vue";
import Profile from "@/components/User/pages/profile/profile.vue";

const routes = [
    {path: '/', component: Login},
    {path: '/signup', component: signUp},
    {path: '/square', component: Square},
    {path: '/square/post', component: Post},

    {path: '/profile', component: Profile},

    {path: '/event', component: EventPage},
    {path: '/event/create', component: CreateEvent},
    {path: '/event/manage', component: ManageEvent},

    {path: '/main', component: mainPage},
    {path: '/search', component: searchPage},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
