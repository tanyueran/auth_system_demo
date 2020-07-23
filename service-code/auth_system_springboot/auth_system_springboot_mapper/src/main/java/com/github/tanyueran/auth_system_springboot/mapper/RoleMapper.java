package com.github.tanyueran.auth_system_springboot.mapper;

import com.github.tanyueran.auth_system_springboot.modal.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    // 根据userId查询角色数据
    List<Role> getRolesByUserId(String userId);
}