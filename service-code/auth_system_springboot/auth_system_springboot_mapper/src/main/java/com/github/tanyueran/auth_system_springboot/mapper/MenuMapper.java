package com.github.tanyueran.auth_system_springboot.mapper;

import com.github.tanyueran.auth_system_springboot.modal.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    // 根据roleId集合查询菜单
    List<Menu> getMenuByRoleIds(@Param("roleIds") List<String> roleIds);
}