<template>
  <div class="sign-up-section">
    <abstract-form>
      <h1 slot="header">注册</h1>

      <label-input
              :topMargin="contentMargin * 2"
              labelText="邮箱地址"
              placeText="用于登录和密码找回"
      ></label-input>
      <label-input
              :topMargin="contentMargin * 2"
              labelText="用户名"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="请输入密码"
              inputType="password"
              placeText="不少于8位, 区分大小写"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="请再次输入密码"
              inputType="password"
              placeText="再次确认您的密码"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="请再次输入密码"
              inputType="password"
              placeText="再次确认您的密码"
      ></label-input>

      <custom-button
              type="success"
              slot="foot"
              :showText="showText"
              :loading="isRegisting"
              @click="clickHandler"
      ></custom-button>
    </abstract-form>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .sign-up-section {
    height: 140%;
    width: 400px;
    float: right;
    margin-top: -10%;

    h1 {
      font-size: 28px;
      text-align: center;
      letter-spacing: 5px;
      font-weight: 400;
    }
  }
</style>

<script type="text/ecmascript-6">
  import LabelInput from '../../components/labelInput.vue'
  import AbstractForm from '../../components/abstractForm.vue'
  import CustomButton from '../../components/customButton.vue'
  import Vue from 'vue'
  import { Checkbox } from 'element-ui'

  export default {
    data () {
      return {
        contentMargin: 0,
        showText: '注册',
        isRegisting: false
      }
    },
    components: {
      'label-input': LabelInput,
      'abstract-form': AbstractForm,
      'custom-button': CustomButton
    }
  }
</script>
