/**
 * @author tanxin
 * @date
 * @Description: 再次封装axios库
 */
import axios from 'axios';
import router from '../router/index.js';
import store from '../store';
//引入消息提示组件
import {Message} from 'element-ui';


/**
 * 用来保存超时的，次数
 */
const OBJ = {};
// 重新请求的次数
const RETRY_NUM = 2;

const _AXIOS = axios.create({
  timeout: 3000,
});

/*
* 请求拦截器
* */
_AXIOS.interceptors.request.use((config) => {
  // 设置请求的次数
  if (OBJ[config.url] === undefined) {
    OBJ[config.url] = 0;
  }

  // 此处可以添加认证的token
  if (store.getters.get_isLogin) {
    config.headers.Authorization = /*'Bearer ' + */store.state.token;
  }

  return config;
}, (err) => {
  console.log("==============REQUEST==ERROR===============");
  // 删除次数
  delete OBJ[err.config.url];
  return Promise.reject(err);
});

/*
* 响应拦截器
* */
_AXIOS.interceptors.response.use(
  (response) => {
    // 清空保存的地址
    delete OBJ[response.config.url];
    let data = response.data;
    /**
     * 判断是否成功
     */
    if (data.code !== 200) {
      Message({
        type: 'error',
        message: data.msg || '本次请求后台出错了'
      });
      return Promise.reject(data);
    }
    return Promise.resolve(data.data);

  }, (error) => {
    // 超时的处理
    if (error.message && error.message.toLocaleLowerCase().indexOf('timeout') > -1) {
      // 重新请求
      if (OBJ[error.config.url] < RETRY_NUM) {
        ++OBJ[error.config.url];
        // 重新请求
        return _AXIOS(error.config);
      } else {
        // 清空
        delete OBJ[error.config.url];
      }
      Message({
        type: 'error',
        message: '请求超时,您可以刷新页面重新请求'
      });
      return Promise.reject(error);
    } else {
      // 对响应错误做点什么
      switch (error.response.status) {
        //处理后台响应的错误
        case 401:
          Message({
            type: 'error',
            message: '请登录后，再操作'
          });
          router.replace({
            path: '/login'
          });
          return Promise.reject(error);
        case 404:
          Message({
            type: 'error',
            message: '接口不存在，请联系管理员'
          });
          return Promise.reject(error);
        case 500:
          Message({
            type: 'error',
            message: '服务器出错啦，请求联系管理员',
          });
          return Promise.reject(error);
        default:
          Message({
            type: 'error',
            message: '未知错误'
          });
          return Promise.reject(error);
      }
    }
  });

export default _AXIOS;


