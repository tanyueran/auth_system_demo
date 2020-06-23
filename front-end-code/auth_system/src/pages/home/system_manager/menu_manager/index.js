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
  Form,
  Modal,
  Input,
} from "antd";

import {
  PlusOutlined,
  UndoOutlined,
  DeleteOutlined,
  EditOutlined,
} from "@ant-design/icons";

import MTableTitle from '../../../../components/MTableTitle.js'
import style from "./index.module.scss";

import {getPrimaryKey} from "../../../../api/common";

import {
  queryAllMenu,
  deleteMenu,
  addMenu,
  editMenu,
} from "../../../../api/menu_manager";

class MenuManagerPage extends React.Component {

  columns = [
    {
      title: '名称',
      dataIndex: 'menuName',
      key: 'menuName',
    },
    {
      title: '编码',
      dataIndex: 'menuCode',
      key: 'menuCode',
    },
    // type;// 资源类型 1、菜单资源2、按钮资源
    {
      title: '类型',
      dataIndex: 'menuType',
      key: 'menuType',
      render: (text, record, index) => {
        if (record.menuType == '0') {
          return <Tag color="blue">一级菜单</Tag>;
        } else if (record.menuType == '1') {
          return <Tag color="magenta">二级菜单</Tag>;
        }
      }
    },
    {
      title: '其他',
      dataIndex: 'data',
      key: 'data',
    },
    {
      title: '备注',
      dataIndex: 'remark',
      key: 'remark',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
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
            obj.menuType === '0' &&
            <>&nbsp;
              <Button onClick={() => {
                this.setState({
                  modalObj: {
                    show: true,
                    isEdit: false,
                    pid: obj.id,
                    menuType: '1',
                  }
                })
              }} size={"small"} icon={<PlusOutlined/>} title={"添加子菜单"}>添加</Button></>
          }
        </div>
      }
    },
  ];

  state = {
    loading: false,
    formRef: React.createRef(),
    idList: [],
    list: [],
    modalObj: {
      isEdit: false,
      show: false,
      menuType: '',// 0、一级菜单，1、二级菜单
      pid: null,
      obj: {}
    },
  };

  // 获取主键
  getKey = () => {
    getPrimaryKey(1).then(data => {
      this.setState({
        idList: data,
      })
    }).catch(err => {
      console.log(err);
    });
  };

  // 获取数据
  getData = () => {
    this.setState({
      loading: true,
    });
    queryAllMenu().then(data => {
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
    deleteMenu(id).then(data => {
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

  modalHandler = (data) => {
    this.setState({
      loading: true,
    });
    // 编辑
    if (this.state.modalObj.isEdit) {
      editMenu({
        ...this.state.modalObj.obj,
        ...data,
      }).then(data => {
        if (data) {
          message.success("操作成功");
          this.getData();
        } else {
          message.error("操作失败");
        }
      }).catch(err => {
        console.log(err);
      }).finally(() => {
        this.getKey();
        this.setState({
          loading: false,
        });
      })
    } else {// 新增
      addMenu({
        ...data,
        id: this.state.idList.shift(),
        pid: this.state.modalObj.pid,
        menuType: this.state.modalObj.menuType,
      }).then(data => {
        if (data) {
          message.success("操作成功");
          this.getData();
        } else {
          message.error("操作失败");
        }
      }).catch(err => {
        console.log(err);
      }).finally(() => {
        this.getKey();
        this.setState({
          loading: false,
        });
      })
    }
  };

  // 关闭模态框
  closeModal = () => {
    this.setState({
      modalObj: {
        show: false,
        obj: {},
        isEdit: false,
        menuType: '',
        pid: null,
      }
    }, () => {
      this.state.formRef.current.resetFields();
    });
  }

  componentDidMount() {
    this.getData();
    this.getKey();
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
              <Button onClick={() => {
                this.setState({
                  modalObj: {
                    show: true,
                    isEdit: false,
                    obj: {},
                    menuType: '0',
                  }
                })
              }} type={'primary'} icon={<PlusOutlined/>}>添加一级菜单</Button>
              &nbsp;
              <Button onClick={this.getData} title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}/>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table
            pagination={false}
            expandable
            rowKey={"id"}
            loading={this.state.loading}
            columns={this.columns}
            dataSource={this.state.list}/>
        </Col>
      </Row>


      {/*新增边的模态框*/}
      <Modal
        title={this.state.modalObj.isEdit ? "编辑菜单" : "新增菜单"}
        visible={this.state.modalObj.show}
        onOk={() => {
          // 验证表单
          this.state.formRef.current.validateFields().then(data => {
            this.modalHandler(data);
            this.closeModal();
          }).catch(err => {
            message.error('请按要求填写完整');
          });
          return false;
        }}
        onCancel={() => {
          this.closeModal();
        }}
      >
        <Form
          ref={this.state.formRef}
          initialValues={this.state.modalObj.obj}
          {...{
            labelCol: {span: 4},
            wrapperCol: {span: 18},
          }}
          name="btnForm"
          onFinish={this.modalHandler}
        >
          <Form.Item
            label="菜单名称"
            name="menuName"
            rules={[{required: true, message: '请输入菜单的中文名称!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="菜单标识"
            name="menuCode"
            rules={[{required: true, message: '请输入菜单的唯一标识!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="其他"
            name="data"
          >
            <Input.TextArea placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="备注"
            name="remark"
          >
            <Input.TextArea placeholder={"请输入"}/>
          </Form.Item>

        </Form>
      </Modal>
    </div>
  }
}

export default MenuManagerPage;
