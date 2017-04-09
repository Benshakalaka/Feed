<template>
  <div class="discover-content">
    <div class="discover-inner-content">
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
        <div class="one-part" id="one-chart" ref="one"></div>
        <div class="two-part" id="two-chart" ref="two"></div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" rel="stylesheet/scss">
  @import "../../common/styles/_variables";
  $main-content-top: 58px;

  .discover-content {
    width: 100%;
    height: calc(100% - #{$main-content-top});
    margin-top: $main-content-top;
    overflow: auto;
  }

  .discover-inner-content {
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
      width: 790px;

      & > div{
        padding: 20px;
        margin-top: 10px;
        background-color: #fff !important;
        border-radius:5px;
      }

      .one-part {
        width: 750px;
        height: 400px;
        margin: 0 auto;
      }

      .two-part {
        width: 750px;
        height: 400px;
      }
    }
  }
</style>

<script type="text/ecmascript-6">
  import { mapGetters } from 'vuex'
  import FeedItem from '../../components/feedItem.vue'
  import CustomTextarea from '../../components/textareaCus.vue'
  import echarts from 'echarts'

  export default {
    name: 'discover',
    data () {
      return {
        value: [
          {'date': '2017-03-13', 'collect': 11952246, 'sign': 12134306},
          {'date': '2017-03-14', 'collect': 11908716, 'sign': 11443790},
          {'date': '2017-03-15', 'collect': 11361574, 'sign': 11910818},
          {'date': '2017-03-16', 'collect': 11211263, 'sign': 11865573},
          {'date': '2017-03-17', 'collect': 11128254, 'sign': 11620285},
          {'date': '2017-03-18', 'collect': 10211503, 'sign': 10765957},
          {'date': '2017-03-19', 'collect': 10145938, 'sign': 10464252},
          {'date': '2017-03-20', 'collect': 11446538, 'sign': 11678545},
          {'date': '2017-03-21', 'collect': 12315671, 'sign': 10713943},
          {'date': '2017-03-22', 'collect': 12135098, 'sign': 11359836},
          {'date': '2017-03-23', 'collect': 11924557, 'sign': 11967931},
          {'date': '2017-03-24', 'collect': 11350129, 'sign': 12176770},
          {'date': '2017-03-25', 'collect': 10377998, 'sign': 11084961},
          {'date': '2017-03-26', 'collect': 9992005, 'sign': 10975040},
          {'date': '2017-03-27', 'collect': 11647908, 'sign': 12034334},
          {'date': '2017-03-28', 'collect': 11805132, 'sign': 10958844},
          {'date': '2017-03-29', 'collect': 11630719, 'sign': 11390465},
          {'date': '2017-03-30', 'collect': 11304737, 'sign': 11553360},
          {'date': '2017-03-31', 'collect': 11007923, 'sign': 11679673},
          {'date': '2017-04-01', 'collect': 10349477, 'sign': 11472045},
          {'date': '2017-04-02', 'collect': 9423569, 'sign': 10124383},
          {'date': '2017-04-03', 'collect': 9544421, 'sign': 9614431},
          {'date': '2017-04-04', 'collect': 8580165, 'sign': 9378081},
          {'date': '2017-04-05', 'collect': 12563870, 'sign': 10836639},
          {'date': '2017-04-06', 'collect': 13231984, 'sign': 9858441}
        ]
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
    },
    mounted () {
      const oneChart = echarts.init(this.$refs.one)
      oneChart.setOption({
        title: {
          text: '揽件签收量图表',
          subtext: '测试图表'
        },
        xAxis: [
          {
            type: 'category',
            data: this.value.map(item => {
              return item.date
            })
          }
        ],
        yAxis: [
          {
            type: 'value',
            min: 7000000
          }
        ],
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['揽件量', '签收量']
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {show: true, readOnly: false},
            dataZoom: {show: true},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '揽件量',
            type: 'bar',
            data: this.value.map(item => {
              return item.collect
            }),
            markPoint: {
              data: [
                {type: 'max', name: '最大揽件量'}
              ]
            }
          }, {
            name: '签收量',
            type: 'bar',
            data: this.value.map(item => {
              return item.sign
            }),
            markPoint: {
              data: [
                {type: 'max', name: '最大签收量'}
              ]
            }
          }
        ]
      })

      const twoChart = echarts.init(this.$refs.two)
      twoChart.setOption({
        title: {
          text: '揽件签收量图表',
          subtext: '测试图表'
        },
        xAxis: [
          {
            type: 'category',
            data: this.value.map(item => {
              return item.date
            })
          }
        ],
        yAxis: [
          {
            type: 'value',
            min: 7000000
          }
        ],
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['揽件量', '签收量']
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {show: true, readOnly: false},
            dataZoom: {show: true},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '揽件量',
            type: 'bar',
            data: this.value.map(item => {
              return item.collect
            }),
            markPoint: {
              data: [
                {type: 'max', name: '最大揽件量'}
              ]
            }
          }, {
            name: '签收量',
            type: 'line',
            data: this.value.map(item => {
              return item.sign
            }),
            markPoint: {
              data: [
                {type: 'max', name: '最大签收量'}
              ]
            }
          }
        ]
      })
    }
  }
</script>
