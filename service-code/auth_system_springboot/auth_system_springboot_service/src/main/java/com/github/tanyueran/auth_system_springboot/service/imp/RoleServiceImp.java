package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.mapper.RoleMapper;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(String userId) {
        return roleMapper.getRolesByUserId(userId);
    }
}
