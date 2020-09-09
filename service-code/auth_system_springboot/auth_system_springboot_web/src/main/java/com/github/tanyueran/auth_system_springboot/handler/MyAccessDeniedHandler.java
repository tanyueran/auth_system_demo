package com.github.tanyueran.auth_system_springboot.handler;

import com.github.tanyueran.auth_system_springboot.utils.HttpResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理认证过的用户访问无权限资源时的异常
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("无权访问异常：+++++++++++++++++++");
        e.printStackTrace();
        // 可以针对不同的异常，返回响应的文字
        HttpResponseUtil.response(httpServletResponse, e.getMessage());
    }
}
