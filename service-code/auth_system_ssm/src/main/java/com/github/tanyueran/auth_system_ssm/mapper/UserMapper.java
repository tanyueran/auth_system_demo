package com.github.tanyueran.auth_system_ssm.mapper;


import com.github.tanyueran.auth_system_ssm.entity.Role;
import com.github.tanyueran.auth_system_ssm.entity.User;
import com.github.tanyueran.auth_system_ssm.pojo.Page;
import com.github.tanyueran.auth_system_ssm.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户信息
    UserPojo getUserInfoByUserCode(String userCode);

    // 根据id查询信息
    User getUserInfoById(String userId);

    // 根据账号查询用户信息
    User getUserInfoByCode(String userCode);

    // 新增用户
    Integer addUser(User user);

    // 根据关键词分页查询用户
    List<User> queryUserDataByPage(Page page);

    // 获取关键字查询用户的总数
    Integer getUserSizeByKeyword(@Param("keyword") String keyword);

    // 编辑用户
    Integer updateUserByUserId(User user);

    // 删除用户和角色的中间表
    Integer deleteUserRoleByUserId(String userId);

    // 根据id删除用户
    Integer deleteUserById(String userId);

    // 根据用户id获取用户的角色
    List<Role> getRolesByUserId(String userId);

    // 删除用户的角色
    Integer deleteRoleByUserId(String userId);

    // 添加用户的橘色
    Integer addRole(List<Map<String, String>> list);
}
