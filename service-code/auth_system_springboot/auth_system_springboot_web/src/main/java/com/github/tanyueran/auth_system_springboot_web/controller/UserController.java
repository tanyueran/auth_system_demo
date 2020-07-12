package com.github.tanyueran.auth_system_springboot_web.controller;

import com.github.tanyueran.auth_system_springboot_pojo.bean.User;
import com.github.tanyueran.auth_system_springboot_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }
}
