<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import 'normalize.css'
  import Vue from 'vue'
  import * as filters from './common/filter'

  Object.keys(filters).map(item => {
    Vue.filter(item, filters[item])
  })

  export default {
    name: 'app'
  }
</script>

<style type="text/scss" lang="scss">
  @import "./common/styles/common";

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
    background-color: darken(#f5f8fa, 13%);
  }
</style>
