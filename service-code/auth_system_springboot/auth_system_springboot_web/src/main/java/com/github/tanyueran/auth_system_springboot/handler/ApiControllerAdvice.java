package com.github.tanyueran.auth_system_springboot.handler;

import com.github.tanyueran.auth_system_springboot.vo.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.github.tanyueran.auth_system_springboot.controller")
public class ApiControllerAdvice implements ResponseBodyAdvice {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler1(Exception e) {
        e.printStackTrace();
        return ResponseResult.error(e.getMessage());
    }


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseResult) {
            return body;
        }
        return ResponseResult.success(body);
    }
}
