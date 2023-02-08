package com.springboot.pjt1.config;

import com.springboot.pjt1.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()	// 모든 요청에 대해서 허용하라.
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")	// 로그아웃에 대해서 성공하면 "/"로 이동
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/login-success")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);	// oauth2 로그인에 성공하면, 유저 데이터를 가지고 우리가 생성한
        // customOAuth2UserService에서 처리를 하겠다. 그리고 "/login-success"로 이동하라.
    }
}
