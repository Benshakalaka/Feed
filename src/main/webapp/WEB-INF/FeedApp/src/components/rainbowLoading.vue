<template>
  <div class="rainbow-loading">
    <div class="rainbow-wrapper">
      <div class="rainbow-inner">
        <div class="rainbow-line"></div>
      </div>
      <div class="rainbow-inner">
        <div class="rainbow-line"></div>
      </div>
      <div class="rainbow-inner">
        <div class="rainbow-line"></div>
      </div>
      <div class="rainbow-inner">
        <div class="rainbow-line"></div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  @mixin expand-all {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }

  $wrapper-width: 50px;
  $wrapper-height: 20px;
  $wrapper-inner-height: 25px;

  .rainbow-loading {
    @include expand-all;

    .rainbow-wrapper {
      @include expand-all;
      width: $wrapper-width;
      height: $wrapper-height;
      margin: 0 auto;

      .rainbow-inner {
        position: absolute;
        top: 0;
        left: 0;
        height: $wrapper-inner-height;
        width: 100%;
        overflow: hidden;
        transform-origin: 50% 100%;
        animation: rainbow_spin 1500ms cubic-bezier(.175, .885, .32, 1.475) infinite;

        $delay_time:50ms;

        .rainbow-line {
          width: 100%;
          box-sizing: border-box;
          border: 2px solid transparent;
          border-radius: 100%;
          margin: 0 auto;
        }

        &:nth-child(1) {
          animation-delay: $delay_time * 4;

          .rainbow-line {
            margin-top: 5px;
            width: 40px;
            height: 40px;
            border-color: #EB4747;
          }
        }

        &:nth-child(2) {
          animation-delay: $delay_time * 3;

          .rainbow-line {
            margin-top: 10px;
            width: 30px;
            height: 30px;
            border-color: #47EB47;
          }
        }

        &:nth-child(3) {
          animation-delay: $delay_time * 2;

          .rainbow-line {
            margin-top: 15px;
            width: 20px;
            height: 20px;
            border-color: #47EBEB;
          }
        }

        &:nth-child(4) {
          animation-delay: $delay_time;

          .rainbow-line {
            margin-top: 20px;
            width: 10px;
            height: 10px;
            border-color: #4747EB;
          }
        }
      }
    }

    @keyframes rainbow_spin {
      0%, 15% {
        transform: rotate(0deg);
      }

      100% {
        transform: rotate(360deg);
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  export default{
    name: 'rainbow-loading'
  }
</script>
