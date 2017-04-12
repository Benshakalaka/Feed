<template>
  <div class="spliter-main" id="spliter-main">
    <div class="spliter-wrapper">
      <div class="operation-part">
        <transition :name="operationTransitionName">
          <component :is="topComponent"></component>
        </transition>
      </div>
      <ul class="operation-step">
        <li
          v-for="i in step"
          class="operation-step-hint"
          :style="{'background-color': stepColor[i-1], width: 100 / step + '%'}"
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

<style lang="scss" rel="stylesheet/scss">
  @import '../../common/styles/variables';

  $operation-height: 350px;

  .spliter-main {
    width: 100%;
    height: 100%;
    overflow: auto;
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

      &::before, &::after {
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
      }
      
      &::before {
        content: '\f100';
        float: left;
        margin-left: -60px;
        box-shadow: 4px 0 6px rgba(0,0,0,.2);
      }

      &::after {
        content: '\f101';
        float: right;
        margin-right: -60px;
        box-shadow: -4px 0 6px rgba(0,0,0,.2);
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
      }
    }
  }

  .right-split-fade-enter-active, .right-split-fade-leave-active,
  .left-split-fade-enter-active, .left-split-fade-leave-active,
  .display-fade-enter-active, .display-fade-leave-active {
    transition: all .5s ease-in;
  }

  .right-split-fade-enter, .left-split-fade-leave-active {
    transform: translateX(-1200px);
    opacity: 0;
  }

  .right-split-fade-leave-active, .left-split-fade-enter {
    transform: translateX(1200px);
    opacity: 0;
  }

  .display-fade-enter, .display-fade-leave-active {
    opacity: 0;
  }

  .step {
    height: 100%;
    float: left;
    width: 100%;
  }

  .display-img {
    margin: 20px 0;
  }

  .display-data {
    width: 100%;
    background-color: #fff;
    margin-top: 20px;
    border-radius: 5px;
    overflow: auto;
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters } from 'vuex'
  import OneTopComp from './step_one/operationTable.vue'
  import OneMiddleComp from './step_one/dataDisplay.vue'
  import OneBottomComp from './step_one/imgDisplay.vue'

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
      'one-bottom-comp': OneBottomComp
    }
  }
</script>
