<template>
  <div class="sign-up-section">
    <div class="switch-text switch-text-left" v-if="showSwitchText">
      {{ switchText }}<router-link :to="{name:'signin'}" replace>登录</router-link>
    </div>
    <abstract-form>
      <h1 slot="header">注册</h1>

      <label-input
              labelText="邮箱地址"
              placeText="用于登录和密码找回"
              :errorText="emailError"
              @change="emailChangeHandler"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="用户名"
              placeText="用于昵称显示"
              @change="usernameChangeHandler"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="请输入密码"
              inputType="password"
              :errorText="pwdError"
              placeText="不少于8位, 区分大小写"
              @change="pwdChangeHandler"
      ></label-input>
      <label-input
              :topMargin="contentMargin"
              labelText="请再次输入密码"
              inputType="password"
              :errorText="pwd2Error"
              placeText="再次确认您的密码"
              @change="pwd2ChangeHandler"
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

<style type="text/scss" lang="scss">
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
      left: 80px;
      bottom: 20px;
    }

    .switch-text a{
      color: #fff;
      &:hover {
        color: #20a0ff;
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
  import { regist } from '../../api'
  import { emailFormatValidate } from '../../common/js'

  export default {
    name: 'signup',
    data () {
      return {
        contentMargin: 40,
        showText: '注册',
        isRegisting: false,
        emailError: '',
        pwdError: '',
        pwd2Error: '',
        switchText: '已有账号？立即',
        switchTextEnabled: true,
        email: '',
        username: '',
        password: '',
        password2: ''
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
        if (!emailFormatValidate(value)) {
          this.emailError = '邮箱格式输入错误！'
          return
        }

        this.emailError = ''
        this.email = value
      },
      usernameChangeHandler (event, value) {
        this.username = value
      },
      pwdChangeHandler (event, value) {
        this.password = value

        if (this.pwd2Error && value === this.password2) {
          this.pwd2Error = ''
        } else if (this.password2 && this.password2 !== value) {
          this.pwd2Error = '两次密码输入不一致！'
        }
      },
      pwd2ChangeHandler (event, value) {
        if (value !== this.password) {
          this.pwd2Error = '两次密码输入不一致！'
        } else {
          this.pwd2Error = ''
        }

        this.password2 = value
      },
      clickHandler () {
        if (this.emailError || this.pwdError || this.pwd2Error) {
          return
        }

        if (!this.email || !this.username || !this.password || !this.password2) {
          return
        }

        regist({
          email: this.email,
          username: this.username,
          password: this.password
        }).then(data => {
        }).catch(errorMsg => {
        })
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
