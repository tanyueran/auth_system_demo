package com.github.tanyueran.auth_system_springboot_service.service.imp;

import com.github.tanyueran.auth_system_springboot_mapper.mapper.UserMapper;
import com.github.tanyueran.auth_system_springboot_pojo.bean.User;
import com.github.tanyueran.auth_system_springboot_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
