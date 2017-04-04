<template>
  <div class="feed-item">
    <div class="left-part">
      <a class="feeder-avatar" :style="avatarStyle"></a>
    </div>
    <div class="right-part">
      <div class="item-title">
        <div class="feeder-info">
          <div class="feed-info">
            <h5>{{ feedItem.username }}</h5>
            <p>发表于{{ feedItem.postTime }}</p>
          </div>
        </div>
        <div class="feed-help">
          <el-dropdown trigger="click">
            <i class="fa fa-sort-desc fa-lg"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>屏蔽此状态</el-dropdown-item>
              <el-dropdown-item>取消关注该用户</el-dropdown-item>
              <el-dropdown-item divided>举报</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
      <div class="item-body">
        {{ feedItem.content }}
      </div>
      <div class="item-footer">
        <ul>
          <li><i class="fa fa-reply"></i>{{ feedItem.replyAmount }}</li>
          <li><i class="fa fa-retweet"></i>{{ feedItem.repostAmount }}</li>
          <li><i class="fa fa-heart"></i>{{ feedItem.appreciateAmount }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  .feed-item {
    margin-top: 10px;
    width: 100%;
    padding: 15px 15px 0 15px;
    background-color: #fff;
    border-radius: 3px;
    box-sizing: border-box;
    overflow: hidden;

    .left-part {
      float: left;
      
      .feeder-avatar {
        display: inline-block;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-size: 100%;
      }
    }

    .right-part {
      margin-left: 55px;
      width: calc(100% - 55px);

      .item-title {
        width: 100%;
        display: flex;
        justify-content: space-between;

        .feeder-info {
          .feed-info {
            display: inline-block;
            vertical-align: top;
            margin-left: 5px;

            h5, p {
              margin: 0;
              padding: 0;
            }

            h5 {
              cursor: pointer;
              vertical-align: top;
              margin-top: 6px;
            }

            p {
              font-size: 13px;
              margin-top: 10px;
              color: lighten(#000, 50%);
            }
          }
        }

        .feed-help {
          font-size: 13px;
          i {
            cursor: pointer;
          }
        }
      }

      .item-body {
        line-height: 23px;
        font-size: 14px;
        word-wrap: break-word;
        padding: 5px 0 0 0 ;
      }

      .item-footer {
        width: 100%;
        height: 30px;
        color: lighten(#000, 30%);

        ul {
          list-style: none;
          margin: 0;
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
    }
  }
</style>

<script type="text/ecmascript-6">
  import Vue from 'vue'
  import { Dropdown, DropdownMenu, DropdownItem } from 'element-ui'

  Vue.use(Dropdown)
  Vue.use(DropdownItem)
  Vue.use(DropdownMenu)

  export default {
    data () {
      return {
        feedItem: {
          username: '我了个去去啊',
          postTime: '15分钟前',
          avatar: '../static/img/red.jpeg',
          content: '【民警违停被劝后称辅警“不懂事” 已被停止执行职务】4月2日，四川达州民警朱某某因女儿下车买包子，将车停在路边，辅警王某上前告知不能停车，朱某某称自己是市公安局巡警支队的，是保卫处处长，出示警官证并称执勤辅警“不懂事”…朱某某已被停止执行职务，并向辅警道歉，事件正在进一步调查处理。',
          replyAmount: 1234,
          repostAmount: 5234,
          appreciateAmount: 666
        }
      }
    },
    computed: {
      avatarStyle () {
        return {
          backgroundImage: `url(${this.feedItem.avatar})`
        }
      }
    }
  }
</script>
