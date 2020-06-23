/**
 * @author tanxin
 * @date $
 * @Description: 菜单权限请求接口
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  // 请求所有的菜单
  queryAllMenu: `/${API}/menu/levelMenu/all`,
  // 删除菜单
  delMenu: `/${API}/menu/`,
};


// 查询所有的菜单权限
export async function queryAllMenu() {
  return request({
    url: api.queryAllMenu,
  });
}

// 删除菜单
export async function deleteMenu(id) {
  return request({
    url: `${api.delMenu}${id}`,
    method: "delete",
  });
}

// 添加菜单
export async function addMenu(data) {
  return request({
    url: `${api.delMenu}`,
    method: 'post',
    data,
  });
}

// 添加菜单
export async function editMenu(data) {
  return request({
    url: `${api.delMenu}`,
    method: 'put',
    data,
  });
}
