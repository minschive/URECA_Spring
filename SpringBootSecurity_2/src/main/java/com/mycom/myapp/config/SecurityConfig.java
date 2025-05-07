package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/", "/index.html").permitAll() // /, index.html 모두 허락
                                    .requestMatchers("/customer/**").hasAnyRole("CUSROMER", "ADMIN")
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated(); // 나머지는 모두 인증
                        }
                )
                .formLogin( form ->
                        form
                                .defaultSuccessUrl("/",true)
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
