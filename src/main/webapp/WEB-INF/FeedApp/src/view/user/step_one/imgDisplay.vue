<template>
  <div class="display-img display-one" :style="{width: `${width}px`}" v-if="!!width">
    <img class="img-display-container" :src="imgUrl" width="100%" @mousedown="imgClickOne" v-if="imgUrl">
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'
  import Vue from 'vue'

  export default {
    name: 'one-bottom-img',
    data () {
      return {
        wrapperEle: null
      }
    },
    methods: {
      ...mapMutations({
        'add_or_update_current_area_coord': types.ONE_ADD_OR_UPDATE_CURRENT_AREA_COORDS
      }),
      imgClickOne (event) {
        const coord = {
          left: Number(event.pageX) + Number(this.wrapperEle.scrollLeft) - event.target.offsetLeft,
          top: Number(event.pageY) + Number(this.wrapperEle.scrollTop) - event.target.offsetTop
        }
        this.add_or_update_current_area_coord({coord})
      }
    },
    computed: {
      ...mapGetters([
        'width',
        'imgUrl'
      ])
    },
    mounted () {
      this.wrapperEle = document.getElementById('spliter-main')
    }
  }
</script>
