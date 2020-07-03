/**
 * @author tanxin
 * @date $
 * @Description: 模块一的状态
 */
import Vue from 'vue';

export default {
  namespaced: true,
  state: {
    //当前账号的船舶
    ship: [],
  },
  getters: {
    //获取当前账号的船舶
    get_ship: state => {
      return state.ship;
    },
  },
  mutations: {
    //设置当前账号的船舶
    set_ship(state, val) {
      Vue.set(state, "ship", val);
    },
  },
  actions: {
    //异步请求当前账号的船舶
    set_ship(context) {
      // 进行异步请求，context.commit('set_ship',result);
    },
  },
}
