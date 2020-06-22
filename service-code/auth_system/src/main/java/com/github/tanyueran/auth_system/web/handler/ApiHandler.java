package com.github.tanyueran.auth_system.web.handler;


import com.github.tanyueran.auth_system.web.vo.MyResponseBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = {"com.github.tanyueran.auth_system.web.controller"})
public class ApiHandler implements ResponseBodyAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public MyResponseBody getAccessDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        return new MyResponseBody(100, "暂无权限", null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public MyResponseBody notFound(NoHandlerFoundException e) {
        e.printStackTrace();
        return MyResponseBody.error(null, "没有找到");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MyResponseBody getExceptions(Exception e) {
        e.printStackTrace();
        return MyResponseBody.error(null, e.getMessage());
    }


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof MyResponseBody) {
            return body;
        }
        return MyResponseBody.success(body, "ok");
    }
}
