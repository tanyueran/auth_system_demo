package com.github.tanyueran.auth_system_springboot.service;

import com.github.tanyueran.auth_system_springboot.modal.User;

public interface AuthService {

    String login(User user) throws Exception;
}
