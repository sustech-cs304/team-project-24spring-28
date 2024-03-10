<script setup>
import {useRoute, useRouter} from "vue-router";
import {reactive, ref} from "vue";
import {onMounted} from "vue";
import HeaderBar from "@/components/Student/HeaderBar.vue";
import axiosInstance from "@/utils/axios";
import TeamPart from "@/components/Student/pages/StudentEnd/team/TeamPart.vue";
import BulletinPart from "@/components/Student/pages/StudentEnd/bulletin/BulletinPart.vue";
import OtherPart from "@/components/Student/pages/StudentEnd/other/OtherPart.vue";

const router = useRouter()
const route = useRoute()

onMounted(() => {

})

</script>

<template>
  <HeaderBar/>

  <div class="wrap">
    <team-part/>
    <bulletin-part/>
    <other-part/>
  </div>


</template>

<style scoped>


.wrap {
  display: flex;
  flex-direction: row;
}

</style>