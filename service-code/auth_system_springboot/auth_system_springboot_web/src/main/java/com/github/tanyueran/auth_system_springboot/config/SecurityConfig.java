package com.github.tanyueran.auth_system_springboot.config;

import com.github.tanyueran.auth_system_springboot.handler.MyAccessDeniedHandler;
import com.github.tanyueran.auth_system_springboot.handler.MyAuthenticationEntryPoint;
import com.github.tanyueran.auth_system_springboot.security.filter.JWTAuthorizationFilter;
import com.github.tanyueran.auth_system_springboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.PrivateKey;
import java.security.PublicKey;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PublicKey publicKey;

    @Autowired
    private AuthService authService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                // swagger-ui的
                "/v2/api-docs",//swagger api json
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/swagger-ui.html",
                "/webjars/**",
                "/common/key/**",
                "/druid/**",
                "/login"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加权限
        /*
         * 注意：此处配置的匿名、允许所有的，自定义的filter都会执行
         * */
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
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
                // 添加自定义的异常处理器
                .exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .and()
                // 添加jwt校验
                .addFilterBefore(new JWTAuthorizationFilter(authService, publicKey), UsernamePasswordAuthenticationFilter.class);
    }
}
