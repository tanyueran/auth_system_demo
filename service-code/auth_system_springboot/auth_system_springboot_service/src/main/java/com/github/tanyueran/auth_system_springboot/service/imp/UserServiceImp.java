package com.github.tanyueran.auth_system_springboot.service.imp;

import com.github.tanyueran.auth_system_springboot.mapper.UserMapper;
import com.github.tanyueran.auth_system_springboot.modal.Menu;
import com.github.tanyueran.auth_system_springboot.modal.Role;
import com.github.tanyueran.auth_system_springboot.modal.User;
import com.github.tanyueran.auth_system_springboot.service.MenuService;
import com.github.tanyueran.auth_system_springboot.service.RoleService;
import com.github.tanyueran.auth_system_springboot.service.UserService;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    public User getUserByUser(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    @Cacheable(value = "userCode", cacheManager = "userDetailRedisCacheManager", key = "#user.userCode")
    public UserDetail getUserDetailByUser(User user) throws Exception {
        if (user == null) throw new Exception("参数不可为空");
        UserDetail userDetail = new UserDetail();
        User user1 = this.getUserByUser(user);
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
}
