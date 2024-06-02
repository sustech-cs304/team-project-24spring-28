<script setup>
import {ref} from 'vue'
import { ElLoading } from 'element-plus'
import postCard from '@/components/User/pages/post/components/postsSquare/postCard.vue'
import {
    ArrowLeft,
    ArrowRight,
    Delete,
    Edit,
    Search,
    Share,
} from "@element-plus/icons";
// import MarkdownEdit from "@/components/User/pages/post/components/postsSquare/markdownEdit.vue";
import SimplePost from "@/components/Modules/SimplePost.vue";
import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';
import HeaderForAll from "@/components/Modules/HeaderForAll.vue";

VMdEditor.Codemirror = Codemirror;
VMdEditor.use(githubTheme, {
    Hljs: hljs,
});


const count = ref(0)
const fullscreenLoading = ref(false)

const editDialogVisible = ref(false)
const shareDialogVisible = ref(false)
const imageDialogVisible = ref(false)


const load = () => {
    count.value += 10
}

const handleEditPost = () => {
    editDialogVisible.value = true;
    imageDialogVisible.value = false;
}
const handleSharePost = () => {
    shareDialogVisible.value = true;
}

const handleImageUpload = () => {
    imageDialogVisible.value = true;
    editDialogVisible.value = false;
}

const postUpload = () => {
    fullscreenLoading.value = true
    setTimeout(() => {
        fullscreenLoading.value = false
    }, 2000)
    imageDialogVisible.value = false;
    editDialogVisible.value = false;
}

const handleUploadImage=(event, insertImage, files)=>{
    // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
    console.log(files);

    // 此处只做示例
    insertImage({
        url:
            'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1269952892,3525182336&fm=26&gp=0.jpg',
        desc: '七龙珠',
        // width: 'auto',
        // height: 'auto',
    });
}

const fullScreenLoading = () => {

}

const markdownText = ref('')

</script>

<template>
    <el-dialog
        v-model="shareDialogVisible"
        title="Share Post"
        width="30%"
    >
        <!-- Share Dialog Content -->
        <span>This is the content of the share dialog.</span>
        <!-- Footer Buttons -->
        <template #footer>
            <el-button @click="shareDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="shareDialogVisible = false">Confirm</el-button>
        </template>
    </el-dialog>

    <el-dialog
        v-model="editDialogVisible"
        title="Start mine!"
        width="1300"
        :before-close="handleClose"
    >
        <div></div>
        <v-md-editor v-model="markdownText" height="400px"
                     :disabled-menus="[]"
                     @upload-image="handleUploadImage"></v-md-editor>
        <el-dialog
            v-model="imageDialogVisible"
            width="500"
            title="Start mine!"
            append-to-body
        >
            <span>This is the inner Dialog</span>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="handleEditPost">Back</el-button>
                    <el-button type="primary" @click="postUpload"  v-loading.fullscreen.lock="fullscreenLoading">
                        Upload
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="editDialogVisible = false">Back</el-button>
                <el-button type="primary" @click="handleImageUpload">
                    Next
                </el-button>
            </div>
        </template>
    </el-dialog>


    <div class="common-layout-all">
        <el-row >
            <el-col :span="24">
                <header-for-all/>
            </el-col>

            <el-backtop :right="10" :bottom="10"/>
        </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="3">
                aside
            </el-col>
            <el-col :span="15">
                <!--subheader-->
                <el-row :justify="space-between">
                    <el-col>
                        <el-card>
                            <el-row>
                                <el-col :span="4">
                                    <el-button-group class="ml-4">
                                        <el-button type="primary" :icon="Edit" @click="handleEditPost"/>
                                        <el-button type="primary" :icon="Share" @click="handleSharePost"/>
                                        <el-button type="primary" :icon="Delete" @click="postUpload" v-loading.fullscreen.lock="fullscreenLoading"/>
                                    </el-button-group>
                                </el-col>
                                <el-col :span="20">
                                    <el-button :icon="Search" round style="width: 100%">Search</el-button>
                                </el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
                <!--main-->
                <el-row>
                    <el-col :span="24">
                        <div>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                            <postCard></postCard>
                        </div>
                    </el-col>
                </el-row>
                <!--翻页-->
                <el-row>
                    <el-col>
                        <el-affix offset="5" position="bottom">
                            <el-card style="border: none; display: flex; justify-content: center; align-items: center;"
                                     shadow="never">
                                <el-pagination background layout="prev, pager, next" :total="1000"/>
                            </el-card>
                        </el-affix>
                    </el-col>
                </el-row>
            </el-col>
            <!--aside-right-->
            <el-col :span="6">
                <el-affix :offset="10">
                    <el-row gutter="10">
                        <el-col>
                            <el-card>
                                <div class="common-layout" style="height: 20vh">
                                    <el-container>
                                        <el-header :height="10">Announcement</el-header>
                                        <el-main>

                                        </el-main>
                                    </el-container>
                                </div>
                            </el-card>
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
                    <el-row>
                        <el-col>
                            <simple-post></simple-post>
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

/* 整个页面的设置 */
/* 走马灯 */
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}

/* 走马灯 */
/* 无限滚动列表 */
.infinite-list {
    height: 300px;
    padding: 0;
    margin: 0;
    list-style: none;
}

.infinite-list .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    background: var(--el-color-primary-light-9);
    margin: 10px;
    color: var(--el-color-primary);
}

.infinite-list .infinite-list-item + .list-item {
    margin-top: 10px;
}

</style>
