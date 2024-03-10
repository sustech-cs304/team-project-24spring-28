<script setup>
import {useRoute, useRouter} from "vue-router";
import {reactive, ref, onMounted} from "vue";
import axiosInstance from "@/utils/axios";

const router = useRouter()
const route = useRoute()
const prop = defineProps(['hasTeam', 'myTeam'])
const emit = defineEmits(['submit'])
function submitForm(formName) {
  axiosInstance.post('/student/team', prop.myTeam)
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.error(error);
      });
  emit('submit')
}
</script>

<template>
  <el-form>
    <h1> Team info </h1>
    <el-form-item label="Team Name" prop="teamName">
      <el-input v-model="myTeam.name"></el-input>
    </el-form-item>
    <el-form-item label="Team Description" prop="teamDescription">
      <el-input type="textarea" autosize v-model="myTeam.description"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button v-if="!hasTeam" type="primary" @click="submitForm('form')">Create</el-button>
      <el-button v-else type="primary" @click="submitForm('form')">Update</el-button>
    </el-form-item>
  </el-form>

</template>

<style scoped>
  .el-form-item {
    margin-top: 20px;
    align-self: center;
  }
  h1 {
    text-align: center;
  }
</style>