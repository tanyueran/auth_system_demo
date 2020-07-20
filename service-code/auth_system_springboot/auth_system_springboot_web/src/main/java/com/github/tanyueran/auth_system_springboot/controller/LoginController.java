package com.github.tanyueran.auth_system_springboot.controller;

import com.github.tanyueran.auth_system_springboot.modal.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return "abc";
    }
}
