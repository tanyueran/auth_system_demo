package com.github.tanyueran.auth_system_springboot.config;

import com.github.tanyueran.auth_system_springboot.security.filter.JWTAuthorizationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/api/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加权限
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                // 禁用表单认证和HTTPbasic
                .formLogin().disable()
                .httpBasic().disable()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 禁用csrf
                .csrf().disable()
                // 添加jwt校验
                .addFilterBefore(new JWTAuthorizationFilter(this.authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }
}
