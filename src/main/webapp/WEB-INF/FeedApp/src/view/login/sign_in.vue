<template>
  <div class="sign-in-section">
    <abstract-form>
      <h1 slot="header">登录</h1>

      <label-input
        :topMargin="contentMargin * 2"
        labelText="邮箱地址"
      >
      </label-input>
      <label-input
        :topMargin="contentMargin"
        labelText="请输入密码"
        inputType="password"
      >
      </label-input>
      <div :style="{'margin-top': `${contentMargin}px`}">
        <el-checkbox>记住密码</el-checkbox>
        <a href="javascript:void(0)"><span class="missing-password">忘记密码</span></a>
      </div>

      <custom-button
        type="primary"
        slot="foot"
        :showText="showText"
        :loading="isLogining"
        @click="clickHandler"
      >
      </custom-button>
    </abstract-form>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .sign-in-section {
    height: 120%;
    width: 400px;
    float: left;
    margin-top: -5%;

    h1 {
      font-size: 28px;
      text-align: center;
      letter-spacing: 5px;
      font-weight: 400;
    }

    .missing-password {
      float: right;
      font-size: 14px;
      opacity: .5;
      
      &:hover, &:active {
        opacity: 1;
        color: #f00;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import LabelInput from '../../components/labelInput.vue'
  import AbstractForm from '../../components/abstractForm.vue'
  import CustomButton from '../../components/customButton.vue'
  import Vue from 'vue'
  import { Checkbox } from 'element-ui'

  Vue.use(Checkbox)

  export default {
    name: 'sign-in',
    data () {
      return {
        contentMargin: 0,
        showText: '登录',
        isLogining: false
      }
    },
    components: {
      'label-input': LabelInput,
      'abstract-form': AbstractForm,
      'custom-button': CustomButton
    },
    methods: {
      clickHandler () {
        this.isLogining = true
        this.showText = '正在登录中···'
      }
    }
  }
</script>
