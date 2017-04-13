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

<style type="text/scss" lang="scss">
  .form-section {
    height: 100%;
    width: 100%;
    background-color: #fff;
    min-height: 485px;

    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;

    -webkit-box-shadow: 6px 6px 56px rgba(0,0,0,.2);
    -moz-box-shadow: 6px 6px 56px rgba(0,0,0,.2);
    box-shadow: 6px 6px 56px rgba(0,0,0,.2);

    .section-head {
      padding-top: 20px;
      height: 14%;
    }

    .section-body {
      padding: 0 10%;
      height: 65%;
      display: flex;
      justify-content: center;
      flex-direction: column;
    }

    .section-foot {
      height:10%;
      padding: 0 10%;
      display: flex;
      align-items:center;
    }
  }
</style>

<script type="text/ecmascript-6">

  export default {
    name: 'abstract-form'
  }
</script>
