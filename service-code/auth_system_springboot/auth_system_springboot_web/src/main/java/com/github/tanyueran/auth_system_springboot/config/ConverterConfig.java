package com.github.tanyueran.auth_system_springboot.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;


// 自定义的消息转换器
@Configuration
public class ConverterConfig {

    @Bean
    public HttpMessageConverter MyHttpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }

}
