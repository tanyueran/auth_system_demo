export const SET_NUM = 'SET_NUM';

let _state = {
  num: 0,
};


export const numReducer = (state = _state, action) => {
  switch (action.type) {
    case SET_NUM:
      return Object.assign({}, state, {num: action.value});
    default:
      return state;
  }
};