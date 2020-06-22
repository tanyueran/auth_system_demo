package com.github.tanyueran.auth_system.filter;

import com.alibaba.fastjson.JSON;
import com.github.tanyueran.auth_system.utils.ResponseDataUtil;
import com.github.tanyueran.auth_system.web.vo.MyResponseBody;
import com.github.tanyueran.auth_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;

// 自定义的jwt验证
public class MyVerifyFilter extends BasicAuthenticationFilter {

    @Autowired
    private PublicKey publicKey;

    public MyVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header)) {
            ResponseDataUtil.ResponseNoLoginData(response, MyResponseBody.error(false, "请登录"));
        } else {
            Map<String, String> map = null;
            try {
                map = JwtUtil.verifyToken(header, (RSAPublicKey) publicKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (map == null) {
                response.setContentType("application/json;charset=utf-8");
                MyResponseBody result = MyResponseBody.error(false, "验证失败");
                response.getWriter().write(JSON.toJSONString(result));
            } else {
                String userCode = map.get("userCode");
                List<SimpleGrantedAuthority> roles = JSON.parseArray(map.get("roles"), SimpleGrantedAuthority.class);
                Authentication authResult = new UsernamePasswordAuthenticationToken(userCode, null, roles);
                // 将获取到的数据放置此次的数据中
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        chain.doFilter(request, response);
    }
}
