<template>
  <div class="display-img display-one" :style="{width: `${width}px`}" v-if="!!width" ref="imgWrapper">
    <ul class="points-container">
      <!--<li class="points-item" v-for="coord in dataDisplayOne"></li>-->
      <!--<li class="points-item" v-for="coord in dataDisplayOne" :style="{left: coord.left - 4 + 'px', top: coord.top - 4 + 'px'}">-->
      <li class="points-item" v-for="(coord, index) in dataDisplayOne" :style="{
          'left': coord.left - 4 + 'px',
          'top': coord.top - 4 + 'px'
      }">
        <el-tooltip :content="index + ' : [' + coord.left + ',' + coord.top + ']'" placement="top">
          <div class="fix-point"></div>
        </el-tooltip>
        <div class="obvious-point" :style="{'background-color': (oneCurrentDataDisplayFocusIndex == index ? '#0f0' : '')}" v-for="n in pointObviousLevel"></div>
      </li>
    </ul>
    <img class="img-display-container" :src="imgUrl" width="100%" @mousedown="imgClickOne" v-if="imgUrl">
  </div>
</template>

<style type="text/scss" lang="scss">
  $point-item-size: 8px;

  .display-one {
    position: relative;

    img {
      position: relative;
      z-index: 9;
    }

    .points-container {
      margin: 0;
      padding: 0;
      list-style: none;
      width: 0;
      height: 0;

      .points-item {
        display: inline-block;
        position: absolute;
        width: $point-item-size;
        height: $point-item-size;

        .fix-point {
          width: 100%;
          height: 100%;
          background-color: #fff;
          border-radius: 100%;
          position: absolute;
          left: 0;
          top: 0;
          z-index: 10;
        }

        .obvious-point {
          width: 100%;
          height: 100%;
          background-color: #f00;
          border-radius: 100%;
          position: absolute;
          left: 0;
          top: 0;
          z-index: 8;

          &:nth-child(2) {
            animation: one-flash-item 2s linear infinite;
          }

          &:nth-child(3) {
            animation: one-flash-item 2s linear infinite;
            animation-delay: 1s;
          }
        }
      }
    }
  }

  @keyframes one-flash-item {
    0% {
      transform: scale(1);
      opacity: .8;
    }
    50% {
      transform: scale(3);
      opacity: .4;
    }
    100% {
      transform: scale(6);
      opacity: 0;
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import { Tooltip } from 'element-ui'
  import * as types from '../../../store/mutation-types'
  import Vue from 'vue'

  Vue.use(Tooltip)

  export default {
    name: 'one-bottom-img',
    data () {
      return {
        wrapperEle: null,
        pointObviousLevel: 2
      }
    },
    methods: {
      ...mapMutations({
        'add_or_update_current_area_coord': types.ONE_ADD_OR_UPDATE_CURRENT_AREA_COORDS
      }),
      imgClickOne (event) {
        const coord = {
          left: Number(event.pageX) + Number(this.wrapperEle.scrollLeft) - this.$refs.imgWrapper.offsetLeft,
          top: Number(event.pageY) + Number(this.wrapperEle.scrollTop) - this.$refs.imgWrapper.offsetTop
        }
        this.add_or_update_current_area_coord({coord})
      }
    },
    computed: {
      ...mapGetters([
        'width',
        'imgUrl',
        'dataDisplayOne',
        'oneCurrentDataDisplayFocusIndex'
      ])
    },
    mounted () {
      this.wrapperEle = document.getElementById('spliter-main')
    }
  }
</script>
