package com.github.tanyueran.auth_system_springboot_web.controller;

import com.github.tanyueran.auth_system_springboot_service.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
