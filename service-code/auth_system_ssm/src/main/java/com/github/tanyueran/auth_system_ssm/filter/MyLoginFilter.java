package com.github.tanyueran.auth_system_ssm.filter;

import com.alibaba.fastjson.JSON;
import com.github.tanyueran.auth_system_ssm.utils.ResponseDataUtil;
import com.github.tanyueran.auth_system_ssm.web.vo.MyResponseBody;
import com.github.tanyueran.auth_system_ssm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 自定义的登录过滤器
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private PrivateKey privateKey;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // 从json中获取username和password
        try {
            String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
            Map<String, String> map = JSON.parseObject(body, Map.class);
            if (map == null) {
                ResponseDataUtil.ResponseData(response, MyResponseBody.error(false, "账号密码不可为空"));
                return null;
            }
            String username = map.get("username");
            String password = map.get("password");
            Authentication authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            return this.getAuthenticationManager().authenticate(authRequest);

        } catch (IOException e) {
            try {
                ResponseDataUtil.ResponseData(response, MyResponseBody.error(false, "服务器出错"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    // 认证成功给用户返回token
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response, FilterChain chain, Authentication authResult) {
        Map<String, String> map = new HashMap<>();
        map.put("userCode", authResult.getName());

        map.put("roles", JSON.toJSONString(authResult.getAuthorities()));
        String token = null;
        try {
            token = JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
            ResponseDataUtil.ResponseData(response, MyResponseBody.success(token, "登录成功"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 认证失败
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        String str = null;
        if (failed instanceof LockedException) {
            str = "账号未激活";
        } else if (failed instanceof BadCredentialsException || failed instanceof UsernameNotFoundException) {
            str = "账号或者密码错误";
        } else {
            str = failed.getMessage();
        }
        ResponseDataUtil.ResponseData(response, MyResponseBody.error(false, "登录失败：" + str));
    }
}
