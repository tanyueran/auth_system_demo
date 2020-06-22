package com.github.tanyueran.auth_system.service;

import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserPojo getUserByUserCode(String userCode);

    Boolean addUser(User user) throws RuntimeException;
}
