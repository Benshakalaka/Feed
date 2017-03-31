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
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }

  #app {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    // background: url(../static/img/background.png) repeat 0 0;
    background-color: #f5f8fa;
  }
</style>
