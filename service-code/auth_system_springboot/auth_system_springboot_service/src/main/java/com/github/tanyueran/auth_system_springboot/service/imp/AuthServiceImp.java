package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import com.github.tanyueran.auth_system_springboot.service.UserService;
import com.github.tanyueran.auth_system_springboot.utils.JwtUtil;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PrivateKey privateKey;

    @Value("${redis.login.prefix}")
    private String loginPrefix;

    // 生成token
    private String makeToken(Map<String, String> map) throws Exception {
        return JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
    }

    @Override
    public String login(User user) throws Exception {
        UserDetail detail = userService.getUserDetailByUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("userCode", detail.getUser().getUserCode());
        String token = makeToken(map);
        return token;
    }
}
