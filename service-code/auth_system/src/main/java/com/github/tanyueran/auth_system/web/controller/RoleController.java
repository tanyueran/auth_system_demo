package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Role;
import com.github.tanyueran.auth_system.service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
