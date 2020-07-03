import {SET_NUM} from './reducer.js'

export const numAction = (val) => {
  return {
    type: SET_NUM,
    value: val,
  }
};

// 异步设置num
export const numActionAsync = (val) => {
  return (dispatch) => {
    setTimeout(() => {
      console.log(val);
      dispatch(numAction(val));
    }, 3000);

  }
};