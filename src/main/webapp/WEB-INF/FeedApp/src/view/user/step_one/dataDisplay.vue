<template>
  <div class="display-data data-one" v-if="count > 0" key="one-d">
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
</template>

<style lang="scss" rel="stylesheet/scss">
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
            box-shadow: 0 0 0;
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
  import Vue from 'vue'
  import { Input } from 'element-ui'
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'

  Vue.use(Input)

  export default {
    name: 'one-middle-data',
    data () {
      return {
        MAX_SHOW_LINES: 5,
        EACH_LINE_HEIGHT: 45
      }
    },
    watch: {
      count (newVal) {
        Vue.nextTick(() => {
          this.$refs.dataDisplayOne.style.height = Math.min(newVal, this.MAX_SHOW_LINES) * this.EACH_LINE_HEIGHT + 'px'
        })
      }
    },
    computed: {
      ...mapGetters([
        'count',
        'dataDisplayOne'
      ])
    },
    methods: {
      ...mapMutations({
        'set_current_area_index': types.ONE_SET_CURRENT_AREA_INDEX,
        'delete_current_area_coord': types.ONE_DELETE_CURRENT_AREA_COORD,
        'set_current_area_coord_index': types.ONE_SET_CURRENT_AREA_COORD_FPCUS_INDEX,
        'cancel_current_area_coord': types.ONE_CANCEL_CURRENT_AREA_COORD_FPCUS_INDEX
      }),
      dataIndexData (event) {
        const li = event.target
        li.classList.add('active')
        Array.from(li.parentElement.children).map(item => {
          li !== item && item.classList.remove('active')
        })
        this.set_current_area_index({
          index: Array.prototype.indexOf.call(li.parentElement.children, li)
        })
      },
      displayDataDeleteOne (event) {
        let inputName = event.target.nextElementSibling.name
        let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
        this.delete_current_area_coord({index})
      },
      // 点击一个坐标, 表示希望修改该坐标
      displayDataChangeOne (event) {
        let inputName = event.target.name
        let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
        this.set_current_area_coord_index({index})
      },
      // 在点击坐标后表示希望修改, 一旦触发blur后立即触发图片点击事件表示真的修改
      // 为focus事件做标记
      displayDataBlurOne (event) {
        const _this = this
        setTimeout(function () {
          _this.cancel_current_area_coord()
        }, 150)
      }
    }
  }
</script>
