<template>
  <div class="display-img display-three" @click="imgClickHandler">
    <div class="img-container" :style="{width:width+'px'}">
      <img :src="imgUrl" alt="" usemap="#Map" width="100%">
      <ul class="area-items-wrapper area-active-container" ref="areaActiveWrapper">
        <li class="active-item"
            v-for="area in areasInfo"
            :style="getAreaActive(area)"
            >
        </li>
      </ul>
      <map name="Map" id="Map">
        <area shape="poly"
          v-for="(area, index) in areasInfo"
          :coords="getAreaCoords(area)"
          @click="areaClickHandler(index)"
          @mouseenter="areaMouseenterHandler(index)"
          @mouseleave="areaMouseleaveHandler(index)"
          href="javascript:;">
      </map>
      <div class="area-pointer" v-show="threeCurrentAreaIndex > -1" :style="position"></div>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .display-three {
    background-color: #373e4c;;

    .img-container {
      position: relative;
    }

    img {
      position: relative;
      z-index: 9;
    }

    map {
      area {
        cursor: default;
      }
    }

    .area-items-wrapper {
      width: 0;
      height: 0;
    }

    .area-active-container {
      .active-item {
        position: absolute;
        z-index: 6;
        display: none;
      }
    }

    .area-pointer {
      height: 36px;
      width: 35px;
      background: url("../../../../static/img/position.png") no-repeat center;
      position: absolute;
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'

  export default {
    data () {
      return {
        position: {
          left: 0,
          top: 0
        }
      }
    },
    computed: {
      ...mapGetters([
        'bgUrl',
        'imgUrl',
        'width',
        'areasInfo',
        'getRelativeMousePosi',
        'threeCurrentAreaIndex'
      ])
    },
    methods: {
      ...mapMutations({
        'set_area_item': types.THREE_SET_AREA_ITEM
      }),
      getAreaCoords (area) {
        let dataOne = ''
        const one = area.area_coords
        if (one.length) {
          dataOne = one.reduce((res, item) => {
            return res + ',' + item.left + ',' + item.top
          }, '').slice(1)
        }
        console.log(dataOne)
        return dataOne
      },
      getAreaActive (area) {
        const one = area.area_coords
        const two = area.area_active
        let dataTwo = ''
        if (one.length && two.length) {
          const left = one.reduce((min, compare) => {
            return {left: Math.min(min.left, compare.left)}
          }).left + 'px'
          const top = one.reduce((min, compare) => {
            return {top: Math.min(min.top, compare.top)}
          }).top + 'px'
          const width = two[3].x - two[0].x + 'px'
          const height = two[3].y - two[0].y + 'px'
          const posx = two[0].x + 'px'
          const posy = two[0].y + 'px'
          return {
            left,
            top,
            width,
            height,
            'background-position': `-${posx} -${posy}`,
            'background-image': `url(${this.bgUrl})`,
            'background-size': this.width + 'px'
          }
        }
        return {}
      },
      areaClickHandler (index) {
      },
      areaMouseenterHandler (index) {
        const areaActiveWrapper = this.$refs.areaActiveWrapper
        areaActiveWrapper.querySelector(`li:nth-child(${index + 1})`).style.display = 'block'
      },
      areaMouseleaveHandler (index) {
        const areaActiveWrapper = this.$refs.areaActiveWrapper
        areaActiveWrapper.querySelector(`li:nth-child(${index + 1})`).style.display = 'none'
      },
      imgClickHandler (event) {
        if (this.threeCurrentAreaIndex > -1) {
          const coord = this.getRelativeMousePosi(event, event.currentTarget)
          this.position.left = coord.left + 'px'
          this.position.top = coord.top + 'px'
          this.set_area_item({
            left: coord.left,
            top: coord.top
          })
        }
      }
    }
  }
</script>
