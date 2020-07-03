/**
 * @author tanxin
 * @date $
 * @Description: user 的 reducer
 */
import Storage from '../../utils/storage.js'

// 设置登录信息
export const SET_USER = 'SET_USER';
export const DEl_USER = 'DEl_USER';
// 设置token
export const SET_TOKEN = "SET_TOKEN";
// 设置是否正在登录
export const SET_LOGIN_STATUS = 'SET_LOGIN_STATUS';
// 设置菜单
export const SET_MENU = "SET_MENU";
// 设置菜单
export const SET_BUTTON = "SET_BUTTON";


// 初始化值
let defaultValue = {
  loading: false,
  loginErr: '',
  userInfo: {
    data: {},
    isLogin: false,
    token: '',
  },
  // 菜单
  menu: [],
  // 二级菜单对应对的按钮权限
  button: {},
};

// 从缓存中取
let val = Storage.get('userInfo');
if (val !== null) {
  defaultValue.userInfo = val;
}
let menu = Storage.get("userMenu");
if (menu !== null) {
  defaultValue.menu = menu;
}
let button = Storage.get("userButton");
if (button !== null) {
  defaultValue.button = button;
}


export function userReducer(state = defaultValue, action) {
  switch (action.type) {
    case SET_USER:
      let o = {
        data: action.value,
        token: state.userInfo.token,
        isLogin: true,
      };
      let obj1 = Object.assign({}, state, {userInfo: o});
      Storage.set('userInfo', o);
      return obj1;

    case SET_TOKEN:
      let o2 = {
        data: {},
        token: action.value,
        isLogin: true,
      };
      let obj3 = Object.assign({}, state, {userInfo: o2});
      Storage.set('userInfo', o2);
      return obj3;


    case DEl_USER:
      let obj2 = Object.assign({}, state, {
        userInfo: {
          data: {},
          isLogin: false,
          token: '',
        },
        menu: [],
      });
      Storage.remove('userInfo');
      Storage.remove('userMenu');
      return obj2;

    case SET_LOGIN_STATUS:
      let obj = Object.assign({}, state, {
        loading: action.value,
      });
      return obj;

    case SET_MENU:
      let obj4 = Object.assign({}, state, {
        menu: action.value,
      });
      Storage.set('userMenu', action.value);
      return obj4;

    case SET_BUTTON:
      let obj5 = Object.assign({}, state, {
        button: action.value,
      });
      Storage.set('userButton', action.value);
      return obj5;

    default:
      return state;
  }
}

