package com.github.tanyueran.auth_system.service.imp;

import com.github.tanyueran.auth_system.entity.Auth;
import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.mapper.UserMapper;
import com.github.tanyueran.auth_system.pojo.RolePojo;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import com.github.tanyueran.auth_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    // security认证
    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        try {
            UserPojo user = getUserByUserCode(userCode);
            System.out.println(user);
            if (user == null) {
                return null;
            }
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            List<RolePojo> roles = user.getRoles();
            if (roles != null) {
                for (RolePojo role : roles) {
                    list.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
                    List<Auth> auths = role.getAuths();
                    if (auths != null) {
                        for (Auth auth : auths) {
                            list.add(new SimpleGrantedAuthority(auth.getCode()));
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
