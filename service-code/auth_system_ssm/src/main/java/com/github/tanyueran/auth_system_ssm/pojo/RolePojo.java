package com.github.tanyueran.auth_system_ssm.pojo;


import com.github.tanyueran.auth_system_ssm.entity.Role;

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
