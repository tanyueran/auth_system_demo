package com.github.tanyueran.auth_system.service;

import com.github.tanyueran.auth_system.entity.Button;
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
    Boolean addMenu(Menu menu) throws Exception;

    // 编辑菜单
    Boolean editMenu(Menu menu) throws Exception;

    // 获取菜单下面挂载的菜单
    List<Button> getButtonForMenuId(String menuId);

    // 更新菜单下面挂载的菜单
    Boolean updateButtonForMenuId(String menuId, List<String> buttonIdList);

    // 获取角色下面的所有菜单列表
    List<LevelMenuPojo> getMenuByRoleIds(List<String> roleIds) throws Exception;
}
