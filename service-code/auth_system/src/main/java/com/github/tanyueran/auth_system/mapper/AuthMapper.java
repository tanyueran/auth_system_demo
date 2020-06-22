package com.github.tanyueran.auth_system.mapper;

import com.github.tanyueran.auth_system.pojo.AuthPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    // 查询菜单
    List<AuthPojo> getMenuAuth();

    // 删除资源
    Integer deleteAuthById(String id);
}
