<template>
  <div class="step step-two">
    <div class="top-list">
      <div class="nav-wrapper">
        <ul :style="topNavWrapperWidth"
            @mousewheel.stop.prevent="topNavScroll"
            @DOMMouseScroll.stop.prevent="topNavScroll"
            ref="topNavWrapperEle"
        >
          <li v-for="n in Number(count)"
              @click="dataIndexData"
              :style="topNavItemWidth"
          >
            {{ `片区${n}` }}
            <div class="nav-item-decorate"></div>
          </li>
        </ul>
      </div>
    </div>
    <div class="bottom-data">
      <div class="left-coords">
        <div class="data-item" v-for="dire_index in 4">
          <el-input
            :icon="directionsIcon[dire_index - 1]"
            :value="dataDisplayTwo.length ? `${dataDisplayTwo[dire_index - 1].x},${dataDisplayTwo[dire_index - 1].y}` : ''"
            :readonly="!dataDisplayTwo.length || dire_index === 2 || dire_index === 3"
            :name="'direction-data-' + dire_index"
            :on-icon-click="changerDirectionInputIndex"
            @focus="focusDirectionInput"
            @blur="blurDirectionInput"
            @change="changerDirectionInputData"
          >
            <template slot="prepend">{{ directions[dire_index - 1] }}</template>
          </el-input>
        </div>
      </div>
      <div class="right-attr">
        <div class="data-item">
          <el-input
            :value="currentAreaHeight"
            :readonly="true">
            <template slot="prepend">高度</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            :value="currentAreaWidth"
            :readonly="true">
            <template slot="prepend">宽度</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            :value="currentAreaX"
            :readonly="true">
            <template slot="prepend">BP_X</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            :value="currentAreaY"
            :readonly="true">
            <template slot="prepend">BP_Y</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
                  :value="currentAreaLeft"
                  :readonly="true">
            <template slot="prepend">LEFT</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
                  :value="currentAreaTop"
                  :readonly="true">
            <template slot="prepend">TOP</template>
          </el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .step-two {
    $top-index-li-height: 50px;

    .top-list {
      width: 100%;
      padding: 0 10px;
      box-sizing: border-box;

      .nav-wrapper {
        width: 100%;
        overflow: hidden;

        ul {
          list-style: none;
          margin: 0;
          padding: 0 0 3px 0;
          height: $top-index-li-height;
          transition: margin .2s ease-in;

          li {
            display: block;
            float: left;
            height: $top-index-li-height;
            line-height: $top-index-li-height;
            text-align: center;
            cursor: default;
            position: relative;

            &::before, &::after {
              position: absolute;
              font-size: 20px;
              color: #ccc;
            }

            &:hover::before {
              content: '[';
              left: 25px;
            }

            &:hover::after {
              content: ']';
              right: 25px;
            }

            &.active:hover::before, &.active:hover::after {
              content: '';
            }

            .nav-item-decorate {
              float: left;
              width: 100%;
              height: $top-index-li-height;
              box-sizing: border-box;
              margin-top: -$top-index-li-height;
              border-bottom: 2px solid #ccc;
            }

            $item-border-radius: 15px;

            &.active-left .nav-item-decorate{
              height: $top-index-li-height / 2;
              border-right: 2px solid #ccc;
              margin-top: -$top-index-li-height / 2;
              border-bottom-right-radius: $item-border-radius;
            }

            &.active .nav-item-decorate{
              height: $top-index-li-height / 2;
              border: 2px solid #ccc;
              border-bottom: 0;
              border-top-left-radius: 15px;
              border-top-right-radius: $item-border-radius;
              width: calc(100% + 4px);
              margin-left: -2px;
            }

            &.active-first .nav-item-decorate {
              border-top-left-radius: 0;
              border-left: 0;
            }

            &.active-last .nav-item-decorate {
              border-top-right-radius: 0;
              border-right: 0;
            }

            &.active-right .nav-item-decorate{
              height: $top-index-li-height / 2;
              border-left: 2px solid #ccc;
              margin-top: -$top-index-li-height / 2;
              border-bottom-left-radius: $item-border-radius;
            }
          }
        }
      }
    }

    .bottom-data {
      height: calc(100% - #{$top-index-li-height});
      width: 100%;
      box-sizing: border-box;
      padding: 45px 30px;

      .data-item {
        width: 50%;
        box-sizing: border-box;
        padding: 0 30px;

        display: flex;
        align-items: center;
      }

      .left-coords {
        width: 50%;
        height: 100%;
        float: left;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        border-right: 1px solid #ccc;
        box-sizing: border-box;
        padding-right: 55px;
      }

      .right-attr {
        width: 50%;
        height: 100%;
        float: left;
        display: flex;
        box-sizing: border-box;
        justify-content: space-between;
        flex-wrap: wrap;
        padding-left: 55px;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'
  import Vue from 'vue'
  import { Input } from 'element-ui'

  Vue.use(Input)

  export default {
    data () {
      return {
        // 顶栏导航最多6个
        topMaxNavCount: 6,
        // 顶栏导航每个最大宽度
        topMaxNavWidth: 948 / 6,
        // 顶栏导航鼠标滚轮滚动每次滑动导航项个数
        topNavMoveItemCount: 2,
        // 四个方向
        directions: ['LT', 'RT', 'LB', 'RB'],
        // 四个方向的icon
        directionsIcon: ['edit', '', '', 'edit'],
        // 当前要手动修改方向的坐标值的input(focus触发)
        currentFocusDirectionInput: -1
      }
    },
    computed: {
      ...mapGetters([
        'count',
        'twoCurrentAreaIndex',
        'dataDisplayTwo',
        'twoCurrentDirection',
        'dataDisplayOneInTwo'
      ]),
      // 获取顶部导航wrapper宽度
      topNavWrapperWidth () {
        return {
          width: this.count <= this.topMaxNavCount
            ? '100%'
            : (this.count * this.topMaxNavWidth + 'px')
        }
      },
      // 获取顶部片区导航每项宽度
      topNavItemWidth () {
        let width = 0
        if (this.count <= this.topMaxNavCount) {
          width = (100 / this.count) + '%'
        } else {
          width = this.topMaxNavWidth + 'px'
        }
        return {width}
      },
      currentAreaHeight () {
        if (this.twoCurrentAreaIndex > -1) {
          return this.dataDisplayTwo[3].y - this.dataDisplayTwo[0].y
        }
        return ''
      },
      currentAreaWidth () {
        if (this.twoCurrentAreaIndex > -1) {
          return this.dataDisplayTwo[3].x - this.dataDisplayTwo[0].x
        }
      },
      currentAreaY () {
        if (this.twoCurrentAreaIndex > -1) {
          return this.dataDisplayTwo[0].y
        }
        return ''
      },
      currentAreaX () {
        if (this.twoCurrentAreaIndex > -1) {
          return this.dataDisplayTwo[0].x
        }
        return ''
      },
      currentAreaLeft () {
        if (this.dataDisplayOneInTwo.length) {
          return this.dataDisplayOneInTwo.reduce((min, compare) => {
            return {left: Math.min(min.left, compare.left)}
          }).left
        }
        return '0,0'
      },
      currentAreaTop () {
        if (this.dataDisplayOneInTwo.length) {
          return this.dataDisplayOneInTwo.reduce((min, compare) => {
            return {top: Math.min(min.top, compare.top)}
          }).top
        }
        return '0,0'
      }
    },
    methods: {
      ...mapMutations({
        'set_current_area_index': types.TWO_SET_CURRENT_AREA_INDEX,
        'cancel_current_area_index': types.TWO_CANCEL_CURRENT_AREA_INDEX,
        'set_current_area_active_direction_coord': types.TWO_CURRENT_AREA_DIRECTON_COORD_CHANGE,
        'set_current_direction_index': types.TWO_CHANGE_CURRENT_DIRECTION,
        'cancel_current_direction_index': types.TWO_CANCEL_CHANGE_CURRENT_DIRECTION
      }),
      dataIndexData (event) {
        const li = event.currentTarget
        const index = Array.prototype.indexOf.call(li.parentElement.children, li)
        if (index === this.twoCurrentAreaIndex) {
          this.cancel_current_area_index()
        } else {
          this.set_current_area_index({index})
        }
      },
      topNavScroll (event) {
        if (this.count <= 6) {
          return
        }
        const navWrapper = event.currentTarget
        const wrapperStyle = navWrapper.style
        const currMarginLeft = (wrapperStyle.marginLeft ? wrapperStyle.marginLeft.match(/-?\d+/)[0] : 0)
        // -1表示滚轮向下，1表示向上
        const delta = (event.wheelDelta
          ? (event.wheelDelta / 120)
          : -(event.detail || 0) / 3)
        if (delta < 0) {
          wrapperStyle.marginLeft = Math.max(-(this.count - this.topMaxNavCount) * this.topMaxNavWidth
              , currMarginLeft - this.topNavMoveItemCount * this.topMaxNavWidth) + 'px'
        } else {
          wrapperStyle.marginLeft = Math.min(0
              , Number(currMarginLeft) + this.topNavMoveItemCount * this.topMaxNavWidth) + 'px'
        }
      },
      addTopNavActiveClass (liList, newVal) {
        if (newVal > -1) {
          liList[newVal].classList.add('active')
          newVal === 0 && liList[newVal].classList.add('active-first')
          newVal === (this.count - 1) && liList[newVal].classList.add('active-last')
          newVal > 0 && liList[newVal - 1].classList.add('active-left')
          newVal < (liList.length - 1) && liList[newVal + 1].classList.add('active-right')
        }
      },
      removeTopNavActiveClass (liList, oldVal) {
        if (oldVal > -1) {
          liList[oldVal].classList.remove('active')
          oldVal === 0 && liList[oldVal].classList.remove('active-first')
          oldVal === (this.count - 1) && liList[oldVal].classList.remove('active-last')
          oldVal > 0 && liList[oldVal - 1].classList.remove('active-left')
          oldVal < (liList.length - 1) && liList[oldVal + 1].classList.remove('active-right')
        }
      },
      changerDirectionInputIndex (event) {
        const inputName = event.target.nextElementSibling.name
        const index = Number(inputName.substring(inputName.lastIndexOf('-') + 1)) - 1
        const anotherIndex = [0, 3][Number(index === 0)]

        if (this.directionsIcon[index] === 'edit') {
          if (this.directionsIcon[anotherIndex] === 'circle-check') {
            Vue.set(this.directionsIcon, anotherIndex, 'edit')
          }
          this.set_current_direction_index({index})
        } else {
          this.cancel_current_direction_index()
        }

        Vue.set(this.directionsIcon, index, this.directionsIcon[index] === 'edit' ? 'circle-check' : 'edit')
      },
      changerDirectionInputData (value) {
        if (!/^\d+,\s?\d+$/.test(value)) {
          return
        }

        const x = Number(value.split(',')[0])
        const y = Number(value.split(',')[1])

        this.set_current_area_active_direction_coord({
          direction: this.currentFocusDirectionInput,
          x,
          y
        })
      },
      focusDirectionInput (event) {
        const inputName = event.target.name
        this.currentFocusDirectionInput = Number(inputName.substring(inputName.lastIndexOf('-') + 1)) - 1
        console.log(this.currentFocusDirectionInput)
      },
      blurDirectionInput (event) {
        this.currentFocusDirectionInput = -1
      }
    },
    watch: {
      twoCurrentAreaIndex (newVal, oldVal) {
        // 改变导航栏样式
        const liList = Array.from(this.$refs.topNavWrapperEle.children)
        Vue.nextTick(() => {
          this.removeTopNavActiveClass(liList, oldVal)
          this.addTopNavActiveClass(liList, newVal)
        })

        // 还原一些数据
        this.currentFocusDirectionInput = -1
        this.directionsIcon = ['edit', '', '', 'edit']
        this.cancel_current_direction_index()
      }
    },
    mounted () {
      const liList = Array.from(this.$refs.topNavWrapperEle.children)
      this.addTopNavActiveClass(liList, this.twoCurrentAreaIndex)
    }
  }
</script>
