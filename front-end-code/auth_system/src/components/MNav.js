/**
 * @author tanxin
 * @date $
 * @Description:
 */
import React from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router'
import {Menu} from "antd";

import {createFromIconfontCN} from '@ant-design/icons';

// 自定义的iconfont
const IconFont = createFromIconfontCN({
  scriptUrl: '/iconfont/iconfont.js',// 引用静态的地址
});

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
    let fontStyle = {
      fontSize: '18px',
    };
    return <Menu mode="inline" onClick={this.clickHandler} selectedKeys={this.props.location.pathname}>
      <Menu.Item key="/home/index">
        <IconFont style={fontStyle} type={'myiconshouye'}/>
        <span>首页</span>
      </Menu.Item>
      <Menu.SubMenu key="sub" icon={<IconFont style={fontStyle} type={'myicongongzuotaishouye'}/>} title="个人中心">
        <Menu.Item key="/home/personCenter/personInfo">
          <IconFont style={fontStyle} type={'myiconzichanxinxibuquancelve'}/>
          <span>个人信息</span>
        </Menu.Item>
      </Menu.SubMenu>
      {/*遍历获取到的数据*/}
      {
        this.props.user.menu.map(item => {
          return <Menu.SubMenu
            key={item.menuCode}
            icon={<IconFont style={fontStyle} type={(item.menuIcon || 'myiconmobanguanli')}/>}
            title={item.menuName}>
            {
              item.children.map(item2 => {
                return <Menu.Item key={item2.menuUrl}>
                  <IconFont style={fontStyle} type={(item2.menuIcon || 'myiconmobanguanli')}/>
                  <span>{item2.menuName}</span>
                </Menu.Item>
              })
            }
          </Menu.SubMenu>
        })
      }
    </Menu>
  }
}

export default connect(MNav.stateToProps)(withRouter(MNav));
