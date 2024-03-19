<script setup>

</script>

<template>
<!--    <el-card class="card-box" shadow="never">-->
        <el-card style="max-width: 60vw; max-height: 30vh; margin-bottom: 10px; border-radius: 0.5vw" shadow="hover">
            <template #header>Yummy hamburger</template>
            <el-row>
                <el-col :span="8">
                    <img
                        src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                        style="width: 5vw"/>
                </el-col>
                <el-col :span="16">
                    <p>
                        content content content content content content content content
                    </p>
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
</style>
