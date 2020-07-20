package com.github.tanyueran.auth_system_springboot.service;

import com.github.tanyueran.auth_system_springboot.modal.User;

import java.util.List;

// 用户业务模块
public interface UserService {
    /**
     * 查询所有的用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据用户的信息查询用户
     *
     * @return
     */
    User getUserByUser(User user);
}
