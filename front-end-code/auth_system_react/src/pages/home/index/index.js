/**
 * @author tanxin
 * @date $
 * @Description:
 */
import React from 'react';
import {} from "antd";
import moment from 'moment';

import style from "./index.module.scss";

// 首页默认显示的
class HomePage extends React.Component {
  render() {
    return <div style={{fontSize: '20px'}} className={style['home-page-content']}>
      {new moment(new Date()).format('YYYY年MM月DD日')}
      <h2>权限管理系统，欢迎您</h2>
    </div>
  }
}

export default HomePage;
