<template>
  <div class="display-data display-three">
    <div class="display-top">
      <h3>DATA</h3>
    </div>
    <div class="display-bottom">
      <ul class="data-wrapper" ref="dataWrapper">
        <li class="data-item" v-for="index in 3">
          <el-input :readonly="true"
                    :value="dataDisplayInThree && dataDisplayInThree[`data${showData[index - 1].data}`]">
            <template slot="prepend">{{showData[index - 1].name}}</template>
            <el-button slot="append" icon="document" @click="dataClickHandler($event, index-1)"></el-button>
          </el-input>
        </li>
      </ul>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .display-three {
    .display-top {
      height: 50px;
      width: 100%;
      line-height: 50px;
      text-align: center;
      border-bottom: 1px solid #ccc;

      h3 {
        padding: 0;
        margin: 0;
      }
    }

    .display-bottom {
      width: 100%;

      .data-wrapper {
        margin: 0;
        padding: 0;
        list-style: none;

        .data-item {
          display: block;
          width: 50%;
          height: 36px;
          margin: 20px auto;
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'
  import { Input, Button } from 'element-ui'
  import Vue from 'vue'

  Vue.use(Input)
  Vue.use(Button)

  export default {
    data () {
      return {
        showData: [
          {name: 'AREA_COORD', data: 'One'},
          {name: 'AREA_ACTIVE', data: 'Two'},
          {name: 'AREA_ITEMS', data: 'Three'}
        ]
      }
    },
    computed: {
      ...mapGetters([
        'dataDisplayInThree'
      ])
    },
    methods: {
      dataClickHandler (event, index) {
        const ele = this.$refs.dataWrapper.querySelector(`li:nth-child(${index + 1}) input`)
        ele.select()
        document.execCommand('copy', false)
      }
    }
  }
</script>
