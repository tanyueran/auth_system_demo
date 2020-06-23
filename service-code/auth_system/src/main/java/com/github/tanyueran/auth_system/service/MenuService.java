package com.github.tanyueran.auth_system.service;

import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.pojo.LevelMenuPojo;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenu();

    // 按层级获取菜单
    List<LevelMenuPojo> getLevelMenu();

    // 根据id删除菜单
    Boolean deleteMenuById(String id) throws Exception;

    // 添加菜单
    Boolean addMenu(Menu menu);

    // 编辑菜单
    Boolean editMenu(Menu menu) throws Exception;
}
