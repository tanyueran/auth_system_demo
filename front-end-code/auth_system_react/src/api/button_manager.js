/**
 * @author tanxin
 * @date $
 * @Description: 按钮权限请求接口
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

const api = {
  // 请求所有的按钮
  queryAllBtn: `/${API}/btn/all`,
  // 删除按钮
  delBtn: `/${API}/btn/`,
};


// 查询所有的按钮权限
export async function queryAllBtn() {
  return request({
    url: api.queryAllBtn,
  });
}

// 删除按钮
export async function deleteBtn(id) {
  return request({
    url: `${api.delBtn}${id}`,
    method: "delete",
  });
}

// 添加按钮
export async function addBtn(data) {
  return request({
    url: `${api.delBtn}`,
    method: 'post',
    data,
  });
}

// 添加按钮
export async function editBtn(data) {
  return request({
    url: `${api.delBtn}`,
    method: 'put',
    data,
  });
}
