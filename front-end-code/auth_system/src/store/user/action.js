/**
 * @author tanxin
 * @date $
 * @Description: user 的 action模块
 */
import {
  SET_TOKEN,
  SET_USER,
  DEl_USER,
  SET_LOGIN_STATUS,
  SET_MENU, SET_BUTTON,
} from "./reducer";

// 请求的api
import {login, getUserInfo} from "../../api/user.js";
import {getMenu} from "../../api/user";

export function set_user(value) {
  return {
    type: SET_USER,
    value,
  }
}

export function set_token(value) {
  return {
    type: SET_TOKEN,
    value,
  }
}

export function del_user() {
  return {
    type: DEl_USER,
  }
}

export function set_loading_status(val) {
  return {
    type: SET_LOGIN_STATUS,
    value: val,
  }
}

export function set_menu(val) {
  return {
    type: SET_MENU,
    value: val,
  }
}

export function set_button(val) {
  return {
    type: SET_BUTTON,
    value: val,
  }
}

// 用户登录
export function userLogin(data, func, errFunc) {
  return dispatch => {
    dispatch(set_loading_status(true));
    login(data).then(token => {
      // 登录成功
      dispatch(set_token(token));
      // 执行回调
      func();
    }).catch(err => {
      console.log(err);
      // 执行失败的回调
      errFunc();
    }).finally(() => {
      dispatch(set_loading_status(false));
    })
  }
}

// 获取用户信息
export function pullUserInfo() {
  return dispatch => {
    getUserInfo().then(data => {
      dispatch(set_user(data));
      let list = [];
      data.roles.forEach(item => {
        list.push(item.id);
      });
      // 请求菜单
      getMenu(list).then(result => {
        dispatch(set_menu(result));
        // 初始化按钮
        let o = {};
        result.forEach(item => {
          if (Array.isArray(item.children)) {
            item.children.forEach(item2 => {
              o[item2.menuUrl] = true;
            });
          }
        });
        dispatch(set_button(o));
      }).catch(err => {
        console.log(err);
      })
    }).catch(err => {
      console.log(err);
    })
  }
}
