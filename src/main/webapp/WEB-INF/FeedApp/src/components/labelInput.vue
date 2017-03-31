<template>
  <div class="label-input-section" :style="{'margin-top': labelMarginTop}">
    <div class="label" :class="labelClass">{{ labelText }}</div>
    <input
      :type="labelInputType"
      :placeholder="placeholder"
      @change="labelInputChange"
      @focus="labelInputFocus"
      @blur="labelInputBlur"
      ref="inputEle"
      spellcheck="false"
    ><!--
    --><i class="input-icon fa" :class="[inputClearIconClass, iconVagueClass]" v-if="inputType === 'text' && !passwordShow" @click="clearClickHandler"></i><!--
    --><i class="input-icon fa" :class="[passwordShow?noSeeIconClass:seeIconClass, iconVagueClass]" v-if="inputType==='password' || passwordShow" @click="pwdClickHandler"></i>

    <div class="underline" :class="[errorText?'error-underline':'']">
      <hr class="normal-line">
      <hr class="info-line" :class="{focus: focus}">
    </div>

    <div class="input-error">
      <transition name="error-fade">
        <span v-if="errorText">
          {{ errorText }}
        </span>
      </transition>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .label-input-section {
    width: 100%;
    line-height: 40px;
    height: 40px;
    margin: 0 auto;
    font-size: 15px;
    overflow: hidden;

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
        transform: translate3d(0, -20px, 0) scale(.85);
      }
    }
    
    .input-icon {
      position: absolute;
      display: inline-block;
      margin: 12px 0 0 -20px;
    }

    .icon-vague {
      opacity: .1;
    }

    input {
      display: inline-block;
      width: 100%;
      height: 38px;
      outline: none;
      border: 0;
      padding: 0 30px 0 5px;
      box-sizing: border-box;
      background-color: transparent;
      font-size: 15px;
    }

    .underline {
      position: relative;
      margin-top: -2px;

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

    .error-underline .info-line {
      background-color: #f44336;
    }

    .input-error {
      color: #f00;
      position: absolute;
      font-size: 12px;
      line-height: 20px;
      text-align: right;
      padding-left: 5px;
      overflow: hidden;

      span {
        display: block;
      }
    }

    .error-fade-enter, .error-fade-leave-active {
      transform: translateY(-10px);
      opacity: 0;
    }

    .error-fade-enter-active, .error-fade-leave-active {
      transition: all .8s ease;
    }
  }
</style>

<script type="text/ecmascript-6">
  export default {
    data () {
      return {
        focus: false,
        value: '',
        passwordShow: false
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
      },
      placeText: {
        type: String
      },
      seeIconClass: {
        type: String,
        default: 'fa-eye'
      },
      noSeeIconClass: {
        type: String,
        default: 'fa-eye-slash'
      },
      inputClearIconClass: {
        type: String,
        default: 'fa-times'
      },
      errorText: {
        type: String
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
      labelInputType () {
        if (this.inputType === 'password') {
          return (this.passwordShow ? 'text' : 'password')
        } else {
          return this.inputType
        }
      },
      labelMarginTop () {
        return this.topMargin + 'px'
      },
      placeholder () {
        return (this.focus ? this.placeText : '')
      },
      iconVagueClass () {
        return (this.focus || this.value) ? '' : 'icon-vague'
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
      },
      pwdClickHandler () {
        this.passwordShow = !this.passwordShow
      },
      clearClickHandler () {
        let have2change = false
        const inputEle = this.$refs.inputEle
        if (inputEle.value !== '') {
          have2change = true
        }
        inputEle.value = ''
        // 上述修改不触发change事件，因此属性value值没变，下面手动变一下
        this.value = ''
        have2change && (this.$emit('change', null, ''))
      }
    }
  }
</script>
