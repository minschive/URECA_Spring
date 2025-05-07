package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            MyAuthenticationSuccessHandler successHandler,
            MyAuthenticationFailureHandler failureHandler
    ) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers(
                                    "/",
                                    "/index.html",
                                    "/csrf-token",
                                    "/login",
                                    "/register",
                                    "/register.html",
                                    "/users/**"
                                    ).permitAll()
                                    .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated(); // 나머지는 모두 인증
                        }
                )
                // csrf 설정
//                .csrf( csrf -> csrf.disable() ) // 기능 off
                .csrf( csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) ) // cookie csrf token 을 내려 준다.
                .formLogin( form ->
                        form
                                // 사용자 정의 login 페이지를 사용하면 기본적으로 csrf 를 전송하도록 구현해야 한다.
                                // 만약 구현하지 않으면 csrf 토큰이 없다는 오류 발생, 로그인 처리 X
                                // csrf 를 무시하도록 설정도 가능 (비추천)
                                .loginPage("/login.html")
                                .loginProcessingUrl("/login")
                                // ajax 요청 처리를 하는 별도의 Handler
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
//                                .defaultSuccessUrl("/",true)
                                .permitAll()
                )
                .logout( logout -> logout.permitAll() ) // /logout url 로 요청하면 자동으로 Spring Security 가 session 을 invalidate
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
