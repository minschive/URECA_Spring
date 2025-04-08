package com.mycom.myapp.config;

import com.mycom.myapp.common.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns( // 인터셉트 제외 부분
                        "/",
                        "/index.html",
                        "favicon.ico",
                        "/assets/**",
                        "/pages/login", // page 요청
                        "/pages/register",  // page 요청
                        "/auth/**",     // 로그인 ajax(데이터) 요청
                        "/users/**"     // 회원가입 ajax(데이터) 요청
                );
    }
}
