<script setup>
import { ref, reactive } from 'vue'
import VMdEditor from '@kangc/v-md-editor'


let imageUrl = ref('')

let mdText = ref('')

const form = reactive({
  title: '',
  name: '',
  type: '',
  applyStartTime: '',
  applyEndTime: '',
  startTime: '',
  endTime: ''
})

const rules = reactive({
  title: [
    { required: true, message: '请输入活动标题', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入活动名', trigger: 'blur' }
  ],
  type: [
    { required: true, message: '请选择活动类型', trigger: 'change' }
  ],
  applyStartTime: [
    { required: true, message: '请选择报名开始时间', trigger: 'change' }
  ],
  applyEndTime: [
    { required: true, message: '请选择报名结束时间', trigger: 'change' }
  ],
  startTime: [
    { required: true, message: '请选择活动开始时间', trigger: 'change' }
  ],
  endTime: [
    { required: true, message: '请选择活动结束时间', trigger: 'change' }
  ]
})

function beforeUpload(file) {
  console.log(file.name)
  imageUrl.value = URL.createObjectURL(file.raw)
  console.log(imageUrl)
  return false
}

function handleSuccess(response, file, fileList) {
  console.log(response)
  imageUrl.value = URL.createObjectURL(file.raw)
}






let eventTypes = [
  { value: '1', label: '计数式' },
  { value: '2', label: '选座式' },
  { value: '3', label: '自定义报名' },
]

let pickerOptions = {
  shortcuts: [
    {
      text: '今天',
      onClick(picker) {
        picker.$emit('pick', new Date())
      }
    },
    {
      text: '昨天',
      onClick(picker) {
        const date = new Date()
        date.setTime(date.getTime() - 3600 * 1000 * 24)
        picker.$emit('pick', date)
      }
    },
    {
      text: '一周前',
      onClick(picker) {
        const date = new Date()
        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
        picker.$emit('pick', date)
      }
    }
  ]
}

mdText = 'sdf\n' +
    '### Title\n' +
    '\n' +
    '![Description](https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg){{{width="200" height="auto"}}}'

</script>

<template>
  <div>
    <p>Header</p>
  </div>
  <div style="overflow-y: scroll; height: 90vh">
    <div>
      <el-form :model="form" :rules="rules" label-width="120px">
        <el-form-item label="活动标题" prop="title" style="width: 600px">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="活动名" prop="name" style="width: 600px">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="活动类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择活动类型">
            <el-option v-for="item in eventTypes" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="报名开始时间" prop="applyStartTime">
          <el-date-picker
            v-model="form.applyStartTime"
            type="datetime"
            placeholder="选择日期和时间"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名结束时间" prop="applyEndTime">
          <el-date-picker
            v-model="form.applyEndTime"
            type="datetime"
            placeholder="选择日期和时间"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动开始时间" prop="startTime">
          <el-date-picker
            v-model="form.startTime"
            type="datetime"
            placeholder="选择日期和时间"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="endTime">
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            placeholder="选择日期和时间"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="活动封面" style="width: 600px">
          <el-upload
            class="avatar-uploader"
            action=""
            :auto-upload="false"
            :show-file-list="false"
            :on-success="handleSuccess"
            :on-change="beforeUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar"/>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
    </div>


    <div>
      <p style="margin-left: 25px; font-size: 14px; color: #606266"
      >活动正文</p>
    </div>
    <div style="width: 90%; display: flex; flex-direction: row; justify-content: center; margin-left: 50px">
      <v-md-editor v-model="mdText"></v-md-editor>
    </div>

    <div style="margin-top: 30px; display: flex; flex-direction: row; justify-content: center;">
      <el-button type="primary">发起活动</el-button>
    </div>
  </div>
</template>

<style scoped>
/deep/ .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

</style>