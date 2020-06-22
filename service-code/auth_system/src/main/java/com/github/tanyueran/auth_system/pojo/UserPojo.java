package com.github.tanyueran.auth_system.pojo;

import com.github.tanyueran.auth_system.entity.User;

import java.util.List;

public class UserPojo extends User {
    private List<RolePojo> roles;

    public List<RolePojo> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePojo> roles) {
        this.roles = roles;
    }

}
