<script setup>
import { Pointer, Share, StarFilled } from "@element-plus/icons";
import { ChatDotSquare } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { ref, onMounted, onBeforeUnmount } from 'vue';
import InfoBox from "@/components/User/pages/post/components/infoBox.vue";

const router = useRouter();

const ChatDotSquareIcon = ChatDotSquare;
const PointerIcon = Pointer;
const ShareIcon = Share;
const StarFilledIcon = StarFilled;


// const number1 = ref(null);
// const number2 = ref(null);
// const number3 = ref(null);
// const number4 = ref(null);
//
// const adjustFontSize = () => {
//     const numbers = [number1.value, number2.value, number3.value, number4.value];
//     numbers.forEach(number => {
//         const num = parseInt(number.innerText);
//         if (num > 9999) {
//             number.innerText = '9999+';
//         } else {
//             const length = num.toString().length;
//             if (length <= 2) {
//                 number.style.fontSize = '40px';
//             } else if (length <= 3) {
//                 number.style.fontSize = '30px';
//             } else if (length <= 4) {
//                 number.style.fontSize = '20px';
//             }
//         }
//     });
// };

// onMounted(() => {
//     adjustFontSize();
//     window.addEventListener('resize', adjustFontSize);
// });
//
// onBeforeUnmount(() => {
//     window.removeEventListener('resize', adjustFontSize);
// });
function goToPost() {
    // router.push({ path: '/square/post' });

    let url = router.resolve({path: '/square/post'}).href;
    window.open(url, '_blank');
}
</script>

<template>
<!--    <el-card class="card-box" shadow="never">-->
        <el-card style=" max-height: 27vh; margin-bottom: 10px; border-radius: 0.5vw" shadow="hover" @click="goToPost">
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
                        <el-col :span="12">
<!--                            <div class="box">-->
<!--                                <span ref="number1" class="number">12</span>-->
<!--                            </div>-->
                            <info-box :given-number="1" :background="'@/src/assets/Like/like.png'"></info-box>
                        </el-col>
                        <el-col :span="12">
<!--                            <div class="box">-->
<!--                                <span ref="number2" class="number">333</span>-->
<!--                            </div>-->
                            <info-box></info-box>
                        </el-col>
                        <el-col :span="12">
<!--                            <div class="box">-->
<!--                                <span ref="number3" class="number">4444</span>-->
<!--                            </div>-->
                            <info-box :given-number="32456"></info-box>
                        </el-col>
                        <el-col :span="12">
<!--                            <div class="box">-->
<!--                                <span ref="number4" class="number">5555555555</span>-->
<!--                            </div>-->
                            <info-box></info-box>
                        </el-col>
                    </el-row>
<!--                    <el-row>-->
<!--                        <el-col style="display: flex; flex-direction: column; justify-content: center;">-->
<!--                            <el-row style="margin-bottom: 5px">-->
<!--                                <el-button-group>-->
<!--                                    <el-button type="primary" :icon="Pointer" class="button-left">-->
<!--                                        点赞-->
<!--                                    </el-button>-->
<!--                                    <el-button type="primary" plain  class="button-right">-->
<!--                                        10-->
<!--                                    </el-button>-->
<!--                                </el-button-group>-->
<!--                            </el-row>-->
<!--                            <el-row style="margin-bottom: 5px">-->
<!--                                <el-button-group>-->
<!--                                    <el-button type="primary" :icon="StarFilled" class="button-left">-->
<!--                                        收藏-->
<!--                                    </el-button>-->
<!--                                    <el-button type="primary" plain class="button-right">-->
<!--                                        29-->
<!--                                    </el-button>-->
<!--                                </el-button-group>-->
<!--                            </el-row>-->
<!--                            <el-row style="margin-bottom: 5px">-->
<!--                                <el-button-group>-->
<!--                                    <el-button type="primary" :icon="Share" class="button-left">-->
<!--                                        分享-->
<!--                                    </el-button>-->
<!--                                    <el-button type="primary" plain class="button-right">-->
<!--                                        7-->
<!--                                    </el-button>-->
<!--                                </el-button-group>-->
<!--                            </el-row>-->
<!--                            <el-row >-->
<!--                                <el-button-group>-->
<!--                                    <el-button type="primary" :icon="ChatDotSquare" class="button-left">-->
<!--                                        评论-->
<!--                                    </el-button>-->
<!--                                    <el-button type="primary" plain class="button-right">-->
<!--                                        20-->
<!--                                    </el-button>-->
<!--                                </el-button-group>-->
<!--                            </el-row>-->
<!--                        </el-col>-->

<!--                    </el-row>-->

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
.box {
    height: 8vh;
    background-color: #ccc;
    margin: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
}
.number {
    position: absolute;
    color: white;
    font-family: 'Arial Rounded MT Bold', sans-serif;
}
</style>
