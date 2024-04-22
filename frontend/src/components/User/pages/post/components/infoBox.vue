<script setup>
import {ref, onMounted, onBeforeUnmount, defineProps} from 'vue';

const props = defineProps({
    background: {
        type: String,
        default: '@/assets/Like/star.png'
    },
    givenNumber: {
        type: Number,
        default: '1234'
    }

});
const num = ref(null);
const adjustFontSize = () => {
    const NUM = parseInt(num.value.innerText);
    if (NUM > 9999) {
        num.value.innerText = '9999+';
    } else {
        const length = NUM.toString().length;
        if (length <= 2) {
            num.value.style.fontSize = '40px';
        } else if (length <= 3) {
            num.value.style.fontSize = '30px';
        } else if (length <= 4) {
            num.value.style.fontSize = '20px';
        }
    }
};

onMounted(() => {
    adjustFontSize();
    window.addEventListener('resize', adjustFontSize);
});

onBeforeUnmount(() => {
    window.removeEventListener('resize', adjustFontSize);
});
</script>

<template>
    <div class="box" :style="{ backgroundImage: `url(${props.background})` }">
        <span ref="num" class="number">{{props.givenNumber}}</span>
    </div>
</template>

<style scoped>
.box {
    height: 8vh;
    //background-color: #ccc;
    margin: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    border-radius: 10px;
    background-size: auto;
}
.number {
    position: absolute;
    color: white;
    font-family: 'Arial Rounded MT Bold', sans-serif;
}
</style>
