<template>
  <div class="spliter-main" id="spliter-main">
    <div class="spliter-wrapper">
      <div class="operation-part">
        <a href="javascript:void(0);" class="left-nav" @click="prevNavGo"><i class="fa fa-angle-double-left fa-1x"></i></a>
        <a href="javascript:void(0);" class="right-nav" @click="nextNavGo"><i class="fa fa-angle-double-right fa-1x"></i></a>
        <transition :name="operationTransitionName">
          <component :is="topComponent"></component>
        </transition>
      </div>
      <ul class="operation-step">
        <li
          v-for="i in maxStep"
          class="operation-step-hint"
          :style="{
            'background-color': stepColor[i-1],
            width: (i <= step ? (100 / step + '%') : 0)
          }"
        ></li>
      </ul>
      <transition name="display-fade">
        <component :is="middleComponent"></component>
      </transition>
      <transition name="display-fade">
        <component :is="bottomComponent"></component>
      </transition>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  @import '../../common/styles/variables';

  $operation-height: 350px;

  .spliter-main {
    width: 100%;
    height: 100%;
    overflow: auto;

    &::-webkit-scrollbar {/*滚动条整体样式*/
      width: 0;     /*高宽分别对应横竖滚动条的尺寸*/
      height: 0;
    }
  }

  .spliter-wrapper {
    margin: 58px auto;
    width: $content-width;

    .operation-part {
      position: relative;
      box-sizing: border-box;
      padding: 10px 60px;
      width: 100%;
      height: $operation-height;
      background-color: #fff;
      overflow: hidden;

      a {
        font: normal normal normal 14px/1 FontAwesome;
        text-rendering: auto;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        font-size: 2em;
        width: 60px;
        height: $operation-height;
        line-height: $operation-height;
        text-align: center;
        opacity: .4;
        display: inline-block;
        margin-top: -10px;
        cursor: pointer;
        transition: opacity .3s ease-in;
        color: darken(#ccc, 30%);

        &:hover {
          opacity: .8;
        }
      }

      .left-nav {
        float: left;
        margin-left: -60px;
        box-shadow: 4px 0 6px rgba(0,0,0,.2);
      }

      .right-nav {
        float: right;
        margin-right: -60px;
        box-shadow: -4px 0 6px rgba(0,0,0,.2);
      }

      // transition组件切换时两个组件同时存在, 会导致一个被换行, 这里使第二个组件强行与第一个在同一行
      // 使得动画效果更贴切
      > div:nth-of-type(2) {
        margin-top: -330px;
      }
    }

    .operation-step {
      width: 100%;
      height: 8px;
      list-style: none;
      padding: 0;
      margin: 0;

      .operation-step-hint {
        height: 100%;
        float: left;
        transition: width .8s ease-in;
      }
    }
  }

  .right-split-fade-enter-active, .right-split-fade-leave-active,
  .left-split-fade-enter-active, .left-split-fade-leave-active,
  .display-fade-enter-active, .display-fade-leave-active {
    transition: transform .8s ease-in, opacity .8s ease-in;
  }

  .right-split-fade-enter, .left-split-fade-leave-active {
    transform: translateX(1200px);
    opacity: 0;
  }

  .right-split-fade-leave-active, .left-split-fade-enter {
    transform: translateX(-1200px);
    opacity: 0;
  }

  .display-fade-enter, .display-fade-leave-active {
    opacity: 0;
  }

  .step {
    height: 100%;
    width: 100%;
    float: left;
  }

  .display-img {
    overflow: auto;
  }

  .display-data {
    width: 100%;
    background-color: #fff;
    margin: 20px 0;
    border-radius: 5px;
    overflow: auto;
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import { getScrollbarWidth } from '../../common/js'
  import * as types from '../../store/mutation-types'

  import OneTopComp from './step_one/operationTable.vue'
  import OneMiddleComp from './step_one/dataDisplay.vue'
  import OneBottomComp from './step_one/imgDisplay.vue'

  import TwoTopComp from './step_two/operationTable.vue'
  import TwoMiddleComp from './step_two/dataDisplay.vue'
  import TwoBottomComp from './step_two/imgDisplay.vue'

  export default {
    name: 'image-split',
    data () {
      return {
        operationTransitionName: '',
        componentArray: ['one', 'two', 'three', 'four']
      }
    },
    watch: {
      step (newVal, oldVal) {
        this.operationTransitionName = (newVal > oldVal ? 'right-split-fade' : 'left-split-fade')
      }
    },
    computed: {
      ...mapGetters([
        'maxStep',
        'stepColor',
        'step'
      ]),
      topComponent () {
        return this.componentArray[this.step - 1] + '-top-comp'
      },
      middleComponent () {
        return this.componentArray[this.step - 1] + '-middle-comp'
      },
      bottomComponent () {
        return this.componentArray[this.step - 1] + '-bottom-comp'
      }
    },
    components: {
      'one-top-comp': OneTopComp,
      'one-middle-comp': OneMiddleComp,
      'one-bottom-comp': OneBottomComp,
      'two-top-comp': TwoTopComp,
      'two-middle-comp': TwoMiddleComp,
      'two-bottom-comp': TwoBottomComp
    },
    methods: {
      ...mapMutations({
        'nav_prev_go': types.NAV_PREV_GO,
        'nav_next_go': types.NAV_NEXT_GO,
        'set_scroll_width': types.SET_SCROLL_WIDTH
      }),
      prevNavGo () {
        this.nav_prev_go()
      },
      nextNavGo () {
        this.nav_next_go()
      }
    },
    mounted () {
      this.set_scroll_width({
        scrollBarWidth: getScrollbarWidth()
      })
    }
  }
</script>
