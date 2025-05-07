package com.mycom.myapp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/board")
    public String board() {
        return "/board.html";
    }

    @GetMapping("/register")
    public String register() {
        return "/register.html";
    }
}
