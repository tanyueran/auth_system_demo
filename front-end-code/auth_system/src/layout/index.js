/**
 * @author tanxin
 * @date $
 * @Description: 布局页面
 */
import React from 'react';
import {withRouter, Route, Redirect} from 'react-router'
import {connect} from 'react-redux';

import {del_user} from '../store/user/action.js';
// 路由白名单
import routeWhiteList from '../router/whiteList.js';

import {
  Layout,
  Avatar,
  Row,
  Col,
  Dropdown,
  Menu,
  Tooltip,
} from 'antd';

import {
  UserOutlined,
  CaretDownOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  QuestionCircleOutlined,
} from '@ant-design/icons'

import MNav from '../components/MNav.js';

import '../pages/home/index.scss';
import {pullUserInfo} from "../store/user/action";

import logo from '../images/logo.jpg';


class IndexPage extends React.Component {

  static stateToProps(state) {
    return {
      user: state.userReducer
    }
  }

  state = {
    collapsed: false,
  };

  /**
   *  切换
   */
  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };

  /**
   * 退出登录
   * */
  logoutHandler = () => {
    this.props.dispatch(del_user());
    this.props.history.replace("/login");
  };

  componentDidMount() {
    // 判断是否登录
    if (!this.props.user.userInfo.isLogin) {
      this.props.history.replace('/login');
    } else {
      if (!this.props.user.userInfo.data.userCode) {
        // 获取用户信息
        this.props.dispatch(pullUserInfo());
      }
    }
  }

  render() {
    return (
      <Layout className="app-wrapper">
        <Layout.Sider theme={'light'} trigger={null} collapsible collapsed={this.state.collapsed}>
          <div className="logo" style={{cursor: 'pointer'}} onClick={() => {
            this.props.history.push('/')
          }}>
            <img alt={"logo"} width={40} src={logo}/>
            {
              this.state.collapsed ? "" : "权限管理系统"
            }
          </div>
          <MNav/>
        </Layout.Sider>
        <Layout className={"app-content-wrapper"}>
          <Layout.Header style={{padding: "0 10px",}}>
            <Row type={"flex"}>
              <Col span={12}>
                {
                  this.state.collapsed ?
                    <MenuUnfoldOutlined onClick={this.toggle}/> :
                    <MenuFoldOutlined onClick={this.toggle}/>
                }
              </Col>
              <Col push={8} span={4} style={{justifyContent: 'center', display: 'flex', alignItems: 'center'}}>
                <Tooltip title={"使用说明"}>
                  <QuestionCircleOutlined style={{fontSize: '18px'}}/>
                </Tooltip>
                {/*头像*/}
                <Avatar style={{margin: "0 1em"}} size={"small"} icon={<UserOutlined/>}/>
                <Dropdown overlay={
                  <Menu theme={"light"}>
                    <Menu.Item onClick={() => {
                      this.props.history.push('/home/personCenter/personInfo')
                    }}>个人中心</Menu.Item>
                    <Menu.Item onClick={this.logoutHandler}>退出</Menu.Item>
                  </Menu>
                }>
                  <a href={"/#"} onClick={(e) => {
                    e.preventDefault();
                  }} style={{fontSize: "16px",}}>
                    {this.props.user.userInfo.data.userName}
                    <CaretDownOutlined style={{marginLeft: "5px"}}/>
                  </a>
                </Dropdown>
              </Col>
            </Row>
          </Layout.Header>
          <Layout.Content className={"app-content-wrapper_box"}
                          style={{overflow: 'auto', margin: '20px 10px'}}>
            {/*配置子路由*/}
            {
              this.props.routes.map((item, i) =>
                <Route exact={item.exact} path={item.path} key={i} render={props => {
                  // 白名单的直接放
                  if (routeWhiteList.indexOf(props.match.path) !== -1) {
                    return <item.component {...props}/>
                    // 没有权限的
                  } else if (this.props.user.button[props.match.path] === undefined) {
                    return <Redirect to={"/home/401"}/>
                  }
                  return <item.component {...props}/>
                }}/>
              )
            }
            {/* 配置404*/}
            {
              this.props.routes.findIndex(item => item.path === this.props.location.pathname) === -1 &&
              < Redirect to="/home/404"/>
            }
          </Layout.Content>
          <Layout.Footer className={"text-center"} style={{margin: "0 10px",}}>
            唯有阳光和绿茶最搭了！
          </Layout.Footer>
        </Layout>
      </Layout>
    );
  }
}

/*
* 通过connect 引入store
* */
export default connect(IndexPage.stateToProps)(withRouter(IndexPage));
