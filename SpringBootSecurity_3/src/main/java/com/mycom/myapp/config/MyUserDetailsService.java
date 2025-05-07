package com.mycom.myapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    // DI
    private final PasswordEncoder passwordEncoder;

    // 기본 form ui 로그인 그대로 ( 우리가 별도의 login 페이지를 제공 X )
    // username, password 가 user / console password 사용 X <= UserDetailsService 를 제공하므로
    // form ui 에 사용자가 입력한 username 값이 loadUserByUsername() 파라미터로 전달 (username)
    // DB 를 통해서 ( JPA 경우 UserRepository 를 거쳐서 ) username 으로 select username, password 를 거쳐서
    // UserDetails 구현 객체를 만들어서 return 해 줘야 한다.
    // UserDetails 구현 객체는 우선 Spring security 에서 제공하는 import org.springframework.security.core.userdetails.User; 를 사용

//    // ROLE 고려 X
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // PasswordEncoder 객체 구현 클래스
//        System.out.println(passwordEncoder.getClass());
//
//        if( "jimin".equals(username) ) {
//            return User.builder()
//                        .username("jimin")
//                        .password(passwordEncoder.encode("1234"))
//                        .build();
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }

    // ROLE 고려 O
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // PasswordEncoder 객체 구현 클래스
        System.out.println(passwordEncoder.getClass());

        if( "jimin".equals(username) ) {
            return User.builder()
                    .username("jimin")
                    .password(passwordEncoder.encode("1234"))
                    .roles("CUSTOMER") // role 이 여러 개라면 .roles("CUSTOMER", "ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
