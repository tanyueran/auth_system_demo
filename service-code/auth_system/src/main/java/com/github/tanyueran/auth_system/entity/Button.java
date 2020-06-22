package com.github.tanyueran.auth_system.entity;

import java.io.Serializable;
import java.util.Date;

// 按钮的实体类
public class Button implements Serializable {
    private String id;
    private String buttonName;
    private String buttonCode;
    private String remark;
    private Date crateTime;
    private Date updateTime;

    public Button() {
    }

    public Button(String id, String buttonName, String buttonCode, String remark, Date crateTime, Date updateTime) {
        this.id = id;
        this.buttonName = buttonName;
        this.buttonCode = buttonCode;
        this.remark = remark;
        this.crateTime = crateTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Button{" +
                "id='" + id + '\'' +
                ", buttonName='" + buttonName + '\'' +
                ", buttonCode='" + buttonCode + '\'' +
                ", remark='" + remark + '\'' +
                ", crateTime=" + crateTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
