/**
 * @author tanxin
 * @date $
 * @Description: 权限不够页面
 */
import React from 'react';
import {withRouter} from 'react-router'
import img from '../../images/401.jpg'

class Page401 extends React.Component {
  render() {
    return <div style={{height: "500px"}}>
      <h4 className="text-center">
        <p className={"text-left"}>
          <a href={"/"} onClick={(e) => {
            this.props.history.replace("/");
            e.preventDefault()
          }
          }>返回首页</a>
        </p>
        <img style={{width: '90%', height: 'auto'}} src={img} alt="401"/>
      </h4>
    </div>
  }
}

export default withRouter(Page401);
