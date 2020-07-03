package com.github.tanyueran.auth_system_springboot_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.tanyueran")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
