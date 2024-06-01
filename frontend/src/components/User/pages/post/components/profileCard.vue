<template>
    <el-popover
        :width="300"
        popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
        placement="bottom-start"
        :show-arrow="false"
        offset="0"
    >
        <template #reference>
            <div class="card-container">
                <el-button-group >
                    <el-button class="profile-avatar">
                        <div class="block">
                            <el-avatar :size="40" :src="props.avatar" />
                        </div>
                    </el-button>
                    <el-button  class="profile-name">
                        <p>{{ props.name }}</p>
                    </el-button>
                </el-button-group>
            </div>
        </template>
        <template #default>
            <div
                class="demo-rich-conent"
                style="display: flex; gap: 16px; flex-direction: column"
            >
                <el-avatar
                    :size="60"
                    src="{{props.avatar}}"
                    style="margin-bottom: 8px"
                />
                <div>
                    <p
                        class="demo-rich-content__name"
                        style="margin: 0; font-weight: 500"
                    >
                        {{props.name}}
                    </p>
                    <p
                        class="demo-rich-content__mention"
                        style="margin: 0; font-size: 14px; color: var(--el-color-info)"
                    >
                        @{{props.id}}
                    </p>
                </div>

                <p class="demo-rich-content__desc" style="margin: 0">
                    {{props.bio}}
                </p>
            </div>
        </template>
    </el-popover>

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
    id: {
        type: String,
        default: '666666'
    },
    bio: {
        type: String,
        default: 'Have no bio yet.'
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
