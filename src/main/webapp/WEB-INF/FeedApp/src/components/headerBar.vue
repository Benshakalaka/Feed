<template>
  <div class="header-nav-bar">
    <div class="nav-inner">
      <div class="user-related">
        <ul>
          <!--<router-link v-if="isLogin" :to="{ name: 'userhome', params: { username: 'Ben' }}" tag="li" active-class="active">-->
          <router-link v-if="isLogin" to="/user/Ben/profile" tag="li" active-class="active">
            <span>
              <i class="fa fa-user-o fa-lg"></i>
              {{ userInfo.username }}
            </span>
          </router-link>
          <router-link v-else :to="{ name: 'signin'}" tag="li" active-class="active">
              <span>
                <i class="fa fa-user-o fa-lg"></i>
                登录
              </span>
          </router-link>
          <router-link :to="{ name: 'main'}" tag="li" active-class="active">
            <span><i class="fa fa-clock-o fa-lg"></i>首页</span>
          </router-link>
          <router-link :to="{ name: 'discover'}" tag="li" active-class="active">
            <span><i class="fa fa-paper-plane-o fa-lg"></i>发现</span>
          </router-link>
        </ul>
      </div>
      <div class="shortcut-part">
        <div class="search-box">
          <div class="input-wrapper">
            <input type="text" placeholder="搜索 Feed">
            <i class="fa fa-search fa-lg"></i>
          </div>
        </div>
        <div class="simple-icon" v-if="isLogin">
          <i class="fa fa-pencil-square-o fa-lg" title="发布新状态"></i>
        </div>
        <div class="simple-icon" v-if="isLogin">
          <el-dropdown>
            <i class="fa fa-cog fa-lg"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>消息设置</el-dropdown-item>
              <el-dropdown-item>账户绑定</el-dropdown-item>
              <router-link :to="{ name: 'signin'}" tag="li" active-class="active">
                <el-dropdown-item divided>登出</el-dropdown-item>
              </router-link>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
      <div class="loading-spinner" v-if="isLoading">
        <Rainbow></Rainbow>
      </div>
    </div>
  </div>
</template>

<style type="text/scss" lang="scss">
  @import "../common/styles/variables";

  .header-nav-bar {
    width: 100%;
    height: 47px;
    background-color: #fff;
    position: fixed;
    top: 0;
    z-index: 999;

    .nav-inner {
      width: $content-width;
      height: 100%;
      margin: 0 auto;
      text-align: center;

      ul {
        list-style: none;
        height: 47px;
        margin: 0;
        padding: 0;
        overflow: hidden;
        font-size: 0;

        li {
          display: inline-block;
          text-align: center;
          height:47px;
          line-height: 47px;
          transition: height .2s ease;
          border-bottom: 5px solid #4db3ff;
          cursor: pointer;
          font-size: 14px;
          padding: 0 15px;

          &:first-child {
            padding-left: 5px;
          }

          &:hover, &.active {
            height:42px;
          }

          span i{
            margin-right: 5px;
          }
          
          a {
            &, &:hover, &:visited, &:active {
              color: #000;
              text-decoration: none;
            }
          }
        }
      }

      .user-related {
        float: left;
      }

      .shortcut-part {
        float: right;
        font-size: 0;

        & > div {
          display: inline-block;
          height: 47px;
          line-height: 47px;
          font-size: 14px;

          &:not(:first-child) {
            margin-left: 25px;
          }

          &:last-child {
            margin-right: 15px;
          }
        }

        .search-box {
          $icon-width: 35px;

          .input-wrapper {
            height: 25px;
            line-height: 25px;
            width: 250px;
            margin: 8px auto;
            border: 1px solid #ccc;
            border-radius: 15px;
            text-align: left;

            input {
              outline: none;
              width: calc(100% - #{$icon-width});
              padding: 0 15px;
              margin-left: 5px;
              box-sizing: border-box;
              border: 0;
            }
          }
        }

        i {
          cursor: pointer;
        }

        .simple-icon {

        }
      }

      .loading-spinner {
        width: 50px;
        height: 47px;
        line-height: 47px;
        display: inline-block;
        margin: 0 auto;
        position: relative;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters } from 'vuex'
  import Vue from 'vue'
  import { Dropdown, DropdownMenu, DropdownItem } from 'element-ui'
  import Rainbow from './rainbowLoading.vue'

  Vue.use(Dropdown)
  Vue.use(DropdownItem)
  Vue.use(DropdownMenu)

  export default {
    name: 'header-bar',
    data () {
      return {
      }
    },
    props: {
    },
    computed: {
      ...mapGetters([
        'userInfo',
        'isLoading'
      ]),
      isLogin () {
        return this.userInfo !== null
      }
    },
    components: {
      Rainbow
    }
  }
</script>
