package com.github.tanyueran.auth_system_springboot.service;

import com.github.tanyueran.auth_system_springboot.modal.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuByRoleIds(List<String> roleIds);
}
