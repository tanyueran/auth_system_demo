package com.github.tanyueran.auth_system_springboot_service.service.imp;

import com.github.tanyueran.auth_system_springboot_mapper.mapper.UserMapper;
import com.github.tanyueran.auth_system_springboot_pojo.bean.User;
import com.github.tanyueran.auth_system_springboot_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }
}
