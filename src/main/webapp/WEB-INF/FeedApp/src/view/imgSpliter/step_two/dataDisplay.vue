<template>
  <div class="display-data data-two" v-if="imgUrl">
    <div class="img-container">
      <div class="img-wrapper">
        <ul class="points-container">
          <li class="points-item" v-for="coord in dataDisplayOneInTwo" :style="{
            'left': onePoint(coord.left) - 2 + 'px',
            'top': onePoint(coord.top) - 2 + 'px'
        }">
            <div class="fix-point"></div>
            <div class="obvious-point"></div>
          </li>
        </ul>
        <img ref="imgEle" :src="imgUrl" alt="" width="100%">
      </div>
    </div>
    <div class="img-container">
      <div class="address-pic" ref="bgImgEle">
        <div class="bg-img" :style="bgImgStyle"></div>
      </div>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .data-two {
    width: 100%;
    padding: 30px 0;

    .img-container {
      width: 50%;
      float: left;
      font-size: 0;
      padding: 0 30px;
      box-sizing: border-box;
      border-right: 1px solid #ccc;

      .img-wrapper {
        width: 100%;
        position: relative;
        overflow: hidden;

        $point-item-size: 4px;
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
            }
          }
        }
      }

      .address-pic {
        position: relative;
        overflow: hidden;
        .bg-img {
          position: absolute;
          left: 0;
          top: 0;
          right: 0;
          bottom: 0;
          margin: auto;
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters } from 'vuex'
  import Vue from 'vue'

  export default {
    data () {
      return {
        // 比例, 当前展示的图片和原始(第一步选定的大小)比例的大小比
        rate: 1
      }
    },
    methods: {
      // 根据比例得到的大小
      onePoint (value) {
        return Number.prototype.toFixed.call(value * this.rate, 1)
      }
    },
    computed: {
      ...mapGetters([
        'imgUrl',
        'bgUrl',
        'width',
        'twoCurrentAreaIndex',
        'dataDisplayTwo',
        'dataDisplayOneInTwo'
      ]),
      bgImgStyle () {
        if (this.twoCurrentAreaIndex > -1) {
          return {
            height: this.onePoint(this.dataDisplayTwo[3].y - this.dataDisplayTwo[0].y) + 'px',
            width: this.onePoint(this.dataDisplayTwo[3].x - this.dataDisplayTwo[0].x) + 'px',
            'background-image': `url(${this.bgUrl})`,
            'background-position': `-${this.onePoint(this.dataDisplayTwo[0].x)}px -${this.onePoint(this.dataDisplayTwo[0].y)}px`,
            'background-size': '484px'
          }
        }
      }
    },
    mounted () {
      const bgImgEle = this.$refs.bgImgEle
      const imgEle = this.$refs.imgEle
      Vue.nextTick(() => {
        this.rate = Number.prototype.toFixed.call(imgEle.width / this.width, 3)
        console.log(imgEle.width + ' / ' + this.width + ' : ' + this.rate)
        bgImgEle.style.width = imgEle.width + 'px'
        bgImgEle.style.height = imgEle.height + 'px'
        console.dir(imgEle)
      })
    }
  }
</script>
