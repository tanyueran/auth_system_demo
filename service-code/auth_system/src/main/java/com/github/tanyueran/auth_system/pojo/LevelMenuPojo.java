package com.github.tanyueran.auth_system.pojo;

import com.github.tanyueran.auth_system.entity.Menu;

import java.util.List;

public class LevelMenuPojo extends Menu {
    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
