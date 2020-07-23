package com.github.tanyueran.auth_system_springboot.vo;

import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.modal.User;

import java.io.Serializable;
import java.util.List;

public class UserDetail implements Serializable {

    private User user;

    private List<Menu> menus;

    private List<Role> roles;

    public UserDetail() {
    }

    public UserDetail(User user, List<Menu> menus, List<Role> roles) {
        this.user = user;
        this.menus = menus;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "user=" + user +
                ", menus=" + menus +
                ", roles=" + roles +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
