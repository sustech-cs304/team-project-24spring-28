<script setup>
const props = defineProps({
  title: String,
  imgUrl: String,
  x: Number,
  y: Number
});

</script>

<template>
  <div class="infoBox" :style="{position: 'absolute', left: x + 'px', top: y + 'px'}">
    <p>{{title}}</p>
    <el-image style="width: 200px; height: 150px" :src="imgUrl"></el-image>
  </div>
</template>

<style scoped>
.infoBox{
  text-align: center;
  font-weight: bold;
  border: #077EB1 1px solid;
  border-radius: 10px;
  background-color: white;
  z-index: 1000;
}

</style>