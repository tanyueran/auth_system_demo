/**
 * @author tanxin
 * @date $
 * @Description: 按钮权限管理页面
 */
import React from 'react';
import {
  Table,
  Row,
  Col,
  Button,
  Popconfirm,
  message,
  Modal,
  Form,
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
  queryAllBtn,
  deleteBtn,
  addBtn,
  editBtn,
} from "../../../../api/button_manager";

class ButtonManagerPage extends React.Component {

  columns = [
    {
      title: '名称',
      dataIndex: 'buttonName',
      key: 'buttonName',
    },
    {
      title: '编码',
      dataIndex: 'buttonCode',
      key: 'buttonCode',
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
                isEdit: true,
                show: true,
                obj: {
                  ...obj,
                }
              }
            }, () => {
              if (this.state.formRef.current !== null) {
                this.state.formRef.current.resetFields();
              }
            })
          }} size={"small"} icon={<EditOutlined/>}>编辑</Button>
          &nbsp;
          <Popconfirm
            title="您确定删除该按钮吗?"
            onConfirm={() => {
              this.delAuth(obj.id);
            }}
            okText="是"
            cancelText="否"
          >
            <Button size={"small"} icon={<DeleteOutlined/>} danger>删除</Button>
          </Popconfirm>
        </div>
      }
    },
  ];

  state = {
    loading: false,
    list: [],
    formRef: React.createRef(),
    // 保存主键
    id: [],
    // 新增编辑对话框
    modalObj: {
      show: false,
      // 是否编辑
      isEdit: false,
      obj: {},
    }
  };

  // 获取数据
  getData = () => {
    this.setState({
      loading: true,
    });
    queryAllBtn().then(data => {
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
    deleteBtn(id).then(data => {
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


  // 模态框
  modalHandler = (val) => {
    this.setState({
      loading: true,
    }, () => {
      let o = {};
      if (this.state.modalObj.isEdit) {// 编辑
        o = {
          ...this.state.modalObj.obj,
          ...val,
        };
        editBtn(o).then(data => {
          if (data) {
            message.success("操作成功");
            this.getData();
          } else {
            message.error("操作失败");
          }
        }).catch(() => {
          this.setState({
            loading: false,
          });
        });
      } else {
        o = {
          ...val,
          id: this.state.idList.shift(),
        };
        addBtn(o).then(data => {
          if (data) {
            message.success("操作成功");
            this.getData();
          } else {
            message.error("操作失败");
          }
        }).catch(() => {
          this.setState({
            loading: false,
          });
        }).finally(() => {
          this.getKey();
        });
      }
    });
  };

  // 请求主键
  getKey = () => {
    getPrimaryKey().then(data => {
      this.setState({
        idList: data,
      })
    }).catch(err => {
      console.log(err);
    });
  };

  // 关闭模态框
  closeModal = () => {
    this.setState({
      modalObj: {
        show: false,
        isEdit: false,
        obj: {},
      }
    }, () => {
      this.state.formRef.current.resetFields();
    })
  };


  componentDidMount() {
    this.getData();
    this.getKey();
  }


  render() {
    return <div>
      <Row className={style['button_manager_content']}>
        <Col span={24}>
          <Row>
            <Col span={12}>
              <MTableTitle>按钮列表</MTableTitle>
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
              }} type={'primary'} icon={<PlusOutlined/>}>添加按钮</Button>
              &nbsp;
              <Button onClick={this.getData} title={"点击刷新列表"} type="link" icon={<UndoOutlined/>}/>
            </Col>
          </Row>
        </Col>
        <Col span={24}>
          <Table
            pagination={false}
            expandable
            rowKey={"id"} loading={this.state.loading} columns={this.columns}
            dataSource={this.state.list}/>
        </Col>
      </Row>

      <Modal
        title={this.state.modalObj.isEdit ? "编辑按钮" : "新增按钮"}
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
            label="按钮名称"
            name="buttonName"
            rules={[{required: true, message: '请输入按钮的中文名称!'}]}
          >
            <Input placeholder={"请输入"}/>
          </Form.Item>

          <Form.Item
            label="按钮标识"
            name="buttonCode"
            rules={[{required: true, message: '请输入按钮的唯一标识!'}]}
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

export default ButtonManagerPage;
