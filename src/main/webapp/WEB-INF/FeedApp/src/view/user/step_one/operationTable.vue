<template>
  <div class="step step-one" key="one">
    <div class="left-part">
      <div class="data-section">
        <label-input
                labelText="分拨中心图片名称"
                :errorText="imgError"
                @change="imgChange"
                @focus="imgDisplayUrl = (imgUrl || imgDisplayUrl)"
        ></label-input>
        <label-input
                labelText="填写图片背景名称"
                :errorText="bgError"
                @change="bgChange"
                @focus="imgDisplayUrl = (bgUrl || imgDisplayUrl)"
        ></label-input>
        <label-input
                labelText="设定片区数量"
                :errorText="countError"
                @change="countChange"
        ></label-input>
        <label-input
                labelText="设定图片宽度"
                :errorText="widthError"
                @change="widthChange"
        ></label-input>
      </div>
    </div><!--
            --><div class="right-part">
        <div class="pick-section">
        <img class="img-container" :src="imgDisplayUrl" width="100%" v-if="imgDisplayUrl">
      </div>
  </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .step-one {
    .left-part, .right-part {
      display: inline-block;
      width: 50%;
      height: 100%;
    }

    .left-part {
      box-sizing: border-box;
      border-right: 1px solid #ccc;
      vertical-align: top;
    }

    .data-section {
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      padding: 10px 50px;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
    }

    .pick-section {
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      position: relative;
      display: inline-block;

      img {
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import LabelInput from '../../../components/labelInput.vue'
  import { mapGetters, mapMutations } from 'vuex'
  import * as types from '../../../store/mutation-types'

  export default {
    name: 'one-top-operation',
    data () {
      return {
        imgDisplayUrl: '',
        imgError: '',
        bgError: '',
        widthError: '',
        countError: ''
      }
    },
    computed: {
      ...mapGetters([
        'count',
        'imgUrl',
        'bgUrl',
        'width',
        'count'
      ])
    },
    methods: {
      ...mapMutations({
        'init_areas_info': types.INIT_AREA_INFO,
        'set_image_url': types.SET_IMAGE_URL,
        'set_background_url': types.SET_BACKGROUND_URL,
        'set_image_width': types.SET_IMAGE_WIDTH,
        'set_area_count': types.SET_AREA_COUNT
      }),
      imgChange (event, value) {
        this.set_image_url({
          imgUrl: (value ? ('../../../static/img/' + value) : '')
        })
        this.imgDisplayUrl = (this.imgUrl || this.bgUrl)
      },
      bgChange (event, value) {
        this.set_background_url({
          bgUrl: (value ? ('../../../static/img/' + value) : '')
        })
        this.imgDisplayUrl = this.bgUrl || this.imgUrl
      },
      widthChange (event, width) {
        this.set_image_width({
          width
        })
      },
      countChange (event, count) {
        this.set_area_count({
          count
        })
      }
    },
    watch: {
      count () {
        this.init_areas_info()
      }
    },
    components: {
      'label-input': LabelInput
    },
    created () {
      this.imgDisplayUrl = this.imgUrl || this.bgUrl
    }
  }
</script>
