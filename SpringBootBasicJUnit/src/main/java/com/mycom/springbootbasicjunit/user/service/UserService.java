package com.mycom.springbootbasicjunit.user.service;

import com.mycom.springbootbasicjunit.user.dto.UserResultDto;
import com.mycom.springbootbasicjunit.user.entity.User;

public interface UserService {
    UserResultDto insertUser(User user);
}
