package com.mycom.myapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    // Filter 와 달리 Interceptor 는 View 로 forwarding 하는 과정에서도 동작
    // /abc : DispatcherServlet 이후 접근, Controller 접근 과정에서 한번 실행, 다시 DispatcherServlet
    // /abc/abc.html : DispatcherServlet 이 다시 처리과정에서 한번 더 동작
    @GetMapping("/abc")
    public String abc() {
        System.out.println("/abc");
        return "/abc/abc.html";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("/login");
        return "/login/login.html";
    }

    @GetMapping("/login/success")
    public String loginSuccess(HttpSession session) {
        System.out.println("/loginSuccess");
        session.setAttribute("login", "success");
        return "/login/loginSuccess.html";
    }
}
