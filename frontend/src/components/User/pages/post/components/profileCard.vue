<template>
    <div class="card-container">
        <el-button-group >
            <el-button class="profile-avatar">
                <div class="block">
                    <el-avatar shape="square" :size="40" :src="props.avatar" />
                </div>
            </el-button>
            <el-button  class="profile-name">
                <p>{{ props.name }}</p>
            </el-button>
        </el-button-group>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    avatar: {
        type: String,
        default: 'squareUrl' // 设置头像的默认值
    },
    name: {
        type: String,
        default: 'John Doe' // 设置名字的默认值
    },
    //对应人物页面
    link: {
        type: String
    }

});
</script>

<style scoped>
.profile-avatar{
    border: none;
    height: 45px;
    width: 45px;
}
.profile-name{
    border: none;
    height: 45px;
}
</style>
