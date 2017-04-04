<template>
  <div class="main-content">
    <div class="inner-content">
      <div class="first-section">
        <div class="user-info">
          <router-link class="user-background" :style="backgroundImg" :to="{ name: 'userhome', params: { username: 'Ben' }}"></router-link>
          <div class="user-detail">
            <router-link class="user-avatar" :style="avatarImg" :to="{ name: 'userhome', params: { username: 'Ben' }}"></router-link>
            <div class="user-name">
              <router-link :to="{ name: 'userhome', params: { username: 'Ben' }}" tag="h5">{{ userInfo.username }}</router-link>
              <p>{{ userInfo.describe || '这个人很懒,什么也没留下'  }}</p>
            </div>
          </div>
          <ul class="user-feed">
            <router-link tag="li" :to="{ name: 'userhome', params: { username: 'Ben' }}"><b>{{ userInfo.feedAmount }}</b><br>状态</router-link>
            <router-link tag="li" to="/user/Ben/fans"><b>{{ userInfo.fansAmount }}</b><br>粉丝</router-link>
            <router-link tag="li" to="/user/Ben/follows"><b>{{ userInfo.followAmount }}</b><br>关注</router-link>
          </ul>
        </div>
      </div>
      <div class="second-section">
        <custom-textarea></custom-textarea>
        <feed-item></feed-item>
        <feed-item></feed-item>
        <feed-item></feed-item>
        <feed-item></feed-item>
        <feed-item></feed-item>
        <feed-item></feed-item>
      </div>
      <div class="third-section">
        <ul class="simple-bar">
          <li>首页</li>
          <li>我的收藏</li>
          <li>我的赞</li>
          <li class="divider"></li>
          <li>热门状态</li>
          <li>热门视频</li>
          <li class="divider"></li>
          <li>特别关注</li>
          <li>悄悄关注</li>
          <li>好友圈</li>
          <li>特别关注</li>
          <li>悄悄关注</li>
          <li class="divider"></li>
          <li>好友圈</li>
          <li>特别关注</li>
          <li>悄悄关注</li>
          <li>好友圈</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  @import "../../common/styles/variables";
  $main-content-top: 58px;

  .main-content {
    width: 100%;
    height: calc(100% - #{$main-content-top});
    margin-top: $main-content-top;
    overflow: auto;
  }

  .inner-content {
    width: $content-width;
    margin: 0 auto;

    .first-section {
      float: left;
      width: 290px;
      margin-right: 10px;

      .user-info {
        background-color: #fff;
        border-radius: 5px;
        overflow: hidden;

        .user-background {
          display: block;
          width: 100%;
          height: 112px;
          background-position: 0 center;
          background-repeat: no-repeat;
          background-size: 100%;
        }

        .user-detail {
          .user-avatar {
            display: inline-block;
            border-radius: 5px;
            width: 75px;
            height: 75px;
            background-size: 100%;
            margin: -32px 0 0 8px;
          }

          .user-name {
            display: inline-block;
            height: 33px;
            margin-left: 10px;

            h5, p {
              margin: 0;
              padding: 0;
              line-height: 20px;
            }
            
            h5 {
              cursor: pointer;
            }

            p {
              font-size: 13px;
            }
          }
        }

        .user-feed {
          width: 100%;
          height: 70px;
          list-style: none;
          margin: 0;
          padding: 0;
          display: flex;
          justify-content: space-around;
          text-align: center;
          align-items: center;
          font-size: 14px;
          color: lighten(#000, 35%);

          li {
            display: block;
            width: 32%;
            height: 40px;
            cursor: pointer;

            b{
              font-size: 17px;
            }

            &:not(:last-child){
              border-right: 2px solid lighten(#000, 50%);
            }

            &:hover b{
              color: #000;
            }
          }
        }
      }
    }

    .second-section {
      float: left;
      width: 590px;
      height: 1000px;
    }

    .third-section {
      float: left;
      width: 190px;
      margin-left: 10px;
      background-color: #fff;
      font-size: 15px;

      .simple-bar {
        list-style: none;
        margin: 0;
        padding: 0;

        li {
          display: block;
          height: 35px;
          line-height: 35px;
          width: 100%;
          box-sizing: border-box;
          padding-left: 20px;
          border: 0;
          cursor: pointer;
          
          &:hover {
            background-color: #f5f8fa;
          }

          &.divider {
            height: 0;
            border: 1px solid #f5f8fa;
          }
        }
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters } from 'vuex'
  import FeedItem from '../../components/feedItem.vue'
  import CustomTextarea from '../../components/textareaCus.vue'

  export default {
    data () {
      return {
      }
    },
    computed: {
      ...mapGetters([
        'userInfo'
      ]),
      backgroundImg () {
        return this.userInfo ? {
          backgroundImage: `url(${this.userInfo.background ? this.userInfo.background : ''})`
        } : {}
      },
      avatarImg () {
        return this.userInfo ? {
          backgroundImage: `url(${this.userInfo.avatar ? this.userInfo.avatar : ''})`
        } : {}
      }
    },
    components: {
      'feed-item': FeedItem,
      'custom-textarea': CustomTextarea
    }
  }
</script>
