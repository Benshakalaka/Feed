<template>
  <div class="spliter-main" ref="wrapper">
    <div class="spliter-wrapper">
      <div class="operation-part">
        <transition :name="operationTransitionName">
          <div class="step step-one" v-if="step === 1" key="one">
            <div class="left-part">
              <div class="data-section">
                <label-input
                        labelText="分拨中心图片名称"
                        :errorText="imgError"
                        @change="imgChange"
                        @focus="imgDisplayUrl = (imgUrl || imgDisplayUrl)"
                ></label-input>
                <label-input
                        labelText="填写图片背景名称"
                        :errorText="bgError"
                        @change="bgChange"
                        @focus="imgDisplayUrl = (bgUrl || imgDisplayUrl)"
                ></label-input>
                <label-input
                        labelText="设定片区数量"
                        :errorText="countError"
                        @change="countChange"
                ></label-input>
                <label-input
                        labelText="设定图片宽度"
                        :errorText="widthError"
                        @change="widthChange"
                ></label-input>
              </div>
            </div><!--
            --><div class="right-part">
              <div class="pick-section">
                <img class="img-container" :src="imgDisplayUrl" width="100%" v-if="imgDisplayUrl">
              </div>
            </div>
          </div>

          <div class="step step-two" v-if="step === 2" key="two">
            <div class="data-section"></div>
            <div class="pick-section"></div>
          </div>

          <div class="step step-two" v-if="step === 3" key="three">
            <div class="data-section"></div>
            <div class="pick-section"></div>
          </div>

          <div class="step step-two" v-if="step === 4" key="four">
            <div class="data-section"></div>
            <div class="pick-section"></div>
          </div>
        </transition>
      </div>
      <transition name="display-fade">
        <div class="display-data data-one" v-if="step === 1 && count > 0" key="one-d">
          <div class="left-index">
            <ul>
              <li v-for="n in Number(count)" @click="dataIndexData">{{ `片区${n}` }}</li>
            </ul>
          </div><!--
          --><div class="right-data" ref="dataDisplayOne">
            <ul>
              <li v-for="coords in dataDisplayOne">
                <el-input
                  :value='`${coords.left},${coords.top}`'
                  icon="delete"
                  :on-icon-click="displayDataDeleteOne"
                  :name='"data-display-one-" + dataDisplayOne.indexOf(coords)'
                  @focus="displayDataChangeOne"
                  @blur="displayDataBlurOne"
                ></el-input>
              </li>
            </ul>
          </div>
        </div>
      </transition>
      <transition name="display-fade">
        <div class="display-img display-one" :style="{width: `${width}px`}" v-if="step === 1 && width" key="one-p">
          <img class="img-display-container" :src="imgUrl" width="100%" @click="imgClickOne" v-if="imgUrl">
        </div>
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

  .step-one {
    .left-part, .right-part {
      display: inline-block;
      width: 49%;
      height: 100%;
    }

    .left-part {
      border-right: 1px solid #ccc;
      vertical-align: top;
    }

    .data-section {
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      padding: 10px 50px;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
    }

    .pick-section {
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      position: relative;
      display: inline-block;

      img {
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
      }
    }
  }

  .display-img {
    margin: 30px 0;
  }

  .display-data {
    width: 100%;
    background-color: #fff;
    margin-top: 10px;
    border-radius: 5px;
    overflow: auto;
  }

  .data-one {
    $left-index-width: 100px;
    $left-index-li-height: 45px;

    .left-index {
      width: $left-index-width;
      float: left;

      ul {
        list-style: none;
        width: 100%;
        margin: 0;
        padding: 0;
        max-height: $left-index-li-height * 5;
        overflow: auto;
        &::-webkit-scrollbar {/*滚动条整体样式*/
          width: 0;     /*高宽分别对应横竖滚动条的尺寸*/
          height: 0;
        }

        li {
          display: block;
          margin: 0;
          padding: 0;
          text-align: center;
          line-height: $left-index-li-height;
          height: $left-index-li-height;
          box-shadow: 6px 0 10px rgba(0,0,0,.2);
          border-bottom: 1px solid lighten(#ccc, 10%);
          border-right: 1px solid #ccc;

          &.active {
            box-shadow: 0 0 0px;
            border-right: 0;
          }
        }
      }
    }

    .right-data {
      width: calc(100% - #{$left-index-width});
      float: left;
      box-sizing: border-box;
      padding: 10px 15px;

      ul {
        list-style: none;
        width: 100%;
        margin: 0;
        padding: 0;
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;

        li {
          display: block;
          width: 120px;
          height: 36px;
          float: left;
          margin: 5px 15px;
        }
      }
    }
  }

</style>

<script type="text/ecmascript-6">
  import LabelInput from '../../components/labelInput.vue'
  import Vue from 'vue'
  import { Input } from 'element-ui'

  Vue.use(Input)

  export default {
    name: 'image-split',
    data () {
      return {
        step: 1,
        operationTransitionName: '',
        imgUrl: '',
        bgUrl: '',
        imgDisplayUrl: '',
        width: 0,
        count: 0,
        imgError: '',
        bgError: '',
        widthError: '',
        countError: '',
        oneCurrentAreaIndex: -1,
        oneCurrentDataDisplayFocusIndex: -1,
        areasInfo: null
      }
    },
    watch: {
      step (newVal, oldVal) {
        this.operationTransitionName = (newVal > oldVal ? 'right-split-fade' : 'left-split-fade')
      },
      count (newVal) {
        Vue.nextTick(() => {
          this.$refs.dataDisplayOne.style.height = Math.min(newVal, 5) * 45 + 'px'
        })

        if (this.areasInfo) {
          this.areasInfo.length > newVal && this.areasInfo.splice(newVal, this.areasInfo.length - newVal)
          this.areasInfo.length < newVal && Array.from({length: newVal - this.areasInfo.length}).map(() => {
            this.areasInfo.push({
              area_coords: [],
              area_item: [],
              area_active: []
            })
          })
        } else {
          this.areasInfo = Array.from({length: newVal}).map((item, index) => {
            return {
              area_coords: [],
              area_item: [],
              area_active: []
            }
          })
        }
      }
    },
    components: {
      'label-input': LabelInput
    },
    methods: {
      imgChange (event, value) {
        this.imgUrl = (value ? ('../../../static/img/' + value) : '')
        this.imgDisplayUrl = (this.imgUrl || this.bgUrl)
      },
      bgChange (event, value) {
        this.bgUrl = (value ? ('../../../static/img/' + value) : '')
        this.imgDisplayUrl = this.bgUrl || this.imgUrl
      },
      widthChange (event, value) {
        this.width = value
      },
      countChange (event, value) {
        this.count = value
      },
      dataIndexData (event) {
        const li = event.target
        li.classList.add('active')
        Array.from(li.parentElement.children).map(item => {
          li !== item && item.classList.remove('active')
        })
        this.oneCurrentAreaIndex = Array.prototype.indexOf.call(li.parentElement.children, li)
      },
      imgClickOne (event) {
        const coord = {
          left: Number(event.pageX) + Number(this.$refs.wrapper.scrollLeft) - event.target.offsetLeft,
          top: Number(event.pageY) + Number(this.$refs.wrapper.scrollTop) - event.target.offsetTop
        }
        if (this.oneCurrentDataDisplayFocusIndex > -1) {
          this.oneCurrentAreaIndex >= 0 && Vue.set(this.areasInfo[this.oneCurrentAreaIndex].area_coords, this.oneCurrentDataDisplayFocusIndex, coord)
        } else {
          this.oneCurrentAreaIndex >= 0 && this.areasInfo[this.oneCurrentAreaIndex].area_coords.push(coord)
        }
      },
      displayDataDeleteOne (event) {
        let inputName = event.target.nextElementSibling.name
        let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
        this.areasInfo[this.oneCurrentAreaIndex].area_coords.splice(index, 1)
      },
      displayDataChangeOne (event) {
        let inputName = event.target.name
        let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
        this.oneCurrentDataDisplayFocusIndex = index
      },
      displayDataBlurOne (event) {
        const _this = this
        setTimeout(function () {
          _this.oneCurrentDataDisplayFocusIndex = -1
        }, 100)
      }
    },
    computed: {
      dataDisplayOne () {
        return (this.areasInfo && this.areasInfo[this.oneCurrentAreaIndex] ? this.areasInfo[this.oneCurrentAreaIndex].area_coords : [])
      }
    }
  }
</script>
