<template>
  <div class="step step-two">
    <div class="top-list">
      <div class="nav-wrapper">
        <ul :style="{width: 'calc(100% + ' + scrollBarWidth + 'px'}" @scroll="topNavScroll">
          <li v-for="n in Number(count)"
              @click="dataIndexData"
              :class="{active: twoCurrentAreaIndex === (n - 1)}"
              :style="{width: 100 / Math.min(count, topMaxNavCount) + '%'}"
          >
            {{ `片区${n}` }}
          </li>
        </ul>
      </div>
    </div>
    <div class="bottom-data">
      <div class="left-coords">
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">LT</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">RT</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">LB</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">RB</template>
          </el-input>
        </div>
      </div>
      <div class="right-attr">
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">Height</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">Width</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">Pos_X</template>
          </el-input>
        </div>
        <div class="data-item">
          <el-input
            icon="close">
            <template slot="prepend">Pos_Y</template>
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
          overflow-y: scroll;
          border-bottom: 2px solid lighten(#000, 85%);

          li {
            display: block;
            float: left;
            height: $top-index-li-height;
            line-height: $top-index-li-height;
            text-align: center;
            box-sizing: border-box;

            &:not(:nth-child(6n)) {
              border-right: 1px solid lighten(#000, 85%);
            }

            &.active {

            }
          }
        }
      }
    }

    .bottom-data {
      height: calc(100% - #{$top-index-li-height});
      width: 100%;
      box-sizing: border-box;
      padding: 45px 50px 45px 30px;

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
      }

      .right-attr {
        width: 50%;
        height: 100%;
        float: left;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
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
        topMaxNavCount: 6
      }
    },
    computed: {
      ...mapGetters([
        'count',
        'twoCurrentAreaIndex',
        'scrollBarWidth'
      ])
    },
    methods: {
      ...mapMutations({
        'set_current_area_index': types.TWO_SET_CURRENT_AREA_INDEX,
        'cancel_current_area_index': types.TWO_CANCEL_CURRENT_AREA_INDEX
      }),
      dataIndexData (event) {
        const li = event.target
        const index = Array.prototype.indexOf.call(li.parentElement.children, li)
        if (index === this.twoCurrentAreaIndex) {
          li.classList.remove('active')
          this.cancel_current_area_index()
        } else {
          li.classList.add('active')
          Array.from(li.parentElement.children).map(item => {
            li !== item && item.classList.remove('active')
          })
          this.set_current_area_index({
            index
          })
        }
      },
      topNavScroll: (function () {
        let scrollTimer = null
        let scrollPrev = 0
        return function (event) {
          const direction = event.target.scrollTop > scrollPrev
          if (!scrollTimer) {
            direction && (event.target.scrollTop += 50)
            direction || (event.target.scrollTop -= 50)
            scrollPrev = event.target.scrollTop
            scrollTimer = setTimeout(function () {
              scrollTimer = null
            }, 300)
          }
          return false
        }
      })()
    }
  }
</script>
