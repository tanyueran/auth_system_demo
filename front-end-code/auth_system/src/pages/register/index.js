/**
 * @author tanxin
 * @date $
 * @Description: 登录页
 */
import React from 'react';
import './index.scss'

import {register} from "../../api/user";

import logo from '../../images/logo.jpg'
import {
  message,
  Form,
  Input,
  Button,
  Checkbox,
  Tooltip,
  Typography,
  Modal,
} from 'antd'

import {
  QuestionCircleOutlined,
} from '@ant-design/icons'

import MSiteAgreement from '../../components/MSiteAgreement.js'


/*
* 注册页面
* */
class RegisterPage extends React.Component {
  state = {
    loading: false,
  };

  loginHandler = values => {
    this.setState({
      loading: true,
    });
    register({
      username: values.username,
      userCode: values.userCode,
      password: values.password,
    }).then(data => {
      if (data === true) {
        message.success("注册成功");
      } else {
        message.error("注册失败");
      }
    }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      })
    })
  };

  render() {
    return (
      <div className='register-wrapper'>
        <Typography.Title>
          欢迎注册
          <img src={logo} alt="logo"/>
        </Typography.Title>
        <Form {...{
          labelCol: {span: 6},
          wrapperCol: {span: 14},
        }} onFinish={this.loginHandler}>
          <Form.Item label={"账号"} hasFeedback name={'userCode'} rules={[{required: true, message: '请输入登录账号!'}]}>
            <Input
              autoComplete={"off"}
              placeholder="请输入登录账号"
            />
          </Form.Item>
          <Form.Item label={"密码"} hasFeedback name={'password'} rules={[{required: true, message: '请输入账号密码!'}]}>
            <Input.Password
              placeholder="请输入账号密码"
            />
          </Form.Item>
          <Form.Item
            label={"确认密码"}
            dependencies={['password']}
            hasFeedback
            name={'password2'}
            rules={[
              {
                required: true,
                message: '请再次输入密码!',
              },
              ({getFieldValue}) => ({
                validator(rule, value) {
                  if (!value || getFieldValue('password') === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject('两次输入的密码不一致!');
                },
              }),
            ]}>
            <Input.Password
              placeholder="请确认密码"
            />
          </Form.Item>
          <Form.Item hasFeedback name={'userName'} rules={[{required: true, message: '请输入昵称!'}]} label={
            <span>
              用户姓名
              <Tooltip title="请输入您的姓名或者昵称?">
                <QuestionCircleOutlined/>
              </Tooltip>
            </span>
          }>
            <Input
              autoComplete={"off"}
              placeholder="请输入昵称"
            />
          </Form.Item>
          <Form.Item name={'protocol'} valuePropName="checked" rules={[
            {validator: (_, value) => value ? Promise.resolve() : Promise.reject('请阅读')},
          ]} wrapperCol={{span: 12, offset: 6}}>
            <Checkbox>
              阅读并接受<a onClick={
              (e) => {
                e.preventDefault();
                Modal.info({
                  title: '网站用户协议',
                  width: '500px',
                  content: <MSiteAgreement/>,
                  okText: '确定',
                })
              }
            }>《网站用户协议》</a>
            </Checkbox>
          </Form.Item>
          <Form.Item wrapperCol={{span: 12, offset: 6}}>
            <Button loading={this.state.loading} type="primary" htmlType="submit">
              注册
            </Button>
            <br/>
            已有帐号<a href="#" onClick={(e) => {
            e.preventDefault();
            this.props.history.push("login")
          }}>登录!</a>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

export default (RegisterPage)
