package com.github.tanyueran.auth_system.service.imp;

import com.github.tanyueran.auth_system.mapper.AuthMapper;
import com.github.tanyueran.auth_system.pojo.AuthPojo;
import com.github.tanyueran.auth_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<AuthPojo> queryAuth() throws Exception {
        return authMapper.getMenuAuth();
    }

    @Override
    public Integer deleteAuthById(String id) {
        return authMapper.deleteAuthById(id);
    }
}
