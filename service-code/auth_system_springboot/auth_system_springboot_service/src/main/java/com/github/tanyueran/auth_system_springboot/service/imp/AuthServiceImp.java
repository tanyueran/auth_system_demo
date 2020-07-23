package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import com.github.tanyueran.auth_system_springboot.service.MenuService;
import com.github.tanyueran.auth_system_springboot.service.RoleService;
import com.github.tanyueran.auth_system_springboot.service.UserService;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate myRedisTemplate;

    @Override
    public String login(User user) throws Exception {
        UserDetail userDetail = new UserDetail();
        // 获取详细的信息保存再redis中，
        User user1 = userService.getUserByUser(user);
        if (user1 == null) {
            throw new Exception("账号或密码错误！");
        }
        userDetail.setUser(user1);
        List<Role> roles = roleService.getRolesByUserId(user1.getId());
        userDetail.setRoles(roles);
        List<String> roleIds = new ArrayList<>();
        roles.forEach(item -> roleIds.add(item.getId()));
        List<Menu> menus = menuService.getMenuByRoleIds(roleIds);
        userDetail.setMenus(menus);
        myRedisTemplate.opsForValue().set("USER_" + user1.getId(), userDetail);
        // 返回token
        return "token";
    }
}
