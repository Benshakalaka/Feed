<template>
  <div class="index-page">
    <header-bar></header-bar>
    <transition>
      <router-view style="height:100%;"></router-view>
    </transition>
  </div>
</template>

<style type="text/scss" lang="scss">
  .index-page {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
</style>

<script type="text/ecmascript-6">
  import HeaderBar from '../../components/headerBar.vue'

  export default {
    name: 'home',
    data () {
      return {
      }
    },
    components: {
      'header-bar': HeaderBar
    }
  }
</script>
