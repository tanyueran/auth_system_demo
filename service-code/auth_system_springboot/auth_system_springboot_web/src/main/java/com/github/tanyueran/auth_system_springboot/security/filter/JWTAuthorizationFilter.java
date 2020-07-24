package com.github.tanyueran.auth_system_springboot.security.filter;

import com.alibaba.druid.util.StringUtils;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import com.github.tanyueran.auth_system_springboot.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private AuthService authService;

    private PublicKey publicKey;

    public JWTAuthorizationFilter() {
    }

    public JWTAuthorizationFilter(AuthService authService, PublicKey publicKey) {
        this.authService = authService;
        this.publicKey = publicKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            // 解析token，获取redis中账号的权限，保存在这次的session中
            Map<String, String> map = null;
            try {
                map = JwtUtil.verifyToken(token, (RSAPublicKey) publicKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (map == null) {
                SecurityContextHolder.getContext().setAuthentication(null);
            } else {
                String userCode = map.get("userCode");
                Authentication authResult = new UsernamePasswordAuthenticationToken(userCode, null, null);
                // 将获取到的数据放置此次的数据中
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        chain.doFilter(request, response);
    }
}
