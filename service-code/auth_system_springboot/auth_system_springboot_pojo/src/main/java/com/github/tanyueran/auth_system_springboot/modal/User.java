package com.github.tanyueran.auth_system_springboot.modal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_table")
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 账号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 是否可以使用（1：可以，0，不可以）
     */
    private String active;

    /**
     * 附件id
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 性别（1男2女）
     */
    private Integer sex;

    /**
     * 描述
     */
    @Column(name = "`desc`")
    private String desc;

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

    public User() {
    }

    public User(String id, String userName, String userCode, String active, String fileId, String password, Integer sex, String desc, Date createTime, Date updateTime) {
        this.id = id;
        this.userName = userName;
        this.userCode = userCode;
        this.active = active;
        this.fileId = fileId;
        this.password = password;
        this.sex = sex;
        this.desc = desc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", active='" + active + '\'' +
                ", fileId='" + fileId + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", desc='" + desc + '\'' +
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
     * 获取昵称
     *
     * @return user_name - 昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置昵称
     *
     * @param userName 昵称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取账号
     *
     * @return user_code - 账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置账号
     *
     * @param userCode 账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 获取是否可以使用（1：可以，0，不可以）
     *
     * @return active - 是否可以使用（1：可以，0，不可以）
     */
    public String getActive() {
        return active;
    }

    /**
     * 设置是否可以使用（1：可以，0，不可以）
     *
     * @param active 是否可以使用（1：可以，0，不可以）
     */
    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    /**
     * 获取附件id
     *
     * @return file_id - 附件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置附件id
     *
     * @param fileId 附件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取性别（1男2女）
     *
     * @return sex - 性别（1男2女）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别（1男2女）
     *
     * @param sex 性别（1男2女）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取描述
     *
     * @return desc - 描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
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