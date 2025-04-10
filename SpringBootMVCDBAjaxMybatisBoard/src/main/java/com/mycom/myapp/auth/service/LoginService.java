package com.mycom.myapp.auth.service;

import com.mycom.myapp.user.dto.UserDto;

import java.util.Optional;

// LoginResultDto 를 만들지 않고 UserDto 를 return 하는 이유는 Controller 에서 session 처리를 하고 난 뒤에 front 에 응답
// 로그인에 성공한 사용자 정보(UserDto)를 세션에 저장해야 하기 때문에
// 로그인 결과만 담은 Dto가 아니라 실제 사용자 정보가 담긴 UserDto를 넘김
public interface LoginService {
    Optional<UserDto> login(UserDto userDto);
}
