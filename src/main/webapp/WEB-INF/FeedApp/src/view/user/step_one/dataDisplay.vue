<template>
  <div class="display-data data-one" v-if="count > 0" key="one-d">
    <div class="left-index">
      <ul>
        <li v-for="n in Number(count)" @click="dataIndexData" :class="{active: oneCurrentAreaIndex === (n - 1)}">{{ `片区${n}` }}</li>
      </ul>
    </div><!--
          --><div class="right-data" ref="dataDisplayOne">
      <ul>
        <li v-for="(coords, index) in dataDisplayOne">
          <el-input
                  :value='`${coords.left},${coords.top}`'
                  icon="delete"
                  :on-icon-click="displayDataDeleteOne"
                  :name='"data-display-one-" + dataDisplayOne.indexOf(coords)'
                  @focus="displayDataChangeOne"
                  @blur="displayDataBlurOne"
                  @change="displayDataInputChangeOne"
          >
            <template slot="prepend">{{ index | twoNumberFilter }}</template>
          </el-input>
        </li>
      </ul>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
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
          cursor: default;

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
      overflow: auto;

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
          width: 140px;
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
        'dataDisplayOne',
        'oneCurrentAreaIndex'
      ])
    },
    methods: {
      ...mapMutations({
        'set_current_area_index': types.ONE_SET_CURRENT_AREA_INDEX,
        'cancel_current_area_index': types.ONE_CANCEL_CURRENT_AREA_INDEX,
        'delete_current_area_coord': types.ONE_DELETE_CURRENT_AREA_COORD,
        'set_current_area_coord_index': types.ONE_SET_CURRENT_AREA_COORD_FPCUS_INDEX,
        'cancel_current_area_coord': types.ONE_CANCEL_CURRENT_AREA_COORD_FPCUS_INDEX,
        'add_or_update_current_area_coord': types.ONE_ADD_OR_UPDATE_CURRENT_AREA_COORDS
      }),
      dataIndexData (event) {
        const li = event.target
        const index = Array.prototype.indexOf.call(li.parentElement.children, li)
        if (index === this.oneCurrentAreaIndex) {
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
      displayDataDeleteOne (event) {
        let inputName = event.target.nextElementSibling.name
        let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
        this.delete_current_area_coord({index})
      },
      // 点击一个坐标, 表示希望修改该坐标
      // 此处延迟是为了blur的延迟修改不影响紧接着的下一次focus
      displayDataChangeOne (event) {
        const _this = this
        setTimeout(function () {
          let inputName = event.target.name
          let index = Number(inputName.substring(inputName.lastIndexOf('-') + 1))
          _this.set_current_area_coord_index({index})
        }, 150)
      },
      // 在点击坐标后表示希望修改, 一旦触发blur后立即触发图片点击事件表示真的修改
      // 延迟是为了在focus获取坐标index后, 点击图片某处修改该坐标, 继而blur释放此坐标index
      displayDataBlurOne (event) {
        const _this = this
        setTimeout(function () {
          _this.cancel_current_area_coord()
        }, 150)
      },
      displayDataInputChangeOne (value) {
        this.add_or_update_current_area_coord({
          coord: {
            left: value.split(',')[0],
            top: value.split(',')[1]
          }
        })
      }
    }
  }
</script>
