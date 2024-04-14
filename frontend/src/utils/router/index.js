import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import Square from "@/components/User/pages/post/postSquare.vue"
import Post from "@/components/User/pages/post/postDetail.vue"

import EventPage from "@/components/User/pages/event/EventPage.vue"
import CreateEvent from "@/components/User/pages/event/CreateEvent.vue";
import ManageEvent from "@/components/User/pages/event/ManageEvent.vue";

const routes = [
    {path: '/', component: Login},
    {path: '/square', component: Square},
    {path: '/square/post', component: Post},

    {path: '/event', component: EventPage},
    {path: '/event/create', component: CreateEvent},
    {path: '/event/manage', component: ManageEvent},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
