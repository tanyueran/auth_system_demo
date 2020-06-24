package com.github.tanyueran.auth_system.service.imp;

import com.github.tanyueran.auth_system.entity.Role;
import com.github.tanyueran.auth_system.mapper.RoleMapper;
import com.github.tanyueran.auth_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRole() {
        return roleMapper.queryAllRoles();
    }

    @Override
    public Boolean addRole(Role role) throws Exception {
        // 判断code是否可用
        //      可用，直接插入，
        //      不可用，报错
        Role role1 = roleMapper.queryRoleByCode(role.getRoleCode());
        if (role1 != null) {
            throw new Exception("角色标识已被使用，请更换");
        }
        Integer i = roleMapper.insertRole(role);
        return i > 0;
    }

    @Override
    public Boolean updateRole(Role role) throws Exception {
        // 根据id查询原来的，
        //  1、code改变了吗
        //      没有 ，直接更新
        //      有，判断code是否可用
        Role role1 = roleMapper.queryRoleById(role.getId());
        if (role1 == null) {
            throw new Exception("被更新的角色不存在");
        }
        if (role1.getRoleCode().equals(role.getRoleCode())) {
            Integer j = roleMapper.updateRole(role);
            return j > 0;
        } else {
            Role role2 = roleMapper.queryRoleByCode(role.getRoleCode());
            if (role2 == null) {
                Integer i = roleMapper.updateRole(role);
                return i > 0;
            } else {
                throw new Exception("角色code已被使用，请更换");
            }
        }
    }

    @Override
    public Boolean deleteRoleById(String id) throws Exception {
        // 1、判断是否被 用户表 引用着，
        //      没有，
        //          2、判断是否用 菜单表 引用着
        //             -- 没有，可以删除
        //             -- 有，不可以删除
        //      有，提示
        Integer j = roleMapper.queryUserRoleByRoleId(id);
        if (j > 0) {
            throw new Exception("此角色被用户使用着，不可删除");
        } else {
            Integer i = roleMapper.queryRoleMenuByRoleId(id);
            if (i > 0) {
                throw new Exception("此角色跟菜单关联着，不可删除");
            }
            Integer m = roleMapper.deleteRoleById(id);
            if (m == 0) {
                throw new Exception("该id不存在角色");
            }
            return true;
        }
    }
}
