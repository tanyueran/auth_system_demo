package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.mapper.MenuMapper;
import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByRoleIds(List<String> roleIds) {
        return menuMapper.getMenuByRoleIds(roleIds);
    }
}
