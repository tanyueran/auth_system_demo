package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.mapper.UserMapper;
import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserByUser(User user) {
        return userMapper.selectOne(user);
    }
}
