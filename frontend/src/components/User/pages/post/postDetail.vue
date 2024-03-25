<script setup>


import PostComments from "@/components/User/pages/post/components/postDetail/postComments.vue";
import ProfileCard from "@/components/User/pages/post/components/profileCard.vue";
import {MoreFilled, Pointer, Share, StarFilled} from "@element-plus/icons";
import {ChatDotSquare} from "@element-plus/icons-vue";

let name = "ggb"
</script>

<template>
    <div class="common-layout-all">
        <el-row :class="main-header">
            header
            <el-backtop :right="10" :bottom="10"/>
        </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="3">
                aside
            </el-col>
            <el-col :span="20">
                <!--subheader-->
                <el-affix offset="10">
                    <el-row :justify="space-between">

                        <el-col>
                            <el-card>
                                <el-row>
                                    <el-col>
                                        <span class="Title">
                                Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title
                                        </span>
                                    </el-col>
                                    <el-divider/>
                                    <el-col>
                                        <el-row>
                                            <!--profile-->
                                            <el-col :span="8">
                                                <profile-card :name="name"></profile-card>
                                            </el-col>
                                            <!--activity-->
                                            <el-col :span="8">

                                            </el-col>

                                        </el-row>
                                    </el-col>
                                </el-row>
                                <!--                                <el-row>-->
                                <!--                                    <el-col :span="4">-->

                                <!--                                    </el-col>-->
                                <!--                                </el-row>-->
                            </el-card>
                        </el-col>
                    </el-row>
                </el-affix>
                <!--main-->
                <el-row>
                    <el-col>
                        <el-row>
                            <!--gap-->
                        </el-row>
                        <el-row>
                            <!--theme-->
                            <el-col :span="24">
                                <el-card style="height: 1000px">

                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row>

                        </el-row>
                        <el-row>
                            <!--comment-->
                            <el-col>
                                <post-comments>

                                </post-comments>
                                <el-card style="height: 1000px">

                                </el-card>
                                <el-card>

                                </el-card>
                                <el-card>

                                </el-card>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <!--buttons-->
                <el-row>

                </el-row>
            </el-col>
            <!--aside-right-->
            <el-col :span="1">
                <el-affix :offset="10">
                    <el-row gutter="10">
                        <el-col style="margin-bottom: 4px">
                            <el-button type="info" class="button-left" style="width: 100%;">?</el-button>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="Pointer" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="StarFilled" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="Share" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ChatDotSquare" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 3px">
                            <el-button type="primary" :icon="MoreFilled" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-card style="border-radius: 0.5vw">
                                <el-carousel height="30vh" motion-blur interval="6000">
                                    <el-carousel-item v-for="item in 4" :key="item">
                                        <h3 class="small justify-center" text="2xl">{{ item }}</h3>
                                    </el-carousel-item>
                                </el-carousel>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-affix>
            </el-col>
        </el-row>
        <el-row :class="main-footer">
            footer

        </el-row>
    </div>
</template>

<style scoped>
/* 整个页面的设置 */
.common-layout-all {
    height: 100vh;
}

.common-layout {
    height: 80vh;
}

.el-row {
    border-radius: 0.5vw;
    margin-bottom: 20px;
}

.el-row:last-child {
    margin-bottom: 0;
}

.el-col {
    border-radius: 0.5vw;
}

.Title {
    font-weight: bold;
    font-size: 20px;
}

.thumb-button {
    height: 40px;
}

/* 整个页面的设置 */
</style>
