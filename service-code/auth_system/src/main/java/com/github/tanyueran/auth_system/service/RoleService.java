package com.github.tanyueran.auth_system.service;


import com.github.tanyueran.auth_system.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    // 新增角色
    Boolean addRole(Role role) throws Exception;

    // 编辑角色
    Boolean updateRole(Role role) throws Exception;

    // 删除角色
    Boolean deleteRoleById(String id) throws Exception;

}
