<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import 'normalize.css'

  export default {
    name: 'app'
  }
</script>

<style lang="scss" rel="stylesheet/scss">
  html, body {
    width: 100%;
    height: 100%;
  }

  #app {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }
</style>
