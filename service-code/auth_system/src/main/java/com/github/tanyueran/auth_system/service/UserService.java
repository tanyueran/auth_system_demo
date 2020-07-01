package com.github.tanyueran.auth_system.service;

import com.github.tanyueran.auth_system.entity.Role;
import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.pojo.Page;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserPojo getUserByUserCode(String userCode);

    UserPojo getUserInfoByUserCode(String userCode);

    Boolean addUser(User user) throws Exception;

    // 根据关键词分页查询用户
    List<User> getUserDataByPage(Page page);

    // 获取总数
    Integer getUserSizeByKeyword(String keyword);

    // 编辑用户
    Boolean editUserByUserId(User user) throws Exception;

    // 删除用户
    Boolean deleteUserById(String userId);

    // 获取用户的角色
    List<Role> getRolesByUserId(String userId);

    // 更新用户的角色
    Boolean updateRole(String userId, List<String> roleIdList);
}
