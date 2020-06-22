/**
 * @author tanxin
 * @date $
 * @Description: 用户管理模块的api
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  // 添加用户
  addUser: `/${API}/user/add`,
  // 查询用户
  queryUserByPage: `/${API}/user/page`,
};


// 添加用户
export async function addUser(data) {
  return request({
    url: api.addUser,
    method: 'post',
    data,
  });
}

// 分页查询用户信息
export async function queryUserByPage(data) {
  return request({
    url: api.queryUserByPage,
    method: 'post',
    data,
  });
}
