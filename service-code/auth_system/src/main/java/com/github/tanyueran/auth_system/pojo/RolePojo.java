package com.github.tanyueran.auth_system.pojo;


import com.github.tanyueran.auth_system.entity.Auth;
import com.github.tanyueran.auth_system.entity.Role;

import java.util.List;

public class RolePojo extends Role {
    List<Auth> auths;

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    @Override
    public String toString() {
        return "RolePojo{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", code='" + super.getCode() + '\'' +
                ", auths=" + auths +
                '}';
    }
}
