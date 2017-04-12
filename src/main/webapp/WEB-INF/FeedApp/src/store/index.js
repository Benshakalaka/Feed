import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './actions'
import imgSplit from './modules/imgSplit'

Vue.use(Vuex)

const state = {
  user: {
    username: 'Ben',
    background: '../../../static/img/a_hand.jpeg',
    avatar: '../../../static/img/benAvatar.png',
    describe: '',
    feedAmount: 0,
    fansAmount: 123,
    followAmount: 66
  },
  isLoading: false
}

// const state = {
//   user: null
// }

const getters = {
  userInfo: state => {
    return state.user
  },
  isLoading: state => {
    return state.isLoading
  }
}

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
  modules: {
    imgSplit
  }
})
