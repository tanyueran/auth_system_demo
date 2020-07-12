package com.github.tanyueran.auth_system_springboot_common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfig {

   /* @Bean
    public ServletRegistrationBean DruidStartView() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet(), "/druid");
        return registration;
    }*/
}
