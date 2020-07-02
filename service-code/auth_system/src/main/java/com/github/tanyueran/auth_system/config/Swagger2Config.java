package com.github.tanyueran.auth_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    // 这是一个swagger分组
    @Bean
    public Docket docket() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
        ticketPar.name("Authorization").description("user ticket")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.tanyueran.auth_system.web.controller"))
                .paths(PathSelectors.any())
                .build()
                // 添加传递认证信息
                .globalOperationParameters(pars)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("测试的一个权限管理系统")
                                .description("这个是描述信息")
                                .version("v1.0")
                                .contact(new Contact("tanyueran", "http://github.com/tanyueran", "txloveu@163.com"))
                                .license("The Apache License")
                                .licenseUrl("http://www.baidu.com")
                                .build());

    }
}
