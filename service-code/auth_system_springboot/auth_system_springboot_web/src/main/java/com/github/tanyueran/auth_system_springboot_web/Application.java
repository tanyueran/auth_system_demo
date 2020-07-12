package com.github.tanyueran.auth_system_springboot_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.github.tanyueran.auth_system_springboot_mapper.mapper")
@SpringBootApplication(scanBasePackages = {"com.github.tanyueran"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
