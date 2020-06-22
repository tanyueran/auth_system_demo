package com.github.tanyueran.auth_system.mapper;


import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.pojo.RolePojo;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    UserPojo getUserInfoByUserCode(String userCode);

    // 新增用户
    Integer addUser(User user);
}
