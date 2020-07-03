/**
 * @author tanxin
 * @date $
 * @Description: 角色请求接口
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  // 请求所有的角色
  queryAllRole: `/${API}/role/all`,
  // 添加角色
  addRole: `/${API}/role/`,
  // 删除角色
  delRoleById: `/${API}/role`,
  // 更新角色的菜单权限
  updateMenuByRoleId: `/${API}/role/menu`,
};


// 查询所有的角色
export async function getAllRoles() {
  return request({
    url: api.queryAllRole,
  });
}

// 添加角色
export async function addRole(data) {
  return request({
    url: api.addRole,
    data,
    method: 'post'
  });
}

// 编辑角色
export async function editRole(data) {
  return request({
    url: api.addRole,
    data,
    method: 'put'
  });
}

// 删除角色
export async function delRoleById(id) {
  return request({
    url: `${api.delRoleById}/${id}`,
    method: 'delete',
  })
}

// 更新角色的菜单权限
export async function updateMenuByRoleId(id, menuIdList) {
  return request({
    url: `${api.updateMenuByRoleId}/${id}/${menuIdList}`,
    method: 'put',
  })
}

// 获取角色的菜单
export async function getMenuByRoleid(id) {
  return request({
    url: `${api.updateMenuByRoleId}/${id}`,
    method: 'get',
  })
}
