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
  Popconfirm,
  Button,
  Modal,
  Radio,
  Input,
  Form,
  message,
  Checkbox,
  Switch,
} from "antd";

import {
  PlusOutlined,
  UndoOutlined,
  EditOutlined,
  DeleteOutlined,
  AppstoreAddOutlined,
} from "@ant-design/icons";

import MTableTitle from '../../../../components/MTableTitle.js'
import style from "./index.module.scss";
import {queryUserByPage} from "../../../../api/user_manager.js";
import {addUser, editUser, deleteUserById, updateRoleByUserId, getRoleByUserId} from "../../../../api/user_manager";
import {getPrimaryKey} from "../../../../api/common";
import {
  getAllRoles,
} from "../../../../api/role_manager";

class UserManagerPage extends React.Component {

  columns = [
    {
      title: '用户姓名',
      dataIndex: 'userName',
      key: 'userName',
    },
    {
      title: '账号',
      dataIndex: 'userCode',
      key: 'userCode',
    },
    {
      title: '激活',
      dataIndex: 'active',
      key: 'active',
      render: (txt) => txt === '1' ? '是' : '否',
    },
    {
      title: '性别',
      dataIndex: 'sex',
      key: 'sex',
      render: (txt) => txt === 1 ? '男' : '女',
    },
    {
      title: '描述',
      dataIndex: 'desc',
      key: 'desc',
    },
    {
      title: '注册时间',
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
            this.getThisUserRole(obj.id);
          }} size={"small"} icon={<AppstoreAddOutlined/>} title={"角色配置"}>配置角色</Button>
          &nbsp;
          <Button onClick={() => {
            this.setState({
              modalObj: {
                show: true,
                obj: {
                  ...obj,
                  active: obj.active ? '1' : '0'
                },
                isEdit: true,
              }
            }, () => {
              if (this.state.formRef.current !== null) {
                this.state.formRef.current.resetFields();
              }
            })
          }} title={"编辑用户信息"} size={"small"} icon={<EditOutlined/>}/>
          &nbsp;
          <Popconfirm
            title="您确定删除此资源嘛?"
            onConfirm={() => {
              this.delUser(obj.id);
            }}
            okText="是"
            cancelText="否"
          >
            <Button title={"删除该用户"} size={"small"} icon={<DeleteOutlined/>} danger/>
          </Popconfirm>
        </div>
      }
    },
  ];

  state = {
    loading: false,
    // 查询对象
    queryObj: {
      current: 1,
      size: 10,
      keyword: '',
    },
    // 结果
    resultObj: {
      total: 0,
      list: [],
    },
    idList: [],
    formRef: React.createRef(),
    modalObj: {
      isEdit: false,
      show: false,
      obj: {}
    },
    // 所有的角色信息
    _roleList: [],
    roleList: [],
    // 菜单的模块框
    roleModalObj: {
      show: false,
      roleId: '',
      list: [],
    }
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

  // 删除用户
  delUser = (id) => {
    this.setState({
      loading: true,
    });
    deleteUserById(id).then(data => {
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
  };


  // 查询用户
  getData = () => {
    this.setState({
      loading: true,
    });
    queryUserByPage({
      current: this.state.queryObj.current,
      size: this.state.queryObj.size,
      keyword: this.state.queryObj.keyword,
    }).then(data => {
      this.setState(state => {
        return Object.assign({}, state, {
          resultObj: {
            ...state.resultObj,
            total: data.total,
            list: data.data,
          }
        })
      })
    }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      });
    })
  };

  // 关闭模态框
  closeModal = () => {
    this.setState({
      modalObj: {
        show: false,
        obj: {},
        isEdit: false,
      }
    }, () => {
      this.state.formRef.current.resetFields();
    });
  };

  // 关闭角色模态框
  closeRoleModal = () => {
    this.setState(state => {
      return Object.assign({}, state, {
        roleModalObj: {
          userId: '',
          list: [],
          show: false,
        }
      })
    })
  };

  // 获取这个用户的角色
  getThisUserRole = (id) => {
    this.setState(state => {
      return Object.assign({}, state, {
        roleModalObj: {
          ...state.roleModalObj,
          userId: id,
          show: true,
        }
      });
    });
    getRoleByUserId(id).then(data => {
      this.setState(state => {
        let list = [];
        data.forEach(item => {
          list.push(item.id);
        });
        return Object.assign({}, state, {
          roleModalObj: {
            ...state.roleModalObj,
            list,
          }
        });
      })
    }).catch(err => {
      console.log(err);
    }).finally(() => {

    })
  };

  // 新增用户
  modalHandler = (data) => {
    this.setState({
      loading: true,
    });
    // 编辑
    if (this.state.modalObj.isEdit) {
      editUser({
        ...this.state.modalObj.obj,
        ...data,
        active: data.active ? '1' : '0'
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
      addUser({
        ...data,
        active: data.active ? '1' : '0',
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

  // 查询所有的角色
  getAllRoles = () => {
    getAllRoles().then(data => {
      let list = [];
      data.forEach(item => {
        list.push({
          id: item.id,
          label: `[${item.roleCode}] - ${item.roleName}`,
          value: item.id,
        })
      });
      this.setState({
        _roleList: data,
        roleList: list,
      })
    }).catch(err => {
      console.log(err);
    });
  };

  // 更新菜单
  updateMenuHandler = (userId, roleIdList) => {
    this.setState({
      loading: true,
    });
    updateRoleByUserId(userId, roleIdList || "null")
      .then(data => {
        if (data) {
          message.success('操作成功');
        } else {
          message.error('操作失败');
        }
      }).catch(err => {
      console.log(err);
    }).finally(() => {
      this.setState({
        loading: false,
      });
    });
  };

  // 查询用户
  componentDidMount() {
    this.getKey();
    this.getData();
    this.getAllRoles();
  }

  componentWillUnmount() {
    this.setState = () => false;
  }


  render() {
    return <div>
      <Row className={style['search-box']}>
        <Col span={24} style={{marginBottom: '10px'}}>
          <MTableTitle>查询条件</MTableTitle>
        </Col>
        <Col span={12} offset={6}>
          <Input onChange={(e) => {
            e.preventDefault();
            if (e.target != null) {
              let v = e.target.value;
              this.setState(state => {
                return Object.assign({}, state, {
                  queryObj: {
                    ...state.queryObj,
                    keyword: v,
                  }
                })
              })
            }
          }} value={this.state.queryObj.keyword} placeholder={"请输入关键字"}/>
        </Col>
        <Col span={6}>
          <Button onClick={() => {
            this.setState(state => {
              return Object.assign({}, state, {
                queryObj: {
                  ...state.queryObj,
                  current: 1,
                }
              })
            }, () => {
              this.getData();
            })
          }} type={"primary"}>搜索</Button>
        </Col>
      </Row>
      <Row className={style['user_manager_content']}>
        <Col span={24}>
          <Row>
            <Col span={12}>
              <MTableTitle>用户列表</MTableTitle>
            </Col>
            <Col span={12} className="text-right">
              <Button onClick={() => {
                this.setState(state => {
                  return Object.assign({}, state, {
                    modalObj: {
                      ...state.modalObj,
                      show: true,
                    }
                  })
                })
              }} type={'primary'} icon={<PlusOutlined/>}>添加用户</Button>
              &nbsp;
              <Button onClick={() => {
                this.getData()
              }} title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}/>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table
            loading={this.state.loading}
            rowKey={"id"}
            pagination={{
              defaultCurrent: 1,
              showSizeChanger: true,
              showQuickJumper: true,
              total: this.state.resultObj.total,
              current: this.state.queryObj.current,
              pageSize: this.state.queryObj.size,
            }}
            onChange={(page, pageSize) => {
              this.setState(state => {
                return Object.assign({}, state, {
                  queryObj: {
                    ...state.queryObj,
                    size: page.pageSize,
                    current: page.current,
                  }
                })
              }, () => {
                this.getData();
              });
            }}
            columns={this.columns}
            dataSource={this.state.resultObj.list}/>
        </Col>
      </Row>

      {/*新增的模态框*/}
      <Modal
        title={this.state.modalObj.isEdit ? "编辑用户" : "新增用户"}
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
            label="用户名称"
            name="userName"
            rules={[{required: true, message: '请输入用户姓名!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="账号"
            name="userCode"
            rules={[{required: true, message: '请输入账号!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item name="active" label="是否使用">
            <Switch checkedChildren="开启" unCheckedChildren="关闭" defaultChecked/>
          </Form.Item>

          <Form.Item name="sex" label="性别">
            <Radio.Group>
              <Radio value={1}>男</Radio>
              <Radio value={2}>女</Radio>
            </Radio.Group>
          </Form.Item>

          <Form.Item
            label="描述"
            name="desc"
          >
            <Input.TextArea placeholder={"请输入"}/>
          </Form.Item>

        </Form>
      </Modal>

      {/*角色的模块*/}
      <Modal
        title={"角色权限配置"}
        visible={this.state.roleModalObj.show}
        onOk={() => {
          this.updateMenuHandler(this.state.roleModalObj.userId, this.state.roleModalObj.list.join(","));
          this.closeRoleModal();
          return false;
        }}
        onCancel={() => {
          this.closeRoleModal();
        }}
      >
        <Checkbox.Group onChange={(val) => {
          this.setState(state => {
            return Object.assign({}, state, {
              roleModalObj: {
                ...state.roleModalObj,
                list: val,
              }
            })
          });
        }} value={this.state.roleModalObj.list} options={this.state.roleList}/>
      </Modal>


    </div>
  }
}

export default UserManagerPage;
