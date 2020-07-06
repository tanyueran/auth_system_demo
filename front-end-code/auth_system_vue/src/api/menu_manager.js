/**
 * @author tanxin
 * @date $
 * @Description: 菜单权限请求接口
 */
import request from '../utils/request.js';
import {PROXY_API} from "../const/index.js";

const api = {
  // 查询所有的菜单
  getAllMenuNoLevel: `/${PROXY_API}/menu/all`,
  // 请求所有的菜单
  queryAllMenu: `/${PROXY_API}/menu/levelMenu/all`,
  // 删除菜单
  delMenu: `/${PROXY_API}/menu/delete`,
  // 添加
  addMenu: `/${PROXY_API}/menu/add`,
  // 编辑
  editMenu: `/${PROXY_API}/menu/edit`,
  // 获取挂载的按钮
  getButtonByMenuId: `/${PROXY_API}/menu/btn`,
  // 更新挂载的按钮
  updateButtonByMenuId: `/${PROXY_API}/menu/btn/update`,
};


// 查询所有的菜单权限
export async function getAllMenuNoLevel() {
  return request({
    url: api.getAllMenuNoLevel,
  });
}

// 查询所有的菜单权限
export async function queryAllMenu() {
  return request({
    url: api.queryAllMenu,
  });
}

// 删除菜单
export async function deleteMenu(id) {
  return request({
    url: `${api.delMenu}/${id}`,
    method: "delete",
  });
}

// 添加菜单
export async function addMenu(data) {
  return request({
    url: `${api.addMenu}`,
    method: 'post',
    data,
  });
}

// 添加菜单
export async function editMenu(data) {
  return request({
    url: `${api.editMenu}`,
    method: 'put',
    data,
  });
}

// 请求菜单下面挂载的按钮
export async function getButtonByMenuId(id) {
  return request({
    url: `${api.getButtonByMenuId}/${id}`,
  });
}

// 更新菜单下面挂载的按钮
export async function updateButtonByMenuId(menuId, btnIdStr) {
  return request({
    url: `${api.updateButtonByMenuId}/${menuId}/${btnIdStr}`,
    method: 'put',
  });
}
