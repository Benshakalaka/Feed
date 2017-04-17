<template>
  <div class="step step-three">
    <div class="area-wrapper" :style="{width:`calc(100% + ${this.scrollBarWidth}px)`}">
      <ul class="area-container"
          :style="areaContainerStyle"
          :class="areaContainerClass"
      >
        <li class="area-item"
            :style="areaItemStyle"
            v-for="index in (count < 6 ? Number(count) : (Math.floor(count / 5) * 5))"
            :class="{active: (threeCurrentAreaIndex + 1) === index}"
            @click="areaChooseThree(index)">
          {{ '片区' + index }}
      </li>
      </ul>
      <ul class="area-left-container">
        <li class="area-left-item"
            :style="areaLeftItemStyle"
            v-for="index in (count % maxColumns)"
            :class="{active: (threeCurrentAreaIndex + 1) === Number(Math.floor(count / maxColumns) * maxColumns + index)}"
            @click="areaChooseThree(Number(Math.floor(count / maxColumns) * maxColumns + index))">
          {{ '片区' + Number(Math.floor(count / maxColumns) * maxColumns + index) }}
      </li>
      </ul>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .step-three {
    padding: 0;
    overflow: hidden;

    .area-wrapper {
      width: 100%;
      height: 100%;
      overflow-y: scroll;
      overflow-x: hidden;
    }

    ul {
      list-style: none;
      margin: 0;
      padding: 0;
      overflow: auto;

      li {
        text-align: center;
        float: left;
        box-sizing: border-box;
        display: block;

        &.active {
          background-color: #f00;
        }

        &:not(:last-child) {
          border-right: 1px solid #ccc;
        }
      }
    }

    .max-area-item .area-item:nth-child(5n){
      border-right: 0 !important;
    }

    .area-container {
      height: 100%;
      overflow: auto;

      .area-item {
        border-bottom: 1px solid #ccc;
      }
    }

    .area-left-container {
      width: 100%;
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'
  import Vue from 'vue'

  export default {
    data () {
      return {
        maxRows: 3,
        maxColumns: 5,
        areaContainerHeight: 351,
        areaContainerWidth: 970
      }
    },
    computed: {
      ...mapGetters([
        'count',
        'scrollBarWidth',
        'threeCurrentAreaIndex'
      ]),
      areaContainerClass () {
        return {
          'max-area-item': this.count > this.maxColumns
        }
      },
      areaContainerStyle () {
        return {
          height: Math.floor(this.count / this.maxColumns) * (this.areaContainerHeight / Math.min(Math.max(Math.ceil(this.count / this.maxColumns), 1), 3)) + 'px'
        }
      },
      areaItemStyle () {
        const height = (this.areaContainerHeight / Math.min(Math.max(Math.ceil(this.count / this.maxColumns), 1), 3)) + 'px'
        return {
          height,
          width: this.areaContainerWidth / Math.min(this.count, 5) + 'px',
          'line-height': height
        }
      },
      areaLeftItemStyle () {
        const height = (this.areaContainerHeight / Math.min(Math.max(Math.ceil(this.count / this.maxColumns), 1), 3)) + 'px'
        return {
          height,
          width: this.areaContainerWidth / (this.count % this.maxColumns) + 'px',
          'line-height': height
        }
      }
    },
    methods: {
      ...mapMutations({
        'set_current_area_index': types.THREE_SET_CURRENT_AREA_INDEX,
        'cancel_current_area_Index': types.THREE_CANCEL_CURRENT_AREA_INDEX
      }),
      areaChooseThree (index) {
        if (this.threeCurrentAreaIndex === (index - 1)) {
          this.cancel_current_area_Index()
        } else {
          this.set_current_area_index({index: index - 1})
        }
      }
    }
  }
</script>
