<script setup>
import {
    ArrowLeft,
    ArrowRight,
    Delete,
    Edit,
    Share,
} from '@element-plus/icons-vue'
</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header>Header</el-header>
            <el-container>
                <el-aside width="200px">Aside</el-aside>
                <el-container>
                    <el-main>
                        <div class="common-layout">
                            <el-container>
                                <el-header>
                                    <!--                                    subheader-->
                                    <el-row :gutter="1">
                                        <el-col :span="6">
                                            <el-button-group>
                                                <el-button type="primary" :icon="ArrowLeft">Previous Page</el-button>
                                                <el-button type="primary">
                                                    Next Page
                                                    <el-icon class="el-icon--right">
                                                        <ArrowRight/>
                                                    </el-icon>
                                                </el-button>
                                            </el-button-group>
                                        </el-col>
                                        <el-col :span="6">
                                            <el-button-group class="ml-4">
                                                <el-button type="primary" :icon="Edit"/>
                                                <el-button type="primary" :icon="Share"/>
                                                <el-button type="primary" :icon="Delete"/>
                                            </el-button-group>
                                        </el-col>
                                    </el-row>
                                </el-header>
                                <el-container>
                                    <el-main>
                                        <el-row>
                                            <el-col :span="18" >
                                                <div class="block text-center">
                                                    <el-carousel height="200px" motion-blur interval="6000">
                                                        <el-carousel-item v-for="item in 4" :key="item">
                                                            <h3 class="small justify-center" text="2xl">{{ item }}</h3>
                                                        </el-carousel-item>
                                                    </el-carousel>
                                                </div>
                                            </el-col>
                                        </el-row>
                                        <el-row>常规</el-row>
                                    </el-main>
                                    <el-aside width="200px">Aside</el-aside>
                                </el-container>
                            </el-container>
                        </div>
                    </el-main>
                    <el-footer>Footer</el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<style scoped>
.demonstration {
    color: var(--el-text-color-secondary);
}

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

</style>
