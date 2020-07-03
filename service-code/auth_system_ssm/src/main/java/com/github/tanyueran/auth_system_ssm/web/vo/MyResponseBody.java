package com.github.tanyueran.auth_system_ssm.web.vo;

public class MyResponseBody {
    private Integer code;
    private String msg;
    private Object data;

    public MyResponseBody() {
    }

    public static MyResponseBody success(Object data, String msg) {
        return new MyResponseBody(200, msg, data);
    }

    public static MyResponseBody error(Object data, String msg) {
        return new MyResponseBody(1, msg, data);
    }

    public MyResponseBody(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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
