<template>
  <div class="sign-in-section">
    <abstract-form>
      <h1 slot="header">登录</h1>

      <label-input
        labelText="邮箱地址"
        placeText="请填写您的邮箱地址"
        :errorText="emailError"
        @change="usernameChange"
      ></label-input>
      <label-input
        :topMargin="contentMargin"
        labelText="请输入密码"
        placeText="请填写您的密码"
        inputType="password"
        @change="userpwdChange"
      ></label-input>
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
      ></custom-button>
    </abstract-form>
    <div class="switch-text switch-text-right" v-if="showSwitchText">
      {{ switchText }}<router-link :to="{name:'signup'}">注册</router-link>
    </div>
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

    .switch-text-right {
      position: absolute;
    }

    .switch-text-right {
      right: 80px;
      bottom: 20px;
    }

    .switch-text a{
      color: #fff;
      &:hover {
        color: #42d885;
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
  import { login } from '../../api'
  import { emailFormatValidate } from '../../utils'

  Vue.use(Checkbox)

  export default {
    name: 'sign-in',
    data () {
      return {
        contentMargin: 60,
        // 按钮文字
        showText: '登录',
        // 切换按钮状态
        isLogining: false,
        // 切换提示文字
        switchText: '还没有账号？点击',
        switchTextEnabled: true,
        username: '',
        emailError: '',
        password: ''
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
      clickHandler () {
        if (this.emailError || !this.username || !this.password) {
          return
        }

        this.isLogining = true
        this.showText = '正在登录中···'
        login({
          email: this.username,
          password: this.password
        }).then(data => {
        }).catch(errorMsg => {
          alert(errorMsg)
        })
      },
      usernameChange (event, value) {
        if (value && !emailFormatValidate(value)) {
          this.emailError = '邮箱格式错误'
          return
        }

        this.emailError = ''
        this.username = value
      },
      userpwdChange (event, value) {
        this.password = value
      }
    },
    activated () {
      // 在每次进入后，初始化一下状态
      this.switchTextEnabled = true
    },
    beforeRouteLeave (to, from, next) {
      // 在路由切换出去后, 不能显示切换提示文本
      // 这个情况本来想父组件传递进来的，但是父组件动画触发 before-leave 事件时，此组件已经不存在了
      this.switchTextEnabled = false
      next()
    },
    computed: {
      showSwitchText () {
        // 显示文本需要三个条件： 1. 文本有内容   2. 不在动画切换过程中
        return this.switchText && this.transitionEnd && this.switchTextEnabled
      }
    }
  }
</script>
