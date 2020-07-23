package com.github.tanyueran.auth_system_springboot.modal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "button_table")
public class Button implements Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 按钮中文名称
     */
    @Column(name = "button_name")
    private String buttonName;

    /**
     * 按钮标识
     */
    @Column(name = "button_code")
    private String buttonCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Button() {
    }

    public Button(String id, String buttonName, String buttonCode, String remark, Date createTime, Date updateTime) {
        this.id = id;
        this.buttonName = buttonName;
        this.buttonCode = buttonCode;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Button{" +
                "id='" + id + '\'' +
                ", buttonName='" + buttonName + '\'' +
                ", buttonCode='" + buttonCode + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取按钮中文名称
     *
     * @return button_name - 按钮中文名称
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 设置按钮中文名称
     *
     * @param buttonName 按钮中文名称
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    /**
     * 获取按钮标识
     *
     * @return button_code - 按钮标识
     */
    public String getButtonCode() {
        return buttonCode;
    }

    /**
     * 设置按钮标识
     *
     * @param buttonCode 按钮标识
     */
    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode == null ? null : buttonCode.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}