/**
 * @author tanxin
 * @date 2019/10/15
 * @desc 组件的状态文件
 **/
import Vue from 'vue';
import vuex from 'vuex';
import {getUserInfo, getMenu} from "../api/user";

import module1 from './module1/index.js'

Vue.use(vuex);

// 需要缓存的页面组件
const cacheList = ['page1', 'page2', 'page3'];

const state = {
  isLogin: false,
  // 导航是否开启的
  navState: true,
  token: '',
  // 需要缓存的数据
  cacheList: [],
  // 用户信息
  userInfo: {},
  // 保存本次打开的历史记录
  navList: [],
  // 用户的菜单
  userMenu: [],
  // 按钮的权限放置
  userButton: {},
};

const getters = {
  get_isLogin: state => state.isLogin,
  // 用户信息
  get_userInfo: state => state.userInfo,
};

const mutations = {
  /*
  * 状态初始化
  * @dec:将一些需要存入sessionStorage的东西放置此处,用户手动刷新的时候统一从session取出，放置store中
  * */
  init() {
    // 是否登录
    const loginState = window.sessionStorage.getItem('isLogin');
    let isLogin = (loginState === '8888' ? true : loginState === '0' ? false : false);
    if (isLogin === true) { //用户已经登录
      Vue.set(state, 'isLogin', true);
      // 用户信息
      Vue.set(state, 'userInfo', JSON.parse(window.sessionStorage.getItem('userInfo')) || {});
      // 设置缓存页面
      Vue.set(state, 'cacheList', [...cacheList]);
      // token
      Vue.set(state, "token", window.sessionStorage.getItem("token"));
      // 历史记录
      Vue.set(state, "navList", JSON.parse(window.sessionStorage.getItem("navList") || "[]"));
      // 菜单
      Vue.set(state, "userMenu", JSON.parse(window.sessionStorage.getItem("userMenu") || "[]"));
      // 菜单
      Vue.set(state, "userButton", JSON.parse(window.sessionStorage.getItem("userButton") || "{}"))

    } else {
      Vue.set(state, 'isLogin', false);
      Vue.set(state, "token", "");
      Vue.set(state, 'userInfo', {});
    }
  },
  set_token(state, val) {
    window.sessionStorage.setItem("token", val);
    Vue.set(state, 'token', val);
  },
  set_isLogin(state, val) {
    window.sessionStorage.setItem('isLogin', '8888');
    Vue.set(state, 'isLogin', val);
    // 设置缓存页面
    Vue.set(state, 'cacheList', [...cacheList]);
  },
  // 用户信息
  set_userInfo(state, val) {
    window.sessionStorage.setItem('userInfo', JSON.stringify(val));
    Vue.set(state, 'userInfo', val);
  },
  // 设置导航的开闭
  set_nav_state(state, val) {
    Vue.set(state, "navState", val);
  },
  // 设置历史记录
  set_nav_list(state, val) {
    window.sessionStorage.setItem("navList", JSON.stringify(val));
    Vue.set(state, "navList", val);
  },
  // 设置菜单
  set_menu(state, val) {
    window.sessionStorage.setItem('userMenu', JSON.stringify(val));
    Vue.set(state, "userMenu", val);
  },
  // 设置按钮的
  set_button(state, val) {
    window.sessionStorage.setItem('userButton', JSON.stringify(val));
    Vue.set(state, "userButton", val);
  },
  // 退出登录
  destroy() {
    window.sessionStorage.removeItem('userInfo');
    window.sessionStorage.removeItem('isLogin');
    window.sessionStorage.removeItem('token');
    window.sessionStorage.removeItem('navList');
    window.sessionStorage.removeItem('userMenu');
    window.sessionStorage.removeItem('userButton');
    Vue.set(state, 'userInfo', {});
    Vue.set(state, 'isLogin', false);
    Vue.set(state, 'token', "");
    Vue.set(state, 'cacheList', []);
    Vue.set(state, 'navList', []);
    Vue.set(state, 'userMenu', []);
    Vue.set(state, 'userButton', {});
  },
};

const actions = {
  /**
   * 请求用户信息
   * @param commit
   */
  getUserInfo({state, commit}) {
    if (JSON.stringify(state.userInfo) !== '{}') {
      return;
    }
    getUserInfo().then(data => {
      commit("set_userInfo", data);
      let list = [];
      data.roles.forEach(item => {
        list.push(item.id);
      });
      // 请求菜单
      getMenu(list).then(result => {
        commit("set_menu", result);
        // 初始化按钮
        let o = {};
        result.forEach(item => {
          if (Array.isArray(item.children)) {
            item.children.forEach(item2 => {
              o[item2.menuUrl] = true;
            });
          }
        });
        commit("set_button", o);
      }).catch(err => {
        console.log(err);
      });
    }).catch(err => {
      console.log(err);
    })
  }
};

export default new vuex.Store({
  state,
  getters,
  mutations,
  actions,
  modules: {
    module1,
  }
});
