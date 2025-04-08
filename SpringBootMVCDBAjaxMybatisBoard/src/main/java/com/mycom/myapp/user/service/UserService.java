package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;

// UserResultDto 만들어서 리턴
public interface UserService {
    UserResultDto registerUser(UserDto userDto);
}
