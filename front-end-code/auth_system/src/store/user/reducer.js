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


// 初始化值
let defaultValue = {
  loading: false,
  loginErr: '',
  userInfo: {
    data: {},
    isLogin: false,
    token: '',
  },
};

// 从缓存中取
let val = Storage.get('userInfo');
if (val !== null) {
  defaultValue.userInfo = val;
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
        data: {},
        isLogin: false,
        token: '',
      });
      Storage.remove('userInfo');
      return obj2;

    case SET_LOGIN_STATUS:
      let obj = Object.assign({}, state, {
        loading: action.value,
      });
      return obj;

    default:
      return state;
  }
}

