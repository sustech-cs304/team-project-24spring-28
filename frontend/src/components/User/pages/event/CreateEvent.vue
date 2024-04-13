<script setup>
import { ref } from 'vue'
import VMdEditor from '@kangc/v-md-editor'

let form = ref({
  title: '',
  description: '',
  location: '',
  startTime: '',
  endTime: ''
})

let mdText = ref('')

let rules = ref({
  title: [
    { required: true, message: 'Please input the title', trigger: 'blur' }
  ],
  description: [
    { required: true, message: 'Please input the description', trigger: 'blur' }
  ],
  location: [
    { required: true, message: 'Please input the location', trigger: 'blur' }
  ],
  startTime: [
    { required: true, message: 'Please input the start time', trigger: 'blur' }
  ],
  endTime: [
    { required: true, message: 'Please input the end time', trigger: 'blur' }
  ]
})

mdText = 'sdf'

</script>

<template>
  <div>
    <p>Header</p>
  </div>
  <div>
    <div>
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <el-form-item label="Title" prop="title">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="form.description"></el-input>
        </el-form-item>
        <el-form-item label="Location" prop="location">
          <el-input v-model="form.location"></el-input>
        </el-form-item>
        <el-form-item label="Start Time" prop="startTime">
          <el-date-picker
            v-model="form.startTime"
            type="datetime"
            placeholder="Pick a date and time"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="End Time" prop="endTime">
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            placeholder="Pick a date and time"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Create</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <v-md-editor v-model="mdText"></v-md-editor>
    </div>

  </div>
</template>

<style scoped>

</style>