<template>
  <div class="sign-up-section">
    <div class="switch-text switch-text-left" v-if="showSwitchText">
      {{ switchText }}<router-link :to="{name:'signin'}">登陆</router-link>
    </div>
    <abstract-form>
      <h1 slot="header">注册</h1>

      <label-input
              :topMargin="contentMargin * 2"
              labelText="邮箱地址"
              placeText="用于登录和密码找回"
              :errorText="emailError"
              @change="emailChangeHandler"
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

    .switch-text-left {
      right: 480px;
      bottom: 20px;
    }

    .switch-text-left {
      position: absolute;
    }

    .switch-text-left {
      right: 480px;
      bottom: 20px;
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
    name: 'signup',
    data () {
      return {
        contentMargin: 0,
        showText: '注册',
        isRegisting: false,
        emailError: '',
        switchText: '已有账号？立即',
        switchTextEnabled: true
      }
    },
    props: {
      transitionEnd: {
        type: Boolean,
        default: false
      }
    },
    components: {
      'label-input': LabelInput,
      'abstract-form': AbstractForm,
      'custom-button': CustomButton
    },
    methods: {
      emailChangeHandler (event, value) {
        if (value.length > 5) {
          this.emailError = '邮箱格式输入错误！'
        } else {
          this.emailError = ''
        }
      }
    },
    computed: {
      showSwitchText () {
        return this.switchText && this.transitionEnd && this.switchTextEnabled
      }
    },
    beforeRouteLeave (to, from, next) {
      this.switchTextEnabled = false
      next()
    },
    activated () {
      this.switchTextEnabled = true
    }
  }
</script>
