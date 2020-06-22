/*
* 自定义的表格title
* */
import React from 'react';

export default function (props) {
  return <b style={{
    paddingLeft: '1em',
    borderLeft: '3px solid #1890FF',
  }}>
    {props.children}
  </b>
}
