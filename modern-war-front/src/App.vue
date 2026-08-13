<template>
  <router-view />
</template>

<script setup lang="ts">
// 根组件
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body, #app {
  width: 100%;
  height: 100%;
  overflow: hidden;
  font-family: 'Microsoft YaHei', 'PingFang SC', sans-serif;
}

#app {
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
}
</style>
