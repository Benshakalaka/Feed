<template>
  <div class="login-section">
    <div class="card">
      <transition>
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .login-section {
    height: 100%;
    width: 100%;
    overflow: auto;
    
    .card {
      width: 65%;
      height: 60%;
      min-width: 950px;
      margin: 10% auto auto auto;
      opacity: .6;
      padding: 0 35px;
      background: url("../../../static/unsplash_1.jpeg") no-repeat center;
    }
  }
</style>

<script type="text/ecmascript-6">
  export default {
    name: 'login',
    data () {
      return {
      }
    },
    components: {
    }
  }
</script>
