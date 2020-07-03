package com.github.tanyueran.auth_system_ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

// 用户实体类
public class User implements Serializable {
    private String id;// 主键
    private String userName;// 用户姓名
    private String fileId;// 头像id
    private String password;// 密码
    private String userCode;// 账号
    private Integer sex;// 性别
    private String desc;// 描述
    private String active;// 是否开启，0，关闭，1，开启
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;// 更新时间

    public User() {
    }

    public User(String id, String userName, String fileId, String password, String userCode, Integer sex, String desc, String active, Date createTime, Date updateTime) {
        this.id = id;
        this.userName = userName;
        this.fileId = fileId;
        this.password = password;
        this.userCode = userCode;
        this.sex = sex;
        this.desc = desc;
        this.active = active;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", fileId='" + fileId + '\'' +
                ", password='" + password + '\'' +
                ", userCode='" + userCode + '\'' +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
                ", active='" + active + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
