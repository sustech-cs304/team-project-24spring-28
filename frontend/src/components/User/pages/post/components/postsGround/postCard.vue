<script>

import {Pointer, Share, StarFilled} from "@element-plus/icons";
import {defineComponent} from "vue";
import {ChatDotSquare} from "@element-plus/icons-vue";

export default defineComponent({
    computed: {
        ChatDotSquare() {
            return ChatDotSquare
        },
        Pointer() {
            return Pointer
        },
        Share() {
            return Share
        },
        StarFilled() {
            return StarFilled
        }
    }
})

</script>

<template>
<!--    <el-card class="card-box" shadow="never">-->
        <el-card style=" max-height: 27vh; margin-bottom: 10px; border-radius: 0.5vw" shadow="hover">
<!--            <template #header>Yummy hamburger</template>-->
            <el-row>
                <el-col :span="20">
                    <el-row style="margin-bottom: 10px;">
                        <el-col :span="22">
                            <span class="title">
                                Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title
                            </span>
                        </el-col>
                    </el-row>
                    <el-row :gutter="5">
                        <el-col :span="8">
                            <el-row style="margin-bottom: 5px">
                                <el-tag type="primary">Event</el-tag>
                            </el-row>
                            <el-row>
                                <img
                                    src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                    style="width: 6vw"/>
                            </el-row>
                        </el-col>
                        <el-col :span="16">
                    <span class="content">
                        content content content content content content content content
                        content content content content content content content content
                        content content content content content content content content
                        content content content content content content content content
                        content content content content content content content content
                        content content content content content content content content
                    </span>
                        </el-col>
                    </el-row>
                </el-col>
                <el-col :span="4">
                    <el-row>
                        <el-col style="display: flex; flex-direction: column; justify-content: center;">
                            <el-row style="margin-bottom: 5px">
                                <el-button-group>
                                    <el-button type="primary" :icon="Pointer" class="button-left">
                                        点赞
                                    </el-button>
                                    <el-button type="primary" plain  class="button-right">
                                        10
                                    </el-button>
                                </el-button-group>
                            </el-row>
                            <el-row style="margin-bottom: 5px">
                                <el-button-group>
                                    <el-button type="primary" :icon="StarFilled" class="button-left">
                                        收藏
                                    </el-button>
                                    <el-button type="primary" plain class="button-right">
                                        29
                                    </el-button>
                                </el-button-group>
                            </el-row>
                            <el-row style="margin-bottom: 5px">
                                <el-button-group>
                                    <el-button type="primary" :icon="Share" class="button-left">
                                        分享
                                    </el-button>
                                    <el-button type="primary" plain class="button-right">
                                        7
                                    </el-button>
                                </el-button-group>
                            </el-row>
                            <el-row >
                                <el-button-group>
                                    <el-button type="primary" :icon="ChatDotSquare" class="button-left">
                                        评论
                                    </el-button>
                                    <el-button type="primary" plain class="button-right">
                                        20
                                    </el-button>
                                </el-button-group>
                            </el-row>
                        </el-col>

                    </el-row>
                    <el-row>

                    </el-row>
                    <el-row>

                    </el-row>
                </el-col>
            </el-row>
        </el-card>
<!--    </el-card>-->
    <el-row>

    </el-row>
</template>

<style scoped>
.card-box{
    //border: none;
    padding: 0 !important;

    margin: 0 !important;
}
.button-left {
    width: 100px;
}
.button-right {
    width: 50px;
}
.title {
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    font-weight: bold;
    font-size: 20px;
}

</style>
