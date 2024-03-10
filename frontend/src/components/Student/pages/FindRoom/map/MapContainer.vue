<script setup>
import AMapLoader from '@amap/amap-jsapi-loader'
import {onMounted, ref} from "vue";
import infoBox from "@/components/Student/pages/FindRoom/map/infoBox.vue";

let map = null;

let showInfoBox = ref(false);
let infoBoxTitle = ref('');
let infoBoxImgUrl = ref('');
let infoBoxX = ref(0);
let infoBoxY = ref(0);

const emit = defineEmits(['selectedZoning'])

onMounted(()=> {
  AMapLoader.load({
    "key": "69757c9f29e2ac635579456fe95e3ce1",
    "version": "1.4.15",
    "plugins": [],
    "AMapUI": {
      "version": '1.1',
      "plugins": [],
    },
    "Loca": {
      "version": '1.3.2'
    },
  }).then((AMap) => {
    let bound = new AMap.Bounds([113.995, 22.593105], [114.01, 22.609141]);

    map = new AMap.Map('container', {
      resizeEnable: true,
      rotateEnable: true,
      pitchEnable: true,
      zoom: 17,
      zoomRange: [16, 18],
      center: [114.000110, 22.603],
      viewMode:'3D',
      buildingAnimation: true,
      pitch: is3D.value ? 60 : 0,
    });


    map.setLimitBounds(bound);
    // let clickHandler = function(e) {
    //   alert('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');
    // };
    // map.on('click', clickHandler);

    new AMap.Marker({
      position: [113.999709,22.602238 ],
      map: map,
    }).on('click', function() {
      emit('selectedZoning', '二期')
    }).on('mouseover', function(e) {
      showInfoBox.value = true;
      infoBoxTitle.value = '二期';
      infoBoxImgUrl.value = 'https://www.sznews.com/news/pic/2019-12/09/f25ba53a-1a97-42e2-be22-fc9ef4543c6b.jpg';
      let pixel = map.lngLatToContainer(e.lnglat);
      infoBoxX.value = pixel.getX();
      infoBoxY.value = pixel.getY();
    }).on('mouseout', function() {
      showInfoBox.value = false;
    })

    new AMap.Marker({
      position: [ 113.998653,22.600013],
      map: map,
    }).on('click', function() {
      emit('selectedZoning', '湖畔')
    }).on('mouseover', function(e) {
      showInfoBox.value = true;
      infoBoxTitle.value = '湖畔';
      infoBoxImgUrl.value = 'https://sustc.wiki/images/thumb/c/c2/%E4%BB%8E%E8%BF%8E%E6%96%B0%E6%A1%A5%E6%89%80%E8%A7%81%E7%9A%84%E6%B9%96%E7%95%94.jpg/1200px-%E4%BB%8E%E8%BF%8E%E6%96%B0%E6%A1%A5%E6%89%80%E8%A7%81%E7%9A%84%E6%B9%96%E7%95%94.jpg';
      let pixel = map.lngLatToContainer(e.lnglat);
      infoBoxX.value = pixel.getX();
      infoBoxY.value = pixel.getY();
    }).on('mouseout', function() {
      showInfoBox.value = false;
    })

    new AMap.Marker({
      position: [114.000722,22.604493],
      map: map,
    }).on('click', function() {
      emit('selectedZoning', '荔园')
    }).on('mouseover', function(e) {
      showInfoBox.value = true;
      infoBoxTitle.value = '荔园';
      infoBoxImgUrl.value = 'https://sustc.wiki/images/c/ce/%E8%8D%94%E5%9B%AD%E9%97%A8%E5%8F%A3.jpg';
      let pixel = map.lngLatToContainer(e.lnglat);
      infoBoxX.value = pixel.getX();
      infoBoxY.value = pixel.getY();
    }).on('mouseout', function() {
      showInfoBox.value = false;
    })

    new AMap.Marker({
      position: [114.002209,22.608659],
      map: map,
    }).on('click', function() {
      emit('selectedZoning', '欣园')
    }).on('mouseover', function(e) {
      showInfoBox.value = true;
      infoBoxTitle.value = '欣园';
      infoBoxImgUrl.value = 'https://www.sznews.com/news/pic/2019-12/09/f25ba53a-1a97-42e2-be22-fc9ef4543c6b.jpg';
      let pixel = map.lngLatToContainer(e.lnglat);
      infoBoxX.value = pixel.getX();
      infoBoxY.value = pixel.getY();
    }).on('mouseout', function() {
      showInfoBox.value = false;
    })

    map.plugin(["AMap.Geolocation"], function () {
      map.addControl(new AMap.Geolocation()); //定位当前位置插件； 用来获取和展示用户主机所在的经纬度位置；
    });


  }).catch(e => {
    console.log(e);
  })
})

let is3D = ref(false);

// 切换地图视图模式的函数
function toggle3D() {
  map.setPitch(is3D.value ? 60 : 0);
}



</script>

<template>
  <div id="container" class="mapContainer">
    <info-box v-if="showInfoBox" :title="infoBoxTitle" :imgUrl="infoBoxImgUrl" :x="infoBoxX" :y="infoBoxY"></info-box>
    <div class="checkbox-wrapper-10" style="margin: 10px">
      <input type="checkbox" id="cb5" class="tgl tgl-flip" v-model="is3D" @change="toggle3D">
      <label for="cb5" data-tg-on="3D" data-tg-off="2D" class="tgl-btn" style="z-index: 10000"></label>
    </div>
  </div>
</template>

<style scoped>
.mapContainer{
  height: 100%;
  width: 100%;
  border-radius: 10px;
  border: #ff93c9;
}

.checkbox-wrapper-10 .tgl {
  display: none;
}

.checkbox-wrapper-10 .tgl,
.checkbox-wrapper-10 .tgl:after,
.checkbox-wrapper-10 .tgl:before,
.checkbox-wrapper-10 .tgl *,
.checkbox-wrapper-10 .tgl *:after,
.checkbox-wrapper-10 .tgl *:before,
.checkbox-wrapper-10 .tgl + .tgl-btn {
  box-sizing: border-box;
}

.checkbox-wrapper-10 .tgl::-moz-selection,
.checkbox-wrapper-10 .tgl:after::-moz-selection,
.checkbox-wrapper-10 .tgl:before::-moz-selection,
.checkbox-wrapper-10 .tgl *::-moz-selection,
.checkbox-wrapper-10 .tgl *:after::-moz-selection,
.checkbox-wrapper-10 .tgl *:before::-moz-selection,
.checkbox-wrapper-10 .tgl + .tgl-btn::-moz-selection,
.checkbox-wrapper-10 .tgl::selection,
.checkbox-wrapper-10 .tgl:after::selection,
.checkbox-wrapper-10 .tgl:before::selection,
.checkbox-wrapper-10 .tgl *::selection,
.checkbox-wrapper-10 .tgl *:after::selection,
.checkbox-wrapper-10 .tgl *:before::selection,
.checkbox-wrapper-10 .tgl + .tgl-btn::selection {
  background: none;
}

.checkbox-wrapper-10 .tgl + .tgl-btn {
  outline: 0;
  display: block;
  width: 4em;
  height: 2em;
  position: relative;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.checkbox-wrapper-10 .tgl + .tgl-btn:after,
.checkbox-wrapper-10 .tgl + .tgl-btn:before {
  position: relative;
  display: block;
  content: "";
  width: 50%;
  height: 100%;
}

.checkbox-wrapper-10 .tgl + .tgl-btn:after {
  left: 0;
}

.checkbox-wrapper-10 .tgl + .tgl-btn:before {
  display: none;
}

.checkbox-wrapper-10 .tgl:checked + .tgl-btn:after {
  left: 50%;
}

.checkbox-wrapper-10 .tgl-flip + .tgl-btn {
  padding: 2px;
  transition: all 0.2s ease;
  font-family: sans-serif;
  perspective: 100px;
}

.checkbox-wrapper-10 .tgl-flip + .tgl-btn:after,
.checkbox-wrapper-10 .tgl-flip + .tgl-btn:before {
  display: inline-block;
  transition: all 0.4s ease;
  width: 100%;
  text-align: center;
  position: absolute;
  line-height: 2em;
  font-weight: bold;
  color: #fff;
  top: 0;
  left: 0;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 4px;
}

.checkbox-wrapper-10 .tgl-flip + .tgl-btn:after {
  content: attr(data-tg-on);
  background: #02C66F;
  transform: rotateY(-180deg);
}

.checkbox-wrapper-10 .tgl-flip + .tgl-btn:before {
  background: #FF3A19;
  content: attr(data-tg-off);
}

.checkbox-wrapper-10 .tgl-flip + .tgl-btn:active:before {
  transform: rotateY(-20deg);
}

.checkbox-wrapper-10 .tgl-flip:checked + .tgl-btn:before {
  transform: rotateY(180deg);
}

.checkbox-wrapper-10 .tgl-flip:checked + .tgl-btn:after {
  transform: rotateY(0);
  left: 0;
  background: #7FC6A6;
}

.checkbox-wrapper-10 .tgl-flip:checked + .tgl-btn:active:after {
  transform: rotateY(20deg);
}
</style>