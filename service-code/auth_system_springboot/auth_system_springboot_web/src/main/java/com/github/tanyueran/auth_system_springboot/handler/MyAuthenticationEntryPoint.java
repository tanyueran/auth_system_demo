package com.github.tanyueran.auth_system_springboot.handler;

import com.github.tanyueran.auth_system_springboot.utils.HttpResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理匿名用户访问无权限资源时的异常
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("匿名用户访问异常：+++++++++++++++==");
        e.printStackTrace();
        // 可以针对不同的异常，返回响应的文字
        HttpResponseUtil.response(httpServletResponse, e.getMessage());
    }
}
