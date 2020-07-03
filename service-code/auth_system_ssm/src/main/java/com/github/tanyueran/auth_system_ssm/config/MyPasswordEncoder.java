package com.github.tanyueran.auth_system_ssm.config;

import org.springframework.security.crypto.password.PasswordEncoder;

// 自定义加密规则
public class MyPasswordEncoder implements PasswordEncoder {
    // 密码加密
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    // 配置密码

    /**
     * @param rawPassword     数据库中查询出来的
     * @param encodedPassword 前台传过来，编码后的
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
