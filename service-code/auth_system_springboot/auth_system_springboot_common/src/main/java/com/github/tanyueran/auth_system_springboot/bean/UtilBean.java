package com.github.tanyueran.auth_system_springboot.bean;

import com.github.tanyueran.auth_system_springboot.utils.IdBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilBean {

    @Bean
    public IdBuilder idBuilder() {
        return new IdBuilder(1, 1, 1);
    }
}
