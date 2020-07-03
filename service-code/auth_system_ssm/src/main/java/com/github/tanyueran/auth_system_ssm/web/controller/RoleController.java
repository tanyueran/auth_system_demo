package com.github.tanyueran.auth_system_ssm.web.controller;

import com.github.tanyueran.auth_system_ssm.entity.Menu;
import com.github.tanyueran.auth_system_ssm.entity.Role;
import com.github.tanyueran.auth_system_ssm.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "角色模块", tags = "角色模块", description = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @ApiOperation("获取所有的模块")
    public List<Role> getAllRoles() {
        return roleService.getAllRole();
    }

    @PostMapping("/")
    @ApiOperation("添加角色")
    public Boolean addRole(@RequestBody Role role) throws Exception {
        return roleService.addRole(role);
    }

    @PutMapping("/")
    @ApiOperation("更新角色")
    public Boolean updateRole(@RequestBody Role role) throws Exception {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除角色")
    public Boolean removeRole(@PathVariable("id") String id) throws Exception {
        return roleService.deleteRoleById(id);
    }

    @GetMapping("/menu/{id}")
    @ApiOperation("获取角色下的菜单")
    public List<Menu> getMenusByRoleId(@PathVariable("id") String id) {
        return roleService.getMenuByRoleId(id);
    }

    @PutMapping("/menu/{roleId}/{menuIdList}")
    @ApiOperation("更新角色下挂载的权限")
    public Boolean updateMenuByRoleId(@PathVariable("roleId") String roleId, @PathVariable("menuIdList") String menuIdList) {
        if (menuIdList.equals("null")) {
            return roleService.updateMenuByRoleId(roleId, new ArrayList<String>());
        }
        return roleService.updateMenuByRoleId(roleId, Arrays.asList(menuIdList.split(",")));
    }
}
