<template>
  <div class="label-input-section" :style="{'margin-top': labelMarginTop}">
    <div class="label" :class="labelClass">{{ labelText }}</div>
    <input :type="inputType" @change="labelInputChange" @focus="labelInputFocus" @blur="labelInputBlur">
    <div class="underline">
      <hr class="normal-line">
      <hr class="info-line" :class="{focus: focus}">
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .label-input-section {
    width: 100%;
    line-height: 40px;
    height: 40px;
    margin: 0 auto;
    position: relative;

    .label {
      position: absolute;
      padding-left: 5px;
      transition: transform .35s cubic-bezier(0.23, 1, 0.32, 1);
      transform: translate3d(0, 0, 0) scale(1);
      transform-origin: left top;
      cursor: text;
      pointer-events: none;
      user-select: none;
      letter-spacing: 2px;

      &.focus {
        transform: translate3d(0, -20px, 0) scale(.75);
      }
    }

    input {
      width: 100%;
      height: 38px;
      outline: none;
      border: 0;
      padding: 0 5px;
      box-sizing: border-box;
      background-color: transparent;
    }

    .underline {
      position: relative;

      hr {
        position: absolute;
        left: 0;
        right: 0;
        margin: auto;
        border: 0;
      }

      .normal-line {
        height: 1px;
        background-color: #c0c0c0;
      }

      .info-line {
        height: 2px;
        background-color: rgb(3, 169, 244);
        transition: transform .35s cubic-bezier(0.23, 1, 0.32, 1);
        transform: scaleX(0);

        &.focus {
          transform: scaleX(1);
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  export default {
    data () {
      return {
        focus: false,
        value: ''
      }
    },
    props: {
      topMargin: {
        type: Number,
        default: 0
      },
      labelText: {
        type: String
      },
      inputType: {
        type: String,
        default: 'text'
      }
    },
    computed: {
      labelClass () {
        let labelNewClass = []

        if (this.focus || this.value) {
          labelNewClass.push('focus')
        }

        return labelNewClass
      },
      labelMarginTop () {
        return this.topMargin + 'px'
      }
    },
    methods: {
      labelInputFocus (event) {
        this.focus = true
        this.$emit('focus', event)
      },
      labelInputBlur (event) {
        this.focus = false
        this.$emit('blur', event)
      },
      labelInputChange (event) {
        const value = event.target.value
        this.value = value
        this.$emit('change', event, value)
      }
    }
  }
</script>
