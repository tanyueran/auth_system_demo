package com.github.tanyueran.auth_system_springboot.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import com.github.tanyueran.auth_system_springboot.service.MenuService;
import com.github.tanyueran.auth_system_springboot.service.RoleService;
import com.github.tanyueran.auth_system_springboot.service.UserService;
import com.github.tanyueran.auth_system_springboot.utils.JwtUtil;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate userDetailRedisTemplate;

    @Autowired
    private PrivateKey privateKey;


    @Value("${redis.login.prefix}")
    private String loginPrefix;

    // 查询用户的信息
    private UserDetail getUserDetail(User user) throws Exception {
        if (user == null) throw new Exception("参数不可为空");
        UserDetail userDetail = new UserDetail();
        User user1 = userService.getUserByUser(user);
        if (user1 == null) throw new Exception("账号或密码错误！");
        userDetail.setUser(user1);
        List<Role> roles = roleService.getRolesByUserId(user1.getId());
        userDetail.setRoles(roles);
        List<String> roleIds = new ArrayList<>();
        roles.forEach(item -> roleIds.add(item.getId()));
        List<Menu> menus = menuService.getMenuByRoleIds(roleIds);
        userDetail.setMenus(menus);
        return userDetail;
    }

    // 生成token
    private String makeToken(Map<String, String> map) throws Exception {
        return JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
    }

    @Override
    public String login(User user) throws Exception {
        UserDetail detail = (UserDetail) userDetailRedisTemplate.opsForValue().get(loginPrefix + user.getUserCode());
        Map<String, String> map = new HashMap<>();
        if (detail == null) {
            detail = this.getUserDetail(user);
            userDetailRedisTemplate.opsForValue().set(loginPrefix + user.getUserCode(), detail);
        }
        map.put("userCode", detail.getUser().getUserCode());
        String token = makeToken(map);
        return token;
    }
}
