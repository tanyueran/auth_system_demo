package com.github.tanyueran.auth_system_springboot;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.github.tanyueran.auth_system_springboot.mapper")
@SpringBootApplication(scanBasePackages = {"com.github.tanyueran"})
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
