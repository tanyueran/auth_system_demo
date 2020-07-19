package com.github.tanyueran.auth_system_springboot.modal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "menu_table")
public class Menu {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 父级主键
     */
    private String pid;

    /**
     * 菜单中文名
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单标识
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单类型（0、一级菜单，1、二级菜单）
     */
    @Column(name = "menu_type")
    private String menuType;

    /**
     * 菜单的icon
     */
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 菜单的地址
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 其他的数据标识
     */
    private String data;

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
     * 获取父级主键
     *
     * @return pid - 父级主键
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父级主键
     *
     * @param pid 父级主键
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取菜单中文名
     *
     * @return menu_name - 菜单中文名
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单中文名
     *
     * @param menuName 菜单中文名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单标识
     *
     * @return menu_code - 菜单标识
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单标识
     *
     * @param menuCode 菜单标识
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单类型（0、一级菜单，1、二级菜单）
     *
     * @return menu_type - 菜单类型（0、一级菜单，1、二级菜单）
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型（0、一级菜单，1、二级菜单）
     *
     * @param menuType 菜单类型（0、一级菜单，1、二级菜单）
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * 获取菜单的icon
     *
     * @return menu_icon - 菜单的icon
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单的icon
     *
     * @param menuIcon 菜单的icon
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取菜单的地址
     *
     * @return menu_url - 菜单的地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单的地址
     *
     * @param menuUrl 菜单的地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
     * 获取其他的数据标识
     *
     * @return data - 其他的数据标识
     */
    public String getData() {
        return data;
    }

    /**
     * 设置其他的数据标识
     *
     * @param data 其他的数据标识
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
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