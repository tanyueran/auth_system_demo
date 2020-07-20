package com.github.tanyueran.auth_system_springboot.vo;

import java.io.Serializable;

/**
 * 自定义的json结果对象
 */
public class ResponseResult implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功
    public static ResponseResult success(Object data) {
        ResponseResult res = new ResponseResult();
        res.setCode(200);
        res.setMsg("ok");
        res.setData(data);
        return res;
    }

    // 失败
    public static ResponseResult error(Object data, String msg) {
        ResponseResult res = new ResponseResult();
        res.setData(data);
        res.setCode(100);
        res.setMsg(msg);
        return res;
    }

    public static ResponseResult error(String msg) {
        ResponseResult res = new ResponseResult();
        res.setData(null);
        res.setCode(100);
        res.setMsg(msg);
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
