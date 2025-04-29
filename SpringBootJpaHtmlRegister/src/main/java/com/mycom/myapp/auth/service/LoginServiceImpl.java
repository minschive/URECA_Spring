package com.mycom.myapp.auth.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public UserResultDto login(String email, String password) {

        UserResultDto userResultDto = new UserResultDto();

        log.debug("login() 시작");

        // #1. id 가 아닌 email 로 find
        // 2개의 select 문 실행됨
//        Optional<User> optionalUser = userRepository.findByEmail(email);

        // #2. id 로 hardcoding 후 테스트
        // 1개의 join select 문 실행됨
//        Optional<User> optionalUser = userRepository.findById(4L);

        // #3. jpql
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if( optionalUser.isPresent() ) {

            User user = optionalUser.get();
            if ( user.getPassword().equals(password) ) {
                // User -> UserDto
                // UserRole 도 함께 처리
                Map<Integer, String> roles = new HashMap<>();
                user.getUserRoles().forEach(userRole -> roles.put(userRole.getId(), userRole.getName()) );

                UserDto userDto = UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .roles(roles)
                        .build();

                userResultDto.setUserDto(userDto);
                userResultDto.setResult("success");
            } else {
                userResultDto.setResult("fail");
            }

        } else {
            userResultDto.setResult("notfound");
        }

        log.debug("login() 종료");

        return userResultDto;
    }
}
