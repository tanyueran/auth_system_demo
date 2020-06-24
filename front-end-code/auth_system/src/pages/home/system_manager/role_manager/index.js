/**
 * @author tanxin
 * @date $
 * @Description: 角色管理页面
 */
import React from 'react';
import {
  Table,
  Row,
  Col,
  Button,
  Popconfirm,
  message,
  Form,
  Input,
  Modal,
} from "antd";

import {
  PlusOutlined,
  UndoOutlined,
  EditOutlined,
  DeleteOutlined,
  AppstoreAddOutlined,
} from "@ant-design/icons";

import style from './index.module.scss'
import MTableTitle from '../../../../components/MTableTitle.js'
import {getAllRoles, addRole, editRole, delRoleById,} from "../../../../api/role_manager";
import {getPrimaryKey} from "../../../../api/common";

class RoleManagerPage extends React.Component {

  columns = [
    {
      title: '角色名称',
      dataIndex: 'roleName',
      key: 'roleName',
    },
    {
      title: '标识',
      dataIndex: 'roleCode',
      key: 'roleCode',
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
          <Button onClick={() => {
            this.setState({
              modalObj: {
                show: true,
                obj: {...obj},
                isEdit: true,
                pid: obj.pid,
                menuType: obj.menuType,
              }
            }, () => {
              if (this.state.formRef.current !== null) {
                this.state.formRef.current.resetFields();
              }
            })
          }} size={"small"} icon={<EditOutlined/>}>编辑</Button>
          &nbsp;
          <Popconfirm
            title="您确定删除此资源嘛?"
            onConfirm={() => {
              this.delRole(obj.id);
            }}
            okText="是"
            cancelText="否"
          >
            <Button size={"small"} icon={<DeleteOutlined/>} danger>删除</Button>
          </Popconfirm>
          {
            obj.menuType === '0' ?
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
                }} size={"small"} type={'primary'} icon={<PlusOutlined/>} title={"添加子菜单"}>添加</Button></> :
              <>
                &nbsp;
                <Button onClick={() => {
                  this.getThisMenuButton(obj.id);
                }} size={"small"} icon={<AppstoreAddOutlined/>} title={"按钮权限配置"}>配置</Button>
              </>
          }
        </div>
      }
    },
  ];

  state = {
    loading: false,
    list: [],
    idList: [],
    formRef: React.createRef(),
    modalObj: {
      isEdit: false,
      show: false,
      obj: {}
    },
  };

  // 获取所有的角色
  getData = () => {
    this.setState({
      loading: true,
    });
    getAllRoles().then(data => {
      this.setState({
        list: data,
      })
    }).catch(err => {

    }).finally(() => {
      this.setState({
        loading: false,
      })
    })
  };

  // 删除
  delRole = (id) => {
    this.setState({
      loading: true,
    });
    delRoleById(id).then(data => {
      if (data) {
        message.success("操作成功");
        this.getData();
      } else {
        message.error("操作失败");
      }
    }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      });
    })
  }

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

  modalHandler = (data) => {
    this.setState({
      loading: true,
    });
    // 编辑
    if (this.state.modalObj.isEdit) {
      editRole({
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
      addRole({
        ...data,
        id: this.state.idList.shift(),
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
  };

  // 查询用户
  componentDidMount() {
    this.getData();
    this.getKey();
  }


  render() {
    return <div>
      <Row className={style['role_manager_content']}>
        <Col span={24}>
          <Row>
            <Col span={12}>
              <MTableTitle>角色列表</MTableTitle>
            </Col>
            <Col span={12} className="text-right">
              <Button onClick={() => {
                this.setState({
                  modalObj: {
                    show: true,
                    isEdit: false,
                    obj: {},
                  }
                })
              }} type={'primary'} icon={<PlusOutlined/>}>添加角色</Button>
              &nbsp;
              <Button onClick={() => {
                this.getData()
              }} title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}/>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table key={"id"} loading={this.state.loading} columns={this.columns} dataSource={this.state.list}/>
        </Col>
      </Row>

      {/*新增边的模态框*/}
      <Modal
        title={this.state.modalObj.isEdit ? "编辑角色" : "新增角色"}
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
            label="角色名称"
            name="roleName"
            rules={[{required: true, message: '请输入角色的中文名称!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="角色标识"
            name="roleCode"
            rules={[{required: true, message: '请输入角色的唯一标识!'}]}
          >
            <Input placeholder={"请输入"}/>
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

export default RoleManagerPage;
