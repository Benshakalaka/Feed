<template>
  <div class="step step-two">
    <div class="top-list">
      <div class="nav-wrapper">
        <ul :style="{width: 'calc(100% + ' + scrollBarWidth + 'px'}" @mouseWheel="topNavMouseWheel">
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
    <div class="right-data"></div>
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
          padding: 0;
          height: $top-index-li-height;
          overflow-y: scroll;

          li {
            display: block;
            float: left;
            height: $top-index-li-height;
            line-height: $top-index-li-height;
            text-align: center;
          }
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'

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
      topNavMouseWheel (event) {
        console.log(event)
      }
    }
  }
</script>
