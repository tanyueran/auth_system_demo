package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.pojo.AuthPojo;
import com.github.tanyueran.auth_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    // 按菜单层级查询权限
    @PostMapping("/page")
    public List<AuthPojo> getAuthByPage() throws Exception {
        return authService.queryAuth();
    }

    // 删除资源
    @DeleteMapping("/{id}")
    public Boolean delAuthById(@PathVariable("id") String id) {
        int i = authService.deleteAuthById(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

}
