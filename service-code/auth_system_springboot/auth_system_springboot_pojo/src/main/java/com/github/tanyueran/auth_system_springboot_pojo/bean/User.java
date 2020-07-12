package com.github.tanyueran.auth_system_springboot_pojo.bean;

public class User {
    private String userName;
    private String userCode;

    public User() {
    }

    public User(String userName, String userCode) {
        this.userName = userName;
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
