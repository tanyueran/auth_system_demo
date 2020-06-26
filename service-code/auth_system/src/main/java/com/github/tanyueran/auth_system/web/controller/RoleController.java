package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.entity.Role;
import com.github.tanyueran.auth_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/all")
    public List<Role> getAllRoles() {
        return roleService.getAllRole();
    }

    @PostMapping("/")
    public Boolean addRole(@RequestBody Role role) throws Exception {
        return roleService.addRole(role);
    }

    @PutMapping("/")
    public Boolean updateRole(@RequestBody Role role) throws Exception {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public Boolean removeRole(@PathVariable("id") String id) throws Exception {
        return roleService.deleteRoleById(id);
    }

    // 获取角色下的菜单
    @GetMapping("/menu/{id}")
    public List<Menu> getMenusByRoleId(@PathVariable("id") String id) {
        return roleService.getMenuByRoleId(id);
    }

    // 更新角色下挂载的权限
    @PutMapping("/menu/{roleId}/{menuIdList}")
    public Boolean updateMenuByRoleId(@PathVariable("roleId") String roleId, @PathVariable("menuIdList") String menuIdList) {
        if (menuIdList.equals("null")) {
            return roleService.updateMenuByRoleId(roleId, new ArrayList<String>());
        }
        return roleService.updateMenuByRoleId(roleId, Arrays.asList(menuIdList.split(",")));
    }
}
