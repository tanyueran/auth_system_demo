/**
 * @author tanxin
 * @date $
 * @Description:
 */
import axios from 'axios';
import {message} from "antd";

import history from "../router/history.js"
import store from '../store/index.js'

const request = axios.create({
  timeout: 30000,
});

/**
 * 用来保存超时的，次数
 */
const OBJ = {};
// 重新请求的次数
const RETRY_NUM = 2;

/*
* 请求拦截器
* */
request.interceptors.request.use((config) => {
  // 设置请求的次数
  if (OBJ[config.url] === undefined) {
    OBJ[config.url] = 0;
  }

  // 此处可以添加认证的token
  if (store.getState().userReducer.userInfo.isLogin) {
    config.headers.Authorization = store.getState().userReducer.userInfo.token;
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
request.interceptors.response.use(
  (response) => {
    // 清空保存的地址
    delete OBJ[response.config.url];
    let data = response.data;
    /**
     * 判断是否成功
     */
    if (data.code !== 200) {
      message.error(data.msg || '本次请求后台出错了');
      return Promise.reject(data.data);
    }
    // 对响应数据做点什么
    return Promise.resolve(data.data);

  }, (error) => {
    // 超时的处理
    if (error.message && error.message.toLocaleLowerCase().indexOf('timeout') > -1) {
      // 重新请求
      if (OBJ[error.config.url] < RETRY_NUM) {
        ++OBJ[error.config.url];
        // 重新请求
        return request(error.config);
      } else {
        // 清空
        delete OBJ[error.config.url];
      }
      message.error('请求超时,您可以刷新页面重新请求');
      return Promise.reject(error);
    } else {
      if (error.response == null) {
        message.info('未知错误');
        return Promise.reject(error);
      }
      // 对响应错误做点什么
      switch (error.response.status) {
        //处理后台响应的错误
        case 401:
          message.info('请登录后，再操作');
          history.replace('/login');
          return Promise.reject(error);
        case 404:
          message.info('接口不存在，请联系管理员');
          return Promise.reject(error);
        case 500:
          message.info('服务器出错啦，请求联系管理员');
          return Promise.reject(error);
        default:
          message.info('未知错误');
          return Promise.reject(error);
      }
    }
  });

export default request;
