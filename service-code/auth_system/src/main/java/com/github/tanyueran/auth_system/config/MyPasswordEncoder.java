package com.github.tanyueran.auth_system.config;

import org.springframework.security.crypto.password.PasswordEncoder;

// 自定义加密规则
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
