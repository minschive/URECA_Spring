package com.mycom.myapp.auth.controller;

import com.mycom.myapp.auth.dto.LoginResultDto;
import com.mycom.myapp.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResultDto login(@RequestBody Map<String, String> loginData) {
        return loginService.login(loginData.get("username"), loginData.get("password"));
    }
}
