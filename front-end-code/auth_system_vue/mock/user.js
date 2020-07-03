module.exports = {
  // 登录
  'POST /login': {
    code: 100,
    data: "sf;sadfj;asjfd;asfjdasjdf;asjfls;fd",
    mgs: 'ok',
  },
  // 获取用户信息
  'GET /user/info': {
    code: 100,
    data: {
      userCode: 'zhangxiaoming',
      userName: '张晓明',
    },
    msg: 'ok',
  }
};
