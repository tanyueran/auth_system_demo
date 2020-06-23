package com.github.tanyueran.auth_system.mapper;

import com.github.tanyueran.auth_system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenu();

    Menu queryMenuById(String id);

    List<Menu> queryMenuChildrenById(String id);

    Integer deleteMenuById(String id);

    Integer addMenu(Menu menu);

    // 检测菜单是否有角色引用的
    Integer checkMenuForRoleById(String id);

    // 删除该菜单下的 菜单-按钮-中间表数据
    Integer deleteMenuButtonByMenuId(String menuId);

    // 为菜单添加基本的按钮权限
    Integer addBasicButtonForMenuByMenuId(List<Map<String, String>> list);
}
