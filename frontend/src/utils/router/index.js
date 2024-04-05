import {createRouter, createWebHashHistory, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import Square from "@/components/User/pages/post/postSquare.vue"
import Post from "@/components/User/pages/post/postDetail.vue"

import EventPage from "@/components/User/pages/event/EventPage.vue"


// import MessageMain from "@/components/Student/pages/Message/Message.vue";
// import Main from "@/components/Student/Main.vue";
// import FindRoom from "@/components/Student/pages/FindRoom/FindRoom.vue";
// import StudentEnd from "@/components/Student/pages/StudentEnd/StudentEnd.vue";
// import Self from "@/components/Student/pages/Self/Self.vue";
// import Bulletin from "@/components/Student/pages/Bulletin/Bulletin.vue";
// import Admin from "@/components/Admin/Admin.vue";
// import Team from "@/components/Student/pages/Team/Team.vue";
// import detailInfo from "@/components/Student/pages/FindRoom/DetailPage/detailInfo.vue";
// import Invite from "@/components/Student/pages/Invite/Invite.vue";
// import Exchange from "@/components/Student/pages/Exchange/Exchange.vue";


const routes = [
    {path: '/', component: Login},
    {path: '/square', component: Square},
    {path: '/square/post', component: Post},

    {path: '/event', component: EventPage},
    // These are the routes in the old code, you can take them as reference
    // {path: '/main', name: 'main', component: Main},
    // {path: '/student', name: 'student', component: StudentEnd},
    // {path: '/message', name: 'message', component: MessageMain},
    // {path: '/findroom', name: 'findRoom', component: FindRoom},
    // {path: '/self', name: 'self', component: Self},
    // {path: '/bulletin', name: 'bulletin', component: Bulletin},
    // {path: `/detailInfo`, name: 'detailInfo', component: detailInfo},
    // {path: '/bulletin', name: 'bulletin', component: Bulletin},
    // {path: '/admin', name: 'admin', component: Admin},
    // {path: '/team', name: 'team', component: Team},
    // {path: '/invite', name: 'invite', component: Invite},
    // {path: '/exchange', name: 'exchange', component: Exchange},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
