package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import com.github.tanyueran.auth_system.service.UserService;
import com.github.tanyueran.auth_system.utils.JwtUtil;
import com.github.tanyueran.auth_system.web.vo.MyResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("人员模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicKey publicKey;

    // 注册
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public MyResponseBody register(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserCode())
                || StringUtils.isEmpty(user.getPassword())) {
            return MyResponseBody.error(false, "账号不可为空");
        }
        if (userService.addUser(user)) {
            return MyResponseBody.success(true, "注册成功");
        }
        return MyResponseBody.error(false, "注册失败");
    }

    // 根据token获取用户信息
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public UserPojo getUserInfo(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        Map<String, String> map = JwtUtil.verifyToken(token, (RSAPublicKey) publicKey);
        UserPojo user = userService.getUserByUserCode(map.get("userCode"));
        user.setPassword(null);
        return user;
    }


    @GetMapping("/get/{userCode}")
    @Secured({"ROLE_SMANAGER", "ROLE_MANAGER"})
    public User getUserInfo(@PathVariable("userCode") String userCode) {
        User user = userService.getUserByUserCode(userCode);
        user.setPassword(null);
        return user;
    }
}
