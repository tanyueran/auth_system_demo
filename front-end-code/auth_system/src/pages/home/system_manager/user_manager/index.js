/**
 * @author tanxin
 * @date $
 * @Description: 用户管理页面
 */
import React from 'react';
import {
  Table,
  Row,
  Col,
  Button,
} from "antd";

import {
  PlusOutlined,
  UndoOutlined,
} from "@ant-design/icons";

import MTableTitle from '../../../../components/MTableTitle.js'
import style from "../role_manager/index.module.scss";

class UserManagerPage extends React.Component {

  columns = [
    {
      title: '姓名',
      dataIndex: 'username',
      key: 'username',
    },
    {
      title: '账号',
      dataIndex: 'userCode',
      key: 'userCode',
    },
    {
      title: '性别',
      dataIndex: 'sex',
      key: 'sex',
    },
    {
      title: '描述',
      dataIndex: 'desc',
      key: 'desc',
    },
  ];

  state = {
    queryObj: {
      current: 1,

    },
    data: [],
  };

  // 查询用户
  componentDidMount() {

  }


  render() {
    return <div>
      <Row className={style['search-box']}>
        <Col span={12}>查询条件</Col>
        <Col span={12} className="text-right">

        </Col>
      </Row>
      <Row className={style['user_manager_content']}>
        <Col span={24}>
          <Row>
            <Col span={12}>
              <MTableTitle>用户列表</MTableTitle>
            </Col>
            <Col span={12} className="text-right">
              <Button type={'primary'} icon={<PlusOutlined/>}>添加用户</Button>
              &nbsp;
              <Button title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}></Button>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table columns={this.columns} datasource={this.state.data}></Table>
        </Col>
      </Row>
    </div>
  }
}

export default UserManagerPage;
