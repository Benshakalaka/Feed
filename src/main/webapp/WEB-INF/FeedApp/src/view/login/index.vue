<template>
  <div class="login-section">
    <div class="card" @click="signFormChange">
      <transition name="sign-fade">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
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
      min-height: 420px;
      margin: 10% auto auto auto;
      opacity: .9;
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
    },
    methods: {
      signFormChange (event) {
        if (event.target === this.$el.getElementsByClassName('card')[0]) {
          let redirectRouter = (this.$route.name === 'signin' ? 'signup' : 'signin')
          this.$router.replace({name: redirectRouter})
        }
      }
    }
  }
</script>
