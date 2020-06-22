/**
 * @author tanxin
 * @date $
 * @Description: 用户的登录、个人中心的配置api接口
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  login: `/${API}/login`,
  register: `/${API}/user/register`,
  // 获取用户信息
  getUserInfo: `/${API}/user/getUserInfo`,
};

// 用户登录
export async function login(data) {
  return request({
    method: 'post',
    url: api.login,
    data,
  });
}

// 获取用户信息
export async function getUserInfo() {
  return request({
    method: 'get',
    url: api.getUserInfo,
  })
}

// 注册
export async function register(data) {
  return request({
    method: 'post',
    url: api.register,
    data,
  })
}
