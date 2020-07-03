package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Role;
import com.github.tanyueran.auth_system.entity.User;
import com.github.tanyueran.auth_system.pojo.Page;
import com.github.tanyueran.auth_system.pojo.UserPojo;
import com.github.tanyueran.auth_system.service.UserService;
import com.github.tanyueran.auth_system.utils.JwtUtil;
import com.github.tanyueran.auth_system.web.vo.MyResponseBody;
import com.github.tanyueran.auth_system.web.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "人员模块", tags = "人员模块", description = "人员模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicKey publicKey;

    // 注册
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public MyResponseBody register(@RequestBody User user) throws Exception {
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
        UserPojo user = userService.getUserInfoByUserCode(map.get("userCode"));
        user.setPassword(null);
        return user;
    }

    // 根据关键词分页查询用户
    @PostMapping("/page")
    public PageResult getUserDataByPage(@RequestBody Page page) {
        PageResult result = new PageResult();
        List<User> list = userService.getUserDataByPage(page);
        list.forEach(item -> item.setPassword(null));
        result.setData(list);
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        int total = userService.getUserSizeByKeyword(page.getKeyword());
        result.setTotal(total);
        result.setTotalPage((int) Math.ceil((double) total / (double) page.getSize()));
        return result;
    }

    @PostMapping("/add")
    @ApiOperation("新增用户")
    public Boolean addUser(@RequestBody User user) throws Exception {
        user.setPassword("password");
        return userService.addUser(user);
    }

    @ApiOperation("编辑用户")
    @PutMapping("/edit")
    public Boolean editUserByUserId(@RequestBody User user) throws Exception {
        return userService.editUserByUserId(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    public Boolean deleteUserById(@PathVariable("id") String id) {
        return userService.deleteUserById(id);
    }

    @ApiOperation("获取用户的角色")
    @GetMapping("/role/{id}")
    public List<Role> getRoleByUserId(@PathVariable("id") String id) {
        return userService.getRolesByUserId(id);
    }

    @ApiOperation("编辑用户的角色")
    @PutMapping("/role/{id}/{roleIdList}")
    public Boolean updateRoleByUserId(@PathVariable("id") String userId, @PathVariable("roleIdList") String roleIdList) {
        if (roleIdList.equals("null")) {
            return userService.updateRole(userId, new ArrayList<>());
        }
        return userService.updateRole(userId, Arrays.asList(roleIdList.split(",")));
    }

}
