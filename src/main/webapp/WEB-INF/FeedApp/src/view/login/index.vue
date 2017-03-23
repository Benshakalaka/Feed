<template>
  <div class="login-section">
    <div class="switch-text switch-text-left" v-if="switchLeftText">
      {{ switchLeftText }}<router-link :to="{name:'signup'}">注册</router-link>
    </div>
    <div class="card" @click="signFormChange">
      <transition :name="formSwitchTransitionName">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </transition>
    </div>
    <div class="switch-text switch-text-right" v-if="switchRightText">
      {{ switchRightText }}<router-link :to="{name:'signin'}">登录</router-link>
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
      min-height: 430px;
      margin: 10% auto auto auto;
      opacity: .9;
      padding: 0 35px;
      background: url("../../../static/unsplash_1.jpeg") no-repeat center;

      perspective: 3000px;

      .sign-fade-left-leave-active, .sign-fade-right-leave-active, .sign-fade-left-enter-active, .sign-fade-right-enter-active {
        transition: transform .6s cubic-bezier(0.640, -0.165, 0.480, 1.240), opacity .5s ease;
        transform-style: preserve-3d;
      }

      .sign-fade-left-leave-active, .sign-fade-right-leave-active {
        opacity: 0;
      }

      .sign-fade-left-leave-active {
        transform: rotateY(-180deg) translateZ(250px) scale(.5);
      }

      .sign-fade-left-enter {
        transform: rotateY(180deg) translateZ(250px) scale(.5);
        opacity: 0;
      }

      .sign-fade-right-enter {
        transform: rotateY(-180deg) translateZ(250px) scale(.5);
        opacity: 0;
      }

      .sign-fade-right-leave-active {
        transform: rotateY(180deg) translateZ(250px) scale(.5);
      }
    }

    .switch-text {

    }

    .switch-text-left {
      float: right;
      margin-right: 30px;
    }
  }
</style>

<script type="text/ecmascript-6">
  export default {
    name: 'login',
    data () {
      return {
        formSwitchTransitionName: 'sign-fade-left',
        switchLeftText: '',
        switchRightText: ''
      }
    },
    components: {
    },
    created () {
      if (this.$route.name === 'signup') {
      }
    },
    methods: {
      signFormChange (event) {
        if (event.target === this.$el.getElementsByClassName('card')[0]) {
          let redirectRouter = (this.$route.name === 'signin' ? 'signup' : 'signin')
          this.$router.replace({name: redirectRouter})
        }
      }
    },
    computed: {

    },
    beforeRouteUpdate (to, from, next) {
      if (from.name === 'signin') {
        this.formSwitchTransitionName = 'sign-fade-left'
        this.switchRightText = '还没有账号？立即'
        this.switchLeftText = ''
      } else {
        this.formSwitchTransitionName = 'sign-fade-right'
        this.switchLeftText = '已经有账号了？点击'
        this.switchRightText = ''
      }

      next()
    }
  }
</script>
