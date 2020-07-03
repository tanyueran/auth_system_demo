package com.github.tanyueran.auth_system_ssm.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.entity.Role;
import com.github.tanyueran.auth_system_ssm.entity.User;
import com.github.tanyueran.auth_system_ssm.mapper.UserMapper;
import com.github.tanyueran.auth_system_ssm.pojo.MenuPojo;
import com.github.tanyueran.auth_system_ssm.pojo.Page;
import com.github.tanyueran.auth_system_ssm.pojo.RolePojo;
import com.github.tanyueran.auth_system_ssm.pojo.UserPojo;
import com.github.tanyueran.auth_system_ssm.service.UserService;
import com.github.tanyueran.auth_system_ssm.web.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(noRollbackFor = RuntimeException.class)
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    // security认证
    @Override
    public UserDetails loadUserByUsername(String userCode) throws AuthenticationException {
        UserPojo user = getUserByUserCode(userCode);
        if (user == null) {
            throw new UsernameNotFoundException("账号或者密码错误");
        }
        if (user.getActive().equals("0")) {
            throw new LockedException("账号被锁定了");
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
        UserDetails details = new org.springframework.security.core.userdetails.User(userCode, user.getPassword(), user.getActive().equals("1"), true, true, true, list);
        return details;
    }

    @Override
    public UserPojo getUserByUserCode(String userCode) {
        return userMapper.getUserInfoByUserCode(userCode);
    }

    @Override
    public UserPojo getUserInfoByUserCode(String userCode) {
        User user = userMapper.getUserInfoByCode(userCode);
        List<Role> roles = userMapper.getRolesByUserId(user.getId());
        UserPojo userPojo = JSONObject.parseObject(JSONObject.toJSONString(user), UserPojo.class);
        List<RolePojo> rolePojos = new ArrayList<>();
        roles.forEach(item -> {
            RolePojo rolePojo = new RolePojo();
            rolePojo.setMenus(null);
            rolePojo.setId(item.getId());
            rolePojo.setRemark(item.getRemark());
            rolePojo.setRoleName(item.getRoleName());
            rolePojo.setCreateTime(item.getCreateTime());
            rolePojo.setUpdateTime(item.getUpdateTime());
            rolePojos.add(rolePojo);
        });
        userPojo.setRoles(rolePojos);
        return userPojo;
    }

    @Override
    public Boolean addUser(User user) throws Exception {
        // 查询用户账号是否已经存在
        User userInfoByCode = userMapper.getUserInfoByCode(user.getUserCode());
        if (userInfoByCode != null) {
            throw new Exception("该账号已被使用，请更换");
        }
        Integer i = userMapper.addUser(user);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getUserDataByPage(Page page) {
        return userMapper.queryUserDataByPage(page);
    }

    @Override
    public Integer getUserSizeByKeyword(String keyword) {
        return userMapper.getUserSizeByKeyword(keyword);
    }

    @Override
    public Boolean editUserByUserId(User user) throws Exception {
        // 根据id查询以前的信息
        User user_old = userMapper.getUserInfoById(user.getId());
        if (user_old == null) {
            throw new Exception("更新的用户不存在");
        }
        int i = 0;
        // 判断账号是否改变
        // 没有，直接修改
        // 改了，判断是否账号是否可用
        if (!user_old.getUserCode().equals(user.getUserCode())) {
            User userInfoByCode = userMapper.getUserInfoByCode(user.getUserCode());
            if (userInfoByCode == null) {
                // 直接修改
                i = userMapper.updateUserByUserId(user);
            } else {
                throw new Exception("当前账号已被失败，请更换");
            }
        } else {
            // 直接修改
            i = userMapper.updateUserByUserId(user);
        }
        return i > 0;
    }

    @Override
    public Boolean deleteUserById(String userId) {
        // 删除用户和角色的中间表信息
        // 删除用户
        Integer i = userMapper.deleteUserRoleByUserId(userId);
        Integer j = userMapper.deleteUserById(userId);
        return i >= 0 && j > 0;
    }

    @Override
    public List<Role> getRolesByUserId(String userId) {
        return userMapper.getRolesByUserId(userId);
    }

    @Override
    public Boolean updateRole(String userId, List<String> roleIdList) {
        // 先删除之前的用户上的角色
        Integer i = userMapper.deleteRoleByUserId(userId);
        if (roleIdList.size() == 0) {
            return i >= 0;
        }
        List<Map<String, String>> list = new ArrayList<>();
        roleIdList.forEach(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", CommonController.idBuilder.nextId() + "");
            map.put("userId", userId);
            map.put("roleId", item);
            list.add(map);
        });
        Integer j = userMapper.addRole(list);
        // 在添加用户的心角色
        return i >= 0 && j >= 0;
    }
}
