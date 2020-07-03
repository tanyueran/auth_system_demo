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
  // 编辑用户
  editUser: `/${API}/user/edit`,
  // 查询用户
  queryUserByPage: `/${API}/user/page`,
  // 删除用户
  delUser: `/${API}/user/delete`,
  // 编辑用户的角色
  updateRoleByUserId: `/${API}/user/role`,
  // 获取用户的角色
  getRoleByUserId: `/${API}/user/role/`,
};


// 添加用户
export async function addUser(data) {
  return request({
    url: api.addUser,
    method: 'post',
    data,
  });
}

// 编辑用户
export async function editUser(data) {
  return request({
    url: api.editUser,
    method: 'put',
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

// 删除用户
export async function deleteUserById(id) {
  return request({
    url: `${api.delUser}/${id}`,
    method: 'delete',
  });
}

// 更新用户的角色
export async function updateRoleByUserId(id, roleIdList) {
  return request({
    url: `${api.updateRoleByUserId}/${id}/${roleIdList}`,
    method: 'put',
  });
}

// 获取用户的角色
export async function getRoleByUserId(id) {
  return request({
    url: `${api.getRoleByUserId}${id}`,
  });
}
