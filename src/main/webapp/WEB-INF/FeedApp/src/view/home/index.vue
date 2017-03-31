<template>
  <div>
    <header-bar></header-bar>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .home-container {

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
