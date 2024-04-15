<script setup>
import PostComments from "@/components/User/pages/post/components/postDetail/postComments.vue";
import ProfileCard from "@/components/User/pages/post/components/profileCard.vue";
import {ArrowLeft, ArrowRight, MoreFilled, Open, Pointer, Share, StarFilled} from "@element-plus/icons";
import {ChatDotSquare} from "@element-plus/icons-vue";
import Comment from '@/components/Modules/comment/Comment.vue';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import {ref} from 'vue';
import EventCard from "@/components/User/pages/post/components/postDetail/eventCard.vue";
// variable
let name = "ggb"

let text = "# 民族音乐学导论（音乐人类学）\n" +
    "\n" +
    "## 期末报告\n" +
    "\n" +
    "选一个中国乐种，运用课堂所学的知识对该音乐乐种的音乐特征进行陈述，并从社会文化的角度对该音乐现象进行分析……\n" +
    "\n" +
    "## Week2 Fieldwork\n" +
    "\n" +
    "<u>*“田野工作，实地考察”*</u>\n" +
    "\n" +
    "> ## Features\n" +
    ">\n" +
    "> - Informant(资料提供者，当地向导)\n" +
    "> - Performance\n" +
    "> - Interview\n" +
    "> - Documentation(整理文件，编辑文件)\n" +
    "> - Recordings\n" +
    "\n" +
    "#### 人类学的早期指导\n" +
    "\n" +
    "归纳与演绎，参与性观察，思维开放与自我批评\n" +
    "\n" +
    "主位与客观 (emic自我的/etic他者的, insider/outsider)\n" +
    "\n" +
    "- Ⅰ选择课题\n" +
    "\n" +
    "- Ⅱ可行性(语言、政治、学术贡献、档案资料、时间、钱、疫情)\n" +
    "\n" +
    "- Ⅲ伦理\n" +
    "\n" +
    "#### 二十世纪以来的田野采风与二十世纪之前的殖民者的田野采风有何区别？\n" +
    "\n" +
    "\n" +
    "\n" +
    "### 参与性观察 (participate-observation)\n" +
    "\n" +
    "民族音乐学者的参与：\n" +
    "\n" +
    "1. 参与者的观察，观察活动公开\n" +
    "2. 尊重东道主的文化，参与与观察的平衡\n" +
    "\n" +
    "\n" +
    "\n" +
    "- 需要一年时间(信任)\n" +
    "\n" +
    "- 工作的伦理：反对无形的入侵(如暗中录音)\n" +
    "- 民族音乐学的双音乐性(bi-musicality 不同文化的音乐体系不同)\n" +
    "\n" +
    "\n" +
    "\n" +
    "前期的资料搜集\n" +
    "\n" +
    "选择乐于接受你的社区\n" +
    "\n" +
    "带上大学或机构相关证明文件\n" +
    "\n" +
    "礼节性地拜访政府官员或文化部门\n" +
    "\n" +
    "准备回答一些无法回避的问题\n" +
    "\n" +
    "文化震惊和生活震惊(如男女限制)\n" +
    "\n" +
    "\n" +
    "\n" +
    "#### 访谈\n" +
    "\n" +
    "- 引导性谈话（非正式采访）\n" +
    "- 半结构式开放性访谈（鼓励资料提供者拓展话题）\n" +
    "- 完全有结构的访谈（访谈者遵循书面提纲，控制谈话的节奏和方向）\n" +
    "- 访谈技巧\n" +
    "- 保存访谈录音\n" +
    "\n"

// Post
function likePost() {

}

function collectPost() {

}

function sharePost() {

}

function commentPost() {

}

// comment
function likeComment() {

}

function shareComment() {

}

const isStarred = ref(false);
const isLiked = ref(false);
const toggleStar = () => {
    // 切换收藏状态
    isStarred.value = !isStarred.value;
}
const toggleLike = () => {
    // 切换点赞状态
    isLiked.value = !isLiked.value;
}

const activeNames = ref(['contentFold'])
const handleChange = (val) => {
    console.log(val)
}
const toggleCollapse = () => {
    // 切换折叠面板的展开状态
    activeNames.value = activeNames.value.length ? [] : ['contentFold'];
}
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
                                            <el-col :span="3">
                                                <profile-card :name="name"></profile-card>
                                            </el-col>
                                            <!--activity-->
                                            <el-col :span="4"/>
                                            <el-col :span="8">
                                                <event-card :name="'sdfsldfjsldfkjdsflkjsalfdfsldfjsldfkjdsflkjsalf'"></event-card>
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
                                <el-card style="">
                                    <el-collapse v-model="activeNames">
                                        <el-collapse-item title="Content" name="contentFold">
                                            <v-md-preview :text="text"></v-md-preview>
                                        </el-collapse-item>
                                    </el-collapse>

                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row>

                        </el-row>
                        <el-row>
                            <!--comment-->
                            <el-col>
                                <!--                                <post-comments>-->

                                <!--                                </post-comments>-->
                                <comment>

                                </comment>
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
                            <div v-show="!isLiked">
                                <el-button type="primary" :icon="Pointer" @click="toggleLike" class="button-left"
                                           style="width: 100%;" plain/>
                            </div>
                            <div v-show="isLiked">
                                <el-button type="primary" :icon="Pointer" @click="toggleLike" class="button-left"
                                           style="width: 100%;"/>
                            </div>
                        </el-col>

                        <el-col style="margin-bottom: 4px">
                            <div v-show="!isStarred">
                                <el-button type="primary" :icon="StarFilled" @click="toggleStar" class="button-left"
                                           style="width: 100%;" plain/>
                            </div>
                            <div v-show="isStarred">
                                <el-button type="primary" :icon="StarFilled" @click="toggleStar" class="button-left"
                                           style="width: 100%;"/>
                            </div>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                                <el-button type="primary" :icon="Share" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ChatDotSquare" class="button-left" style="width: 100%;"
                                       plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ArrowLeft" class="button-left" style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="ArrowRight" class="button-left" style="width: 100%;"
                                       plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="Open" @click="toggleCollapse" class="button-left"
                                       style="width: 100%;" plain/>
                        </el-col>
                        <el-col style="margin-bottom: 4px">
                            <el-button type="primary" :icon="MoreFilled" class="button-left" style="width: 100%;"
                                       plain/>
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
/* 弹出提示 */
.el-popper.is-customized .el-popper__content {
    /* 设置渐变色作为背景 */
    background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
    /* Set padding to ensure the height is 32px */
    padding: 6px 12px;
}

.el-popper.is-customized .el-popper__arrow::before {
    /* 设置箭头的渐变色 */
    background: linear-gradient(45deg, #b2e68d, #bce689);
    right: 0;
}

/* 弹出提示 */

</style>
