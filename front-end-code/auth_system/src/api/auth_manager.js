/**
 * @author tanxin
 * @date $
 * @Description: 权限管理模块的api
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  // 分页查询权限
  queryAuth: `/${API}/auth/page`,
  // 删除资源
  delAuth: `/${API}/auth/`,
};


// 分页查询用户信息
export async function queryAuthByPage() {
  return request({
    url: api.queryAuth,
    method: 'post',
  });
}

// 删除资源
export async function deleteAuth(id) {
  return request({
    url: `${api.delAuth}${id}`,
    method: 'delete',
  });
}
