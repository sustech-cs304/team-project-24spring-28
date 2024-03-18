<script setup>
import Avatar from "@/components/Modules/avatar/Avatar.vue";

const props = defineProps({
  userId: {
    type: String,
    required: true
  },
  needLevi: {
    type: Boolean,
    default: true
  },
  needSmall: {
    type: Boolean,
    default: false
  },
  sizeSmall: {
    type: String,
    default: '25px'
  },
  sizeMini: {
    type: String,
    default: '38px'
  },
  sizeWrapper: {
    type: String,
    default: '45px'
  },
  name: {
    type: String,
    default: ''
  },
  nameColor: {
    type: String,
    default: '#000000'
  }
})
</script>

<template>
  <div class="person">
    <avatar :user-id="userId"
            :need-levi="needLevi"
            :need-small="needSmall"
            :size-small="sizeSmall"
            :size-mini="sizeMini"
            :size-wrapper="sizeWrapper"/>
    <p class="text" :style="{color: nameColor}">{{name}}</p>
  </div>
</template>

<style scoped>
.person {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.text {
  font-size: 15px;
  font-weight: normal;
  margin-left: 5px;
}
</style>