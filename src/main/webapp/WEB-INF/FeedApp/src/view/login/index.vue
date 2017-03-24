<template>
  <div class="login-section">
    <div class="card" @click="signFormChange">
      <transition :name="formSwitchTransitionName" 
        @before-enter="beforeEnterTransitionEndHandler"
        @after-enter="enterTransitionEndHandler" 
        >
        <keep-alive>
          <router-view :transitionEnd="isTransitionEnd"></router-view>
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
      min-height: 430px;
      margin: 10% auto auto auto;
      opacity: .9;
      padding: 0 35px;
      background: url("../../../static/unsplash_1.jpeg") no-repeat center;
      position: relative;

      perspective: 3000px;

      .sign-fade-left-leave-active, .sign-fade-right-leave-active, .sign-fade-left-enter-active, .sign-fade-right-enter-active {
        transition: transform .5s cubic-bezier(0.640, -0.165, 0.480, 1.240), opacity .4s ease;
        transform-style: preserve-3d;
      }

      .sign-fade-left-enter-active, .sign-fade-right-enter-active {
        transition-delay: .1s;
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

      .switch-text {
        color: #fff;
        letter-spacing: 2px;
      }
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
        switchRightText: '',
        isTransitionEnd: true
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
      },
      enterTransitionEndHandler () {
        this.isTransitionEnd = true
      },
      beforeEnterTransitionEndHandler () {
        this.isTransitionEnd = false
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
