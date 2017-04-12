import * as types from '../mutation-types'
import Vue from 'vue'

const state = {
  // 最大步骤数
  // 第一步: 描点
  MAX_STEP: 4,
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
  // 第一步, 当前在划分的片区
  oneCurrentAreaIndex: -1,
  // 第一步, 当前在划分的片区的第几个坐标
  oneCurrentDataDisplayFocusIndex: -1,
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
  oneCurrentAreaIndex: state => state.oneCurrentAreaIndex,
  oneCurrentDataDisplayFocusIndex: state => state.oneCurrentDataDisplayFocusIndex,
  areasInfo: state => state.areasInfo,
  dataDisplayOne: state => (state.areasInfo && state.areasInfo[state.oneCurrentAreaIndex] ? state.areasInfo[state.oneCurrentAreaIndex].area_coords : [])
}

const mutations = {
  [types.INIT_AREA_INFO] (state) {
    const count = state.count
    if (state.areasInfo) {
      const areasInfo = state.areasInfo
      areasInfo.length > count && areasInfo.splice(count, areasInfo.length - count)
      areasInfo.length < count && Array.from({length: count - areasInfo.length}).map(() => {
        areasInfo.push({
          area_coords: [],
          area_item: [],
          area_active: []
        })
      })
    } else {
      state.areasInfo = Array.from({length: count}).map(() => {
        return {
          area_coords: [],
          area_item: [],
          area_active: []
        }
      })
    }
  },
  [types.SET_IMAGE_URL] (state, { imgUrl }) {
    console.log('what????????????')
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
  [types.ONE_SET_CURRENT_AREA_INDEX] (state, { index }) {
    state.oneCurrentAreaIndex = index
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
  }
}

export default {
  state,
  getters,
  mutations
}
