/**
 * @author tanxin
 * @date $
 * @Description:
 */
import React from 'react'
import {Spin,} from "antd";

let style = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
};

export default () => <Spin delay={300} style={style} size={"large"}/>

