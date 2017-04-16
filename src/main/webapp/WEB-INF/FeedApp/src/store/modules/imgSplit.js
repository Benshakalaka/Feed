import * as types from '../mutation-types'
import { getRelativeMousePosi } from '../../common/js'
import Vue from 'vue'

const state = {
  // 最大步骤数
  // 第一步: 描点
  MAX_STEP: 2,
  // 进度颜色
  STEP_COLOR: ['#47EBEB', '#FFEB3B', '#47EB47', '#EB4747'],
  // 当前进度
  step: 1,
  // 分拨中心图片
  imgUrl: '',
  // 分拨中心背景图
  bgUrl: '',
  // 图片宽度
  width: 0,
  // 几个片区
  count: 0,
  // 浏览器滚动条宽度
  scrollBarWidth: 0,
  // 获取鼠标相对于容器的定位的函数
  getRelativeMousePosi: null,
  // 第一步, 当前在划分的片区
  oneCurrentAreaIndex: -1,
  // 第一步, 当前在划分的片区的第几个坐标
  oneCurrentDataDisplayFocusIndex: -1,
  // 第二步, 当前在切割的片区
  twoCurrentAreaIndex: -1,
  // 第二步, 当前要修改的坐标, 0或3
  twoCurrentDirection: -1,
  // 最终数据集合
  areasInfo: null
}

const getters = {
  maxStep: state => state.MAX_STEP,
  stepColor: state => state.STEP_COLOR,
  step: state => state.step,
  imgUrl: state => state.imgUrl,
  bgUrl: state => state.bgUrl,
  width: state => state.width,
  count: state => state.count,
  scrollBarWidth: state => state.scrollBarWidth,
  oneCurrentAreaIndex: state => state.oneCurrentAreaIndex,
  oneCurrentDataDisplayFocusIndex: state => state.oneCurrentDataDisplayFocusIndex,
  areasInfo: state => state.areasInfo,
  dataDisplayOne: state => (state.areasInfo && state.areasInfo[state.oneCurrentAreaIndex] ? state.areasInfo[state.oneCurrentAreaIndex].area_coords : []),
  twoCurrentAreaIndex: state => state.twoCurrentAreaIndex,
  getRelativeMousePosi: state => state.getRelativeMousePosi,
  dataDisplayTwo: state => (state.areasInfo && state.areasInfo[state.twoCurrentAreaIndex] ? state.areasInfo[state.twoCurrentAreaIndex].area_active : []),
  twoCurrentDirection: state => state.twoCurrentDirection,
  // 根据第二步的片区索引获取第一步得到的数据
  dataDisplayOneInTwo: state => (state.areasInfo && state.areasInfo[state.twoCurrentAreaIndex] ? state.areasInfo[state.twoCurrentAreaIndex].area_coords : [])
}

const mutations = {
  [types.INIT_AREA_INFO] (state) {
    const count = state.count
    if (state.areasInfo) {
      const areasInfo = state.areasInfo
      areasInfo.length > count && areasInfo.splice(count, areasInfo.length - count)
      areasInfo.length < count && Array.from({length: count - areasInfo.length}).map(() => {
        areasInfo.push({
          // 第一步获取分拨中心哥哥片区坐标
          area_coords: [],
          // 第四步获取显示信息定位
          area_item: [],
          // 第二步获取背景图片每个片区四个角坐标, 顺序是左上, 右上, 左下, 右下
          // [{left: ?, top: ?}, {left: ?, top: ?}, {left: ?, top: ?}, {left: ?, top: ?}]
          // 第三步获取图片相对于主图定位
          area_active: null
        })
      })
    } else {
      state.areasInfo = Array.from({length: count}).map(() => {
        return {
          area_coords: [],
          area_item: [],
          area_active: null
        }
      })
    }
  },
  [types.SET_IMAGE_URL] (state, { imgUrl }) {
    state.imgUrl = imgUrl
  },
  [types.SET_BACKGROUND_URL] (state, { bgUrl }) {
    state.bgUrl = bgUrl
  },
  [types.SET_IMAGE_WIDTH] (state, { width }) {
    state.width = width
  },
  [types.SET_AREA_COUNT] (state, { count }) {
    state.count = count
  },
  [types.SET_SCROLL_WIDTH] (state, { scrollBarWidth }) {
    state.scrollBarWidth = scrollBarWidth
  },
  [types.ONE_SET_CURRENT_AREA_INDEX] (state, { index }) {
    state.oneCurrentAreaIndex = index
  },
  [types.ONE_CANCEL_CURRENT_AREA_INDEX] () {
    state.oneCurrentAreaIndex = -1
  },
  [types.NAV_PREV_GO] (state) {
    state.step = Math.max(1, state.step - 1)
  },
  [types.NAV_NEXT_GO] (state) {
    if (state.imgUrl && state.bgUrl && state.count > 0) {
      state.step = Math.min(state.MAX_STEP, state.step + 1)
    }
  },
  [types.SET_RELATIVE_MOUSE_POSI_FUNC] (state, {wrapperEle}) {
    state.getRelativeMousePosi = (event, relativeEle) => {
      return getRelativeMousePosi(event, relativeEle, wrapperEle)
    }
  },
  // 步骤一: 为某个片区添加坐标
  [types.ONE_ADD_OR_UPDATE_CURRENT_AREA_COORDS] (state, { coord }) {
    if (state.areasInfo && state.oneCurrentAreaIndex > -1 && state.oneCurrentAreaIndex < state.areasInfo.length) {
      // 修改
      if (state.oneCurrentDataDisplayFocusIndex > -1) {
        if (state.oneCurrentDataDisplayFocusIndex < state.areasInfo[state.oneCurrentAreaIndex].area_coords.length) {
          Vue.set(state.areasInfo[state.oneCurrentAreaIndex].area_coords, state.oneCurrentDataDisplayFocusIndex, coord)
        }
      } else {
        // 添加
        state.areasInfo[state.oneCurrentAreaIndex].area_coords.push(coord)
      }
    }
  },
  [types.ONE_DELETE_CURRENT_AREA_COORD] (state, { index }) {
    if (state.areasInfo && state.oneCurrentAreaIndex > -1 && state.oneCurrentAreaIndex < state.areasInfo.length) {
      state.areasInfo[state.oneCurrentAreaIndex].area_coords.splice(index, 1)
    }
  },
  [types.ONE_SET_CURRENT_AREA_COORD_FPCUS_INDEX] (state, { index }) {
    state.oneCurrentDataDisplayFocusIndex = index
  },
  [types.ONE_CANCEL_CURRENT_AREA_COORD_FPCUS_INDEX] (state) {
    state.oneCurrentDataDisplayFocusIndex = -1
  },
  [types.TWO_SET_CURRENT_AREA_INDEX] (state, { index }) {
    state.twoCurrentAreaIndex = index
  },
  [types.TWO_CANCEL_CURRENT_AREA_INDEX] () {
    state.twoCurrentAreaIndex = -1
  },
  [types.TWO_INIT_AREAS_DATA] (state, {height}) {
    // area_active数据在进入第二步第三个模块mounted之后才初始化, 片区数量count变少无所谓, 数量变多那就要在进入第二步时继续处理
    state.areasInfo.map(item => {
      if (!item.area_active) {
        item.area_active = [
          {x: 0, y: 0},
          {x: state.width, y: 0},
          {x: 0, y: height},
          {x: state.width, y: height}
        ]
      }
    })
  },
  [types.TWO_CURRENT_AREA_DIRECTON_COORD_CHANGE] (state, {direction, x, y}) {
    const dire = (direction === undefined ? state.twoCurrentDirection : direction)
    if (state.areasInfo) {
      const currAreaActive = state.areasInfo[state.twoCurrentAreaIndex].area_active
      currAreaActive[dire].x = x
      currAreaActive[dire].y = y

      // 改动左上或右下坐标, 将带动右上和左下坐标的变动
      currAreaActive[1].y = currAreaActive[0].y
      currAreaActive[1].x = currAreaActive[3].x

      currAreaActive[2].x = currAreaActive[0].x
      currAreaActive[2].y = currAreaActive[3].y
    }
  },
  [types.TWO_CHANGE_CURRENT_DIRECTION] (state, {index}) {
    state.twoCurrentDirection = index
  },
  [types.TWO_CANCEL_CHANGE_CURRENT_DIRECTION] (state) {
    state.twoCurrentDirection = -1
  }
}

export default {
  state,
  getters,
  mutations
}
