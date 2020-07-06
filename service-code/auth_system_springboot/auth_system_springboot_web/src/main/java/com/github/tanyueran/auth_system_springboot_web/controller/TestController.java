package com.github.tanyueran.auth_system_springboot_web.controller;

import com.github.tanyueran.auth_system_springboot_service.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/test")
    public String test() {
        logger.info("test");
        return testService.test();
    }
}
