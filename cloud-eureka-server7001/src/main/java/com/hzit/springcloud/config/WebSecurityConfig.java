package com.hzit.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * author biXia
 * create 2020-07-09-9:55
 * Security认证登录
 *  1.pom文件
 *  2.配置文件
 *  3.配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.csrf().disable();
        //注意：为了可以使用http://${user}:${password}@${host}:${port}/eureka方式登录所以必须是httpbasic
        //如果是form方式，不能使用url登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
