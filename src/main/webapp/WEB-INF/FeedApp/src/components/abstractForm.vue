<template>
  <div class="form-section">
    <div class="section-head">
      <slot name="header"></slot>
    </div>
    <div class="section-body">
      <slot></slot>
    </div>
    <div class="section-foot">
      <slot name="foot"></slot>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .form-section {
    height: 100%;
    width: 100%;
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
      height: 15%;
    }

    .section-body {
      width: 80%;
      height: 65%;
      margin: 0 auto;
      display: flex;
      justify-content: space-around;
      flex-direction: column;
    }
  }
</style>

<script type="text/ecmascript-6">

  export default {
    name: 'abstract-form'
  }
</script>
