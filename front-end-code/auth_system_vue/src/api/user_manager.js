/**
 * @author tanxin
 * @date $
 * @Description: 用户管理模块的api
 */
import request from '../utils/request.js';
import {PROXY_API} from "../const/index.js";

const api = {
  // 添加用户
  addUser: `/${PROXY_API}/user/add`,
  // 编辑用户
  editUser: `/${PROXY_API}/user/edit`,
  // 查询用户
  queryUserByPage: `/${PROXY_API}/user/page`,
  // 删除用户
  delUser: `/${PROXY_API}/user/delete`,
  // 编辑用户的角色
  updateRoleByUserId: `/${PROXY_API}/user/role`,
  // 获取用户的角色
  getRoleByUserId: `/${PROXY_API}/user/role/`,
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
