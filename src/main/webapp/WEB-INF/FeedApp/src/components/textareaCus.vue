<template>
  <div class="textarea-section">
    <textarea name="" id="" v-model="value" placeholder="有什么新鲜事想告诉大家?"></textarea>
    <div class="textarea-more">
      <div class="textarea-help">
        <ul>
          <li><i class="fa fa-smile-o fa-lg" style="color: #ffa405;"></i>表情</li>
          <li><i class="fa fa-picture-o fa-lg" style="color: #84c002;"></i>图片</li>
          <li><i class="fa fa-caret-square-o-right fa-lg" style="color: #737e95;"></i>视频</li>
        </ul>
      </div>
      <div class="textarea-post">
        <button class="post-btn" :class="{focus: value}">发布</button>
      </div>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  .textarea-section {
    width: 100%;
    background-color: #fff;
    padding: 6px 6px 0 6px;
    font-size: 0;
    overflow: hidden;
    border-radius: 3px;
    box-sizing: border-box;

    textarea {
      height: 68px;
      width: 100%;
      margin: 0;
      padding: 9px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      font-size: 14px;
      word-wrap: break-word;
      line-height: 18px;
      overflow: hidden;
      outline: none;
      resize: none;
      background-color: #fff;
      
      &:focus {
        border-color: #fa7d3c;
      }
    }

    .textarea-help {
      float: left;

      ul {
        list-style: none;
        margin: 3px 0 0 0;
        padding: 0;
        overflow: hidden;
        font-size: 14px;

        li {
          float: left;
          display: inline-block;
          font-size: 14px;
          cursor: pointer;
          height: 35px;
          line-height: 35px;
          margin-left: 1px;

          &:not(:first-child) {
            margin-left: 20px;
          }

          i {
            margin-right: 3px;
          }
        }
      }
    }

    .textarea-post {
      float: right;
      height: 35px;

      .post-btn {
        height: 30px;
        width: 80px;
        margin-top: 3px;
        font-size: 14px;
        text-align: center;
        border: 0;
        outline: none;
        display: inline-block;
        background-color: #fbbd9e;
        border-radius: 3px;
        color: #fff;
        
        &.focus {
          background-color: #ff8140;
          cursor: pointer;
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  export default {
    data () {
      return {
        value: ''
      }
    },
    components: {
    }
  }
</script>
