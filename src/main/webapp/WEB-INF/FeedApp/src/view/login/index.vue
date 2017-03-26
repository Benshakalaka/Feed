<template>
  <div class="login-section">
    <div class="card" :style="backgroundStyle" @click="signFormChange" @mousemove.stop="mouseOverHandler" ref="card">
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
      background-repeat: no-repeat;
      position: relative;
      transition: background-position .15s ease;

      perspective: 3000px;

      .sign-fade-left-leave-active,
      .sign-fade-right-leave-active,
      .sign-fade-left-enter-active,
      .sign-fade-right-enter-active {
        transition: transform .5s cubic-bezier(0.640, -0.165, 0.480, 1.240), opacity .5s ease;
        transform-style: preserve-3d;
      }

      .sign-fade-left-enter-active,
      .sign-fade-right-enter-active {
        transition-delay: .1s;
      }

      .sign-fade-left-leave-active,
      .sign-fade-right-leave-active {
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
  import config from '../../config'

  export default {
    name: 'login',
    data () {
      return {
        formSwitchTransitionName: 'sign-fade-left',
        // 登录和注册表单切换动画是否完成, 传递此属性到俩表单组件中去
        // 目前只用于控制表单一侧'切换提示文字', 动画开始就隐藏结束就显现出来
        isTransitionEnd: true,
        backgroundStyle: config.normal
      }
    },
    methods: {
      signFormChange (event) {
      },
      // 下面俩都是改变传递给子组件的动画状态
      enterTransitionEndHandler () {
        this.isTransitionEnd = true
      },
      beforeEnterTransitionEndHandler () {
        this.isTransitionEnd = false
      },
      mouseOverHandler: (() => {
        const positionRange = {
          positionXMax: Number(config.normal.backgroundPositionX.replace('px', '')) + config.normal.offsetMax,
          positionXMin: Number(config.normal.backgroundPositionX.replace('px', '')) - config.normal.offsetMax,
          positionYMax: Number(config.normal.backgroundPositionY.replace('px', '')) + config.normal.offsetMax,
          positionYMin: Number(config.normal.backgroundPositionY.replace('px', '')) - config.normal.offsetMax
        }
        let mouseOverTimeout = null
        let prevX = config.normal.backgroundPositionX
        let prevY = config.normal.backgroundPositionY

        return function (event) {
          const cardEle = this.$refs.card
          if (event.target !== cardEle) {
            return
          }

          const currX = event.clientX
          const currY = event.clientY

          if (mouseOverTimeout === null) {
            let changePosition = (dire, prev, curr) => {
              const direName = 'backgroundPosition' + dire.toUpperCase()
              const style = cardEle.style

              let offset = Math.abs(curr - prev) / 30
              const originPosition = style[direName].replace('px', '')

              let finalPosi = Number(originPosition) + (curr > prev ? -offset : offset)
              let max = positionRange[`position${dire.toUpperCase()}Max`]
              let min = positionRange[`position${dire.toUpperCase()}Min`]

              finalPosi = (finalPosi > max ? max : (finalPosi < min ? min : finalPosi))
              style[direName] = finalPosi + 'px'
            }

            changePosition('x', prevX, currX)
            changePosition('y', prevY, currY)

            prevX = currX
            prevY = currY

            mouseOverTimeout = setTimeout(() => {
              mouseOverTimeout = null
            }, 120)
          }
        }
      })()
    },
    // 此组件内部的route view变化 /login/sign-in 和 /login/sign-up 即触发routeupdate事件
    beforeRouteUpdate (to, from, next) {
      // 更改 transition 的 name, 向左切换或是向右
      // 切换时候总有一个是从 enter 到 enter-active的, 也总有一个是从 leave 到 leave-active的
      this.formSwitchTransitionName = (from.name === 'signup' ? 'sign-fade-right' : 'sign-fade-left')
      next()
    }
  }
</script>
