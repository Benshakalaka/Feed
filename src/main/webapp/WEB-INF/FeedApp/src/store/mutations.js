import * as types from './mutation-types'

export default {
  [types.LOADING_STATE_CHANGE] (state, { isLoading }) {
    state.isLoading = isLoading
  }
}
