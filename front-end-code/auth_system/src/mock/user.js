import Mock from 'mockjs';
import {ok} from "./_utils";

Mock.mock('/api/login', function () {
  return ok({
    id: '234',
    username: '234',
    isLogin: true,
    token: '234',
  })
});
