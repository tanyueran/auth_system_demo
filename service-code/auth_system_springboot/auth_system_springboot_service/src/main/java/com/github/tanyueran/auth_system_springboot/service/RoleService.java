package com.github.tanyueran.auth_system_springboot.service;

import com.github.tanyueran.auth_system_springboot.modal.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByUserId(String userId);
}
