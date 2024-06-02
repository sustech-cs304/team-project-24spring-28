import axiosInstance from "@/utils/axios";
import {ref} from 'vue'

export async function getBriefEvent(id) {
    const event = ref([])
    await axiosInstance.get('/event/brief'
        , {
            params: {
                id: id
            }
        }).then(response => {
        event.value = response.data.data;
        }
    ).catch(error => {
        console.error(error);
    });
    return event.value
}

export async function getEvent(id) {
    const event = ref([])
    await axiosInstance.get('/event/brief'
        , {
            params: {
                postID: id
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
