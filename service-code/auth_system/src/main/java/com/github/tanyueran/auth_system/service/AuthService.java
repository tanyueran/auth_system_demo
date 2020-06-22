package com.github.tanyueran.auth_system.service;

import com.github.tanyueran.auth_system.pojo.AuthPojo;

import java.util.List;

public interface AuthService {
    List<AuthPojo> queryAuth() throws Exception;

    Integer deleteAuthById(String id);
}
