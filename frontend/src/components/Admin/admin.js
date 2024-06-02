import axiosInstance from "@/utils/axios";
import {ref} from 'vue'

export async function getBriefEvent(id) {
    const event = ref([])
    await axiosInstance.get('/event/brief'
        , {
            params: {
                eventId: id
            }
        }).then(response => {
            event.value = response.data.data;
        }
    ).catch(error => {
        console.error(error);
    });
    return event.value
}

export async function getPost(id) {
    const post = ref([])
    await axiosInstance.get('/event/brief'
        , {
            params: {
                eventId: id
            }
        }).then(response => {
            post.value = response.data.data;
        }
    ).catch(error => {
        console.error(error);
    });
    return post.value
}

export async function getAllEvents() {
    const events = ref([])
    await axiosInstance.get('/event/all'
    ).then(response => {
        events.value = response.data.data;
    }).catch(error => {
        console.error(error);
    });
    return events.value
}

export async function getAllPosts() {
    const posts = ref([])
    await axiosInstance.get('/post/all'
    ).then(response => {
        posts.value = response.data.data;
    }).catch(error => {
        console.error(error);
    });
    return posts.value
}