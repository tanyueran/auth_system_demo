package com.github.tanyueran.auth_system.pojo;


import com.github.tanyueran.auth_system.entity.Role;

import java.util.List;

public class RolePojo extends Role {
    private List<MenuPojo> menus;

    public List<MenuPojo> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuPojo> menus) {
        this.menus = menus;
    }
}
