package com.github.tanyueran.auth_system.service.imp;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.mapper.UserMapper;
import com.github.tanyueran.auth_system.pojo.MenuPojo;
import com.github.tanyueran.auth_system.pojo.RolePojo;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import com.github.tanyueran.auth_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(noRollbackFor = RuntimeException.class)
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    // security认证
    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        try {
            UserPojo user = getUserByUserCode(userCode);
            if (user == null) {
                return null;
            }
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            List<RolePojo> roles = user.getRoles();
            if (roles != null) {
                for (RolePojo role : roles) {
                    list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
                    // 权限全部添加按钮权限，（菜单code:按钮code）
                    List<MenuPojo> menus = role.getMenus();
                    if (menus != null) {
                        for (MenuPojo menu : menus) {
                            List<Button> buttons = menu.getButtons();
                            if (buttons != null) {
                                for (Button button : buttons) {
                                    list.add(new SimpleGrantedAuthority(menu.getMenuCode() + ":" + button.getButtonCode()));
                                }
                            }
                        }
                    }
                }
            }
            UserDetails details = new org.springframework.security.core.userdetails.User(userCode, user.getPassword(), list);
            return details;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserPojo getUserByUserCode(String userCode) {
        return userMapper.getUserInfoByUserCode(userCode);
    }

    @Override
    public Boolean addUser(User user) throws RuntimeException {
        Integer i = userMapper.addUser(user);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
