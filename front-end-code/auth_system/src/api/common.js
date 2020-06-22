/**
 * @author tanxin
 * @date $
 * @Description: 公用的api接口
 */
import request from '../utils/request.js';
import {API} from '../constant/index.js';

let api = {
  // 请求主键
  getPrimaryKey: `/${API}/common/key/`,
};


// 请求主键
export async function getPrimaryKey(num = 1) {
  return request({
    url: `${api.getPrimaryKey}${num}`,
    method: 'get',
  });
}
