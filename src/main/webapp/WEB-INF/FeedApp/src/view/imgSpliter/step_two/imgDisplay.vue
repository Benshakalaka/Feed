<template>
  <div class="display-img display-two"
       :style="{width: `${width}px`}"
       v-if="!!width"
       @mouseleave="imgLeaveTwo"
       @mousemove="imgMoveTwo"
       @mousedown="imgClickTwo">
    <div class="posi-item left-top-posi" :style="leftItem"></div>
    <div class="posi-item right-bottom-posi" :style="rightItem"></div>
    <img class="img-display-container"
         :src="bgUrl"
         width="100%"
         v-if="bgUrl"
         ref="bgImg"
    >
  </div>
</template>

<style type="text/scss" lang="scss">
  .display-two {
    position: relative;

    .posi-item {
      position: absolute;
      box-sizing: border-box;
    }

    &:hover .left-top-posi {
      border-right: 1px dashed rgba(0,0,0,.8);
      border-bottom: 1px dashed rgba(0,0,0,.8);
    }

    &:hover .right-bottom-posi {
      border-left: 1px dashed rgba(0,0,0,.8);
      border-top: 1px dashed rgba(0,0,0,.8);
    }

    .left-top-posi {
      top: 0;
      left: 0;
    }

    .right-bottom-posi {
      right: 0;
      bottom: 0;
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'

  export default {
    data () {
      return {
        leftItem: {width: '0', height: '0'},
        rightItem: {width: '0', height: '0'}
      }
    },
    computed: {
      ...mapGetters([
        'bgUrl',
        'width',
        'twoCurrentAreaIndex',
        'getRelativeMousePosi',
        'twoCurrentDirection',
        'dataDisplayTwo'
      ])
    },
    methods: {
      ...mapMutations({
        'init_areas_active_data': types.TWO_INIT_AREAS_DATA,
        'set_current_area_active_direction_coord': types.TWO_CURRENT_AREA_DIRECTON_COORD_CHANGE
      }),
      imgClickTwo (event) {
        if (this.twoCurrentAreaIndex > -1 && this.twoCurrentDirection > -1) {
          const coord = this.getRelativeMousePosi(event, event.currentTarget)
          // 右下角坐标必须大于左上角
          if (this.twoCurrentDirection === 3 && (coord.left < this.dataDisplayTwo[0].x || coord.top < this.dataDisplayTwo[0].y)) {
            return
          }

          this.set_current_area_active_direction_coord({
            x: coord.left,
            y: coord.top
          })
        }
      },
      imgMoveTwo (event) {
        if (this.twoCurrentAreaIndex > -1 && this.twoCurrentDirection > -1) {
          const coord = this.getRelativeMousePosi(event, event.currentTarget)
          const height = this.$refs.bgImg.height

          this.leftItem.width = coord.left + 'px'
          this.leftItem.height = coord.top + 'px'
          this.rightItem.width = this.width - coord.left + 1 + 'px'
          this.rightItem.height = height - coord.top + 1 + 'px'
        }
      },
      imgLeaveTwo () {
        this.leftItem.width =
          this.leftItem.height =
            this.rightItem.width =
              this.rightItem.height = 0
      }
    },
    mounted () {
      this.init_areas_active_data({
        height: this.$refs.bgImg.height
      })
    }
  }
</script>
