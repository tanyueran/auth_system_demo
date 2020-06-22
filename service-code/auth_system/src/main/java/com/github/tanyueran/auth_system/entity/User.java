package com.github.tanyueran.auth_system.entity;

import java.io.Serializable;

// 用户实体类
public class User implements Serializable {
    private Integer id;// 主键
    private String username;// 用户姓名
    private String file_id;// 头像id
    private String password;// 密码
    private String userCode;// 账号
    private Integer sex;// 性别
    private String desc;// 描述

    public User() {
    }

    public User(Integer id, String username, String file_id, String password, String userCode, Integer sex, String desc) {
        this.id = id;
        this.username = username;
        this.file_id = file_id;
        this.password = password;
        this.userCode = userCode;
        this.sex = sex;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", file_id='" + file_id + '\'' +
                ", password='" + password + '\'' +
                ", userCode='" + userCode + '\'' +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
