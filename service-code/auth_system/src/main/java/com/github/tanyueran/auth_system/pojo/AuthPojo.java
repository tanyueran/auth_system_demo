package com.github.tanyueran.auth_system.pojo;

import com.github.tanyueran.auth_system.entity.Auth;

import java.util.List;

public class AuthPojo extends Auth {
    private List<Auth> children;

    public AuthPojo() {
        super();
    }

    public AuthPojo(Integer id, Integer pid, String name, String code, String type, String remark, String data, List<Auth> children) {
        super(id, pid, name, code, type, remark, data);
        this.children = children;
    }

    public List<Auth> getChildren() {
        return children;
    }

    public void setChildren(List<Auth> children) {
        this.children = children;
    }
}
