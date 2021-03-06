package com.github.tanyueran.auth_system_ssm.mapper;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.entity.Menu;
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

    // 根据菜单code查询
    Menu queryMenuByCode(String menuCode);

    // 为菜单添加基本的按钮权限
    Integer addBasicButtonForMenuByMenuId(List<Map<String, String>> list);

    // 根据菜单主键更新
    Integer updateMenuById(Menu menu);

    // 查询菜单下面挂载的按钮
    List<Button> queryButtonByMenuId(String id);

    // 给菜单添加按钮
    Integer addButtonByMenuId(List<Map<String, String>> list);

    // 查询角色下面的所有菜单那
    List<Menu> getAllMenuByRoleIds(@Param("roleIds") List<String> roleIds);
}
