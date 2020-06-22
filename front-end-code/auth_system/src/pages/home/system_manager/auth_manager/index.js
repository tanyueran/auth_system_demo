/**
 * @author tanxin
 * @date $
 * @Description: 权限管理页面
 */
import React from 'react';
import {
  Table,
  Row,
  Col,
  Button,
  Tag,
  Popconfirm,
  message,
} from "antd";

import {
  PlusOutlined,
  UndoOutlined,
  DeleteOutlined,
  EditOutlined,
} from "@ant-design/icons";

import MTableTitle from '../../../../components/MTableTitle.js'
import style from "./index.module.scss";
import {
  queryAuthByPage,
  deleteAuth,
} from '../../../../api/auth_manager.js'

class UserManagerPage extends React.Component {

  columns = [
    {
      title: '名称',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '编码',
      dataIndex: 'code',
      key: 'code',
    },
    // type;// 资源类型 1、菜单资源2、按钮资源
    {
      title: '类型',
      dataIndex: 'type',
      key: 'type',
      render: (text, record, index) => {
        if (record.type == '0') {
          return <Tag color="blue">一级菜单</Tag>;
        } else if (record.type == '1') {
          return <Tag color="magenta">二级菜单</Tag>;
        } else if (record.type == '2') {
          return <Tag color="green">按钮</Tag>
        }
      }
    },
    {
      title: '备注',
      dataIndex: 'remark',
      key: 'remark',
    },
    {
      title: '其他',
      dataIndex: 'data',
      key: 'data',
    },
    {
      title: '操作',
      width: 240,
      align: 'center',
      render: (text, obj, index) => {
        return <div>
          <Button size={"small"} icon={<EditOutlined/>}>编辑</Button>
          &nbsp;
          <Popconfirm
            title="您确定删除此资源嘛?"
            onConfirm={() => {
              this.delAuth(obj.id);
            }}
            okText="是"
            cancelText="否"
          >
            <Button size={"small"} icon={<DeleteOutlined/>} danger>删除</Button>
          </Popconfirm>
          {
            obj.type !== '2' &&
            <>&nbsp;
              <Button size={"small"} icon={<PlusOutlined/>} title={"添加子权限"}>添加</Button></>
          }
        </div>
      }
    },
  ];

  state = {
    loading: false,
    list: [],
  };

  // 获取数据
  getData = () => {
    this.setState({
      loading: true,
    });
    queryAuthByPage().then(data => {
      this.setState({
        list: data,
      })
    }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      });
    });
  };

  // 删除资源
  delAuth = (id) => {
    this.setState({
      loading: true,
    });
    deleteAuth(id).then(data => {
      if (data === true) {
        message.success("删除成功！");
        this.getData();
      } else {
        message.error("删除失败！");
      }
    }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      })
    })
  };

  componentDidMount() {
    this.getData();
  }


  render() {
    return <div>
      <Row className={style['user_manager_content']}>
        <Col span={24}>
          <Row>
            <Col span={12}>
              <MTableTitle>权限列表</MTableTitle>
            </Col>
            <Col span={12} className="text-right">
              <Button type={'primary'} icon={<PlusOutlined/>}>添加一级菜单</Button>
              &nbsp;
              <Button onClick={this.getData} title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}></Button>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table
            pagination={false}
            expandable
            rowKey={"id"} loading={this.state.loading} columns={this.columns}
            dataSource={this.state.list}></Table>
        </Col>
      </Row>
    </div>
  }
}

export default UserManagerPage;
