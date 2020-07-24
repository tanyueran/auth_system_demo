package com.github.tanyueran.auth_system_springboot.service;

import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;

import java.util.List;

// 用户业务模块
public interface UserService {

    /**
     * 根据用户的信息查询用户
     *
     * @return
     */
    User getUserByUser(User user);


    UserDetail getUserDetailByUser(User user) throws Exception;
}
