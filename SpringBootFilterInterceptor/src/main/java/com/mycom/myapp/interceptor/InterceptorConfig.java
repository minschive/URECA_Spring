package com.mycom.myapp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// DI 용도가 아니라, Spring 에게 Interceptor 정책 전달
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // #1
//    @Autowired // 한번만 사용될 것이기에 여기서는 생성자 주입안하고 @Autowired 작성
//    private MyInterceptor myInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor) // registry에 등록
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login/**"); // interceptor 를 적용하지 않을 대상
//    }

    // #2
    @Autowired // 한번만 사용될 것이기에 여기서는 생성자 주입안하고 @Autowired 작성
    private MyInterceptor2 myInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor2) // registry에 등록
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**"); // interceptor 를 적용하지 않을 대상
    }
}
