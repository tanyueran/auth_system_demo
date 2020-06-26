/**
 * @author tanxin
 * @date $
 * @Description:
 */
import React from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router'
import {Menu} from "antd";
import {
  TeamOutlined,
  HomeOutlined,
  AppstoreOutlined,
  MenuOutlined,
  UnlockOutlined,
  ClusterOutlined
} from '@ant-design/icons'


class MNav extends React.Component {
  clickHandler = (val) => {
    this.props.history.push(val.key);
  };

  static stateToProps(state) {
    return {
      user: state.userReducer
    }
  }

  render() {
    let menus = [];
    this.props.user.userInfo.data.roles && this.props.user.userInfo.data.roles.forEach(item => {
      item.menus.forEach(item2 => {
        menus[item2.menuCode] = item2;
      })
    });

    return <Menu mode="inline" onClick={this.clickHandler} selectedKeys={this.props.location.pathname}>
      <Menu.Item key="/home/index">
        <HomeOutlined/>
        <span>首页</span>
      </Menu.Item>
      <Menu.SubMenu key="sub" icon={<AppstoreOutlined/>} title="个人中心">
        <Menu.Item key="/home/personCenter/personInfo">
          <TeamOutlined/>
          <span>个人信息</span>
        </Menu.Item>
      </Menu.SubMenu>
      <Menu.SubMenu key="sub2" icon={<AppstoreOutlined/>} title="系统管理">
        {
          menus['userManager'] !== undefined
          && <Menu.Item key="/home/system_manager/user_manager">
            <TeamOutlined/>
            <span>用户管理</span>
          </Menu.Item>
        }
        {
          menus['roleManager'] !== undefined
          && <Menu.Item key="/home/system_manager/role_manager">
            <ClusterOutlined/>
            <span>角色管理</span>
          </Menu.Item>
        }
        {
          menus['menuManager'] !== undefined
          && <Menu.Item key="/home/system_manager/menu_manager">
            <MenuOutlined/>
            <span>菜单管理</span>
          </Menu.Item>
        }
        {
          menus['buttonManager'] !== undefined
          && <Menu.Item key="/home/system_manager/btn_manager">
            <UnlockOutlined/>
            <span>按钮管理</span>
          </Menu.Item>
        }
      </Menu.SubMenu>
    </Menu>
  }

}

export default connect(MNav.stateToProps)(withRouter(MNav));
