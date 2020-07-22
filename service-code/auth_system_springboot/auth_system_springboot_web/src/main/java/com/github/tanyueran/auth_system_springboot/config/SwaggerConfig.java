package com.github.tanyueran.auth_system_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    // 这是一个swagger分组
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.github.tanyueran.auth_system_springboot.controller"))   // 指定controller包
                .paths(PathSelectors.any())         // 所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("****平台接口api")        // 文档页标题
                .contact(new Contact("tanyueran",// 联系人信息
                        "https://github.com/tanyueran",
                        ""))
                .description("描述信息")  // 详细信息
                .version("0.0.1")   // 文档版本号
                .termsOfServiceUrl("https://www.baidu.com") // 网站地址
                .build();
    }


}
