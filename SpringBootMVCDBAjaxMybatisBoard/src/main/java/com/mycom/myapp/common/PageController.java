package com.mycom.myapp.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

// jsp 페이지 이동 담당
@Controller
public class PageController {

    @GetMapping("/pages/register")
    public String register() {
        return "register";
    }

    @GetMapping("/pages/login")
    public String login() {
        return "login";
    }

    @GetMapping("/pages/board")
    public String board() {

        // 예외 처리 테스트
//        String s = null;
//        s.length();

        return "board";
    }

    // logout 페이지 이동으로 처리
    // LoginController 에 위치시킬 수도 있다.
//    @GetMapping("/pages/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "login";
//    }

    // 페이지 이동과 상관없는 테스트 용도
    // jackson, gson 두 library 의 LocalDateTime 객체의 json 문자열 비교
    // jackson : "2025-04-08T11:16:19.669399"
    // gson : {"date":{"year":2025,"month":4,"day":8},"time":{"hour":12,"minute":56,"second":30,"nano":361600000}}
//    @GetMapping("/pages/json")
//    @ResponseBody
//    public LocalDateTime json() {
//        return LocalDateTime.now();
//    }

    // 현재 Controller 작업 수행 시 에외 발생 => 예외 처리 jsp 이동
    // try-catch 의 catch() 역할
    @ExceptionHandler(Exception.class)
    public String pageExceptionHandler(Exception ex, Model model, HttpServletRequest request) {
        model.addAttribute("exception", ex);
        model.addAttribute("requestURI", request.getRequestURI());
        return "error"; // error.jsp로 포워딩
    }
}
