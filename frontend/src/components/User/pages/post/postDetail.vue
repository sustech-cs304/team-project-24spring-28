<script setup>


</script>

<template>
    <div class="common-layout-all">
        <el-row :class="main-header">
            header
            <el-backtop :right="10" :bottom="10" />
        </el-row>
        <el-row :class="main-main" gutter="10">
            <el-col :span="3">
                aside
            </el-col>
            <el-col :span="15">
                <!--subheader-->
                <el-affix offset="10">
                    <el-row :justify="space-between">
                        <el-col>
                            <el-card>
                                <el-row>
                                    <el-col :span="4">
                                        <el-button-group class="ml-4">
                                            <el-button type="primary" :icon="Edit"/>
                                            <el-button type="primary" :icon="Share"/>
                                            <el-button type="primary" :icon="Delete"/>
                                        </el-button-group>
                                    </el-col>
                                    <el-col :span="20">
                                        <el-button :icon="Search" round style="width: 100%">Search</el-button>
                                    </el-col>
                                </el-row>
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
</style>
