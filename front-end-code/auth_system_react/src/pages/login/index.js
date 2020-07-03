/**
 * @author tanxin
 * @date $
 * @Description: 登录页
 */
import React from 'react';
import {connect} from "react-redux";
import {
  message,
  Form,
  Input,
  Button,
  Checkbox,
  Typography,
} from 'antd';

import {
  UserOutlined,
  LockOutlined,
} from '@ant-design/icons'

import './index.scss';

import {
  userLogin
} from '../../store/user/action.js'

import bgImg3 from '../../images/bg/bg3.jfif'


class LoginPage extends React.Component {
  static stateToProps(state) {
    return {
      user: state.userReducer
    }
  }

  state = {
    form: React.createRef(),
  };

  loginHandler = values => {
    this.props.dispatch(userLogin(values, () => {
      message.success('登录成功!');
      this.props.history.replace("/");
    }, () => {
      // 清空账号密码
      this.state.form.current.resetFields();
    }));
  };

  render() {
    return (
      <div className={"login-wrapper"}>
        <img src={bgImg3} alt={"背景"}/>
        <div className='login-content'>
          <Typography.Title>
            欢迎，权限DEMO
          </Typography.Title>
          <Form ref={this.state.form} onFinish={this.loginHandler}>
            <Form.Item name={"username"} initialValue={"manager"} rules={[{required: true, message: '请输入登录账号!'}]}>
              <Input
                autoComplete={"off"}
                prefix={<UserOutlined style={{color: 'rgba(0,0,0,.25)'}}/>}
                placeholder="账号"
              />
            </Form.Item>
            <Form.Item name={'password'} initialValue={"password"} rules={[{required: true, message: '请输入账号密码!'}]}>
              <Input
                prefix={<LockOutlined style={{color: 'rgba(0,0,0,.25)'}}/>}
                type="password"
                placeholder="密码"
              />
            </Form.Item>
            <Form.Item>
              <div style={{marginBottom: '8px'}}>
                <Checkbox name={'remember'}>记住我</Checkbox>
                <a href="/#" className={"right"} onClick={(e) => {
                  e.preventDefault();
                  message.info('请联系管理员')
                }}>
                  忘记密码
                </a>
              </div>
              <Button loading={this.props.user.loading} block type="primary" htmlType="submit">
                登录
              </Button>
              <br/>
              <div style={{marginBottom: '8px'}}>
                或 <a href="/#" onClick={(e) => {
                e.preventDefault();
                this.props.history.push("/register")
              }}>注册!</a>
              </div>
            </Form.Item>
          </Form>
        </div>
      </div>
    );
  }
}

export default connect(LoginPage.stateToProps)(LoginPage);
