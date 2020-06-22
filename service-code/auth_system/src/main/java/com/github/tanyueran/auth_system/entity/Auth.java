package com.github.tanyueran.auth_system.entity;

import java.io.Serializable;

// 资源实体类
public class Auth implements Serializable {
    private Integer id;// 主键
    private Integer pid;// 父级主键
    private String name;// 中文标识名称
    private String code;// 资源code
    private String type;// 资源类型 1、菜单资源2、按钮资源
    private String remark;// 备注
    private String data;// 其他数据

    public Auth() {
    }

    public Auth(Integer id, Integer pid, String name, String code, String type, String remark, String data) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.code = code;
        this.type = type;
        this.remark = remark;
        this.data = data;
    }

    @Override
    public String toString() {
        return "AuthMapper{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
