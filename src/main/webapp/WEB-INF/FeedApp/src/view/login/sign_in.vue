<template>
  <div class="sign-in-section">
    <div class="section-head">
      <h1>登录</h1>
    </div>
    <div class="section-body">
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
      <div :style="{'margin-top': (contentMargin+15)+'px'}">
        <el-checkbox>记住密码</el-checkbox>
        <a href="javascript:void(0)"><span class="missing-password">忘记密码</span></a>
      </div>
    </div>
    <div class="section-foot">

    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .sign-in-section {
    height: 120%;
    width: 400px;
    float: left;
    margin-top: -5%;
    background-color: #fff;
    min-height: 485px;

    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;

    -webkit-box-shadow: 2px 2px 56px rgba(0,0,0,.2);
    -moz-box-shadow: 2px 2px 56px rgba(0,0,0,.2);
    box-shadow: 2px 2px 56px rgba(0,0,0,.2);

    .section-head {
      padding-top: 20px;

      h1 {
        font-size: 28px;
        text-align: center;
        letter-spacing: 5px;
        font-weight: 400;
        font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
      }
    }

    .section-body {
      width: 80%;
      padding: 0 10%;

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
  }
</style>

<script type="text/ecmascript-6">
  import LabelInput from '../../components/labelInput.vue'
  import Vue from 'vue'
  import Checkbox from 'element-ui'

  Vue.use(Checkbox)

  export default {
    name: 'sign-in',
    data () {
      return {
        contentMargin: 45
      }
    },
    components: {
      'label-input': LabelInput
    }
  }
</script>
