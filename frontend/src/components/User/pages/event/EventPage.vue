<script setup>
import { ref } from 'vue'
import VMdEditor from '@kangc/v-md-editor'

let text = ref('')

text = 'sdf\n' +
    '### Title\n' +
    '\n' +
    '![Description](https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg){{{width="200" height="auto"}}}'

</script>

<template>
  <v-md-editor v-model="text"></v-md-editor>
</template>

<style scoped>

</style>