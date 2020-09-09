package com.github.tanyueran.auth_system_springboot.controller;

import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import com.github.tanyueran.auth_system_springboot.utils.RsaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;

@RestController
@Api("登录注册模块")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PrivateKey privateKey;

    @PostMapping("/login")
    @ApiOperation("登录")
    public String login(@RequestBody User user) throws Exception {
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUserCode())) {
            throw new Exception("账号密码不可为空");
        }
        User u = new User();
        u.setUserCode(user.getUserCode());
        RsaUtil.privateDecrypt(user.getPassword().getBytes(), privateKey);
        u.setPassword(user.getPassword());
        return authService.login(u);
    }
}
