<template>
  <div class="custom-button-wrapper">
    <el-button
      :type="type"
      @click="clickHandler"
      :loading="loading"
    >
      {{ showText }}
    </el-button>
  </div>
</template>

<style type="text/scss" lang="scss">
  .custom-button-wrapper {
    margin-top: 12px;
    width: 100%;

    .el-button {
      border-radius: 0 !important;
      width: 100%;
      letter-spacing: 5px;

      i {
        letter-spacing: 0;
        margin-right: 2px;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import Vue from 'vue'
  import { Button } from 'element-ui'

  Vue.use(Button)

  export default {
    name: 'custom-button',
    props: {
      type: {
        type: String,
        default: 'primary'
      },
      showText: {
        type: String
      },
      loading: {
        type: Boolean,
        default: false
      }
    },
    methods: {
      clickHandler (event) {
        this.$emit('click', event)
      }
    }
  }
</script>
