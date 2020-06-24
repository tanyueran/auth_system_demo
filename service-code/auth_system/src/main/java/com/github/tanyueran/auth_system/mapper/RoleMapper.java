package com.github.tanyueran.auth_system.mapper;

import com.github.tanyueran.auth_system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    // 请求所有得角色
    List<Role> queryAllRoles();

    // 请求角色
    Role queryRoleById(String roleId);

    // 根据code查询角色
    Role queryRoleByCode(String roleCode);

    // 新增code
    Integer insertRole(Role role);

    // 根据角色id查询是否被 用户表 引用着
    Integer queryUserRoleByRoleId(String roleId);

    // 根据角色id查询是否被 菜单表 引用着
    Integer queryRoleMenuByRoleId(String roleId);

    // 删除角色
    Integer deleteRoleById(String roleId);

    // 更新角色
    Integer updateRole(Role role);
}
