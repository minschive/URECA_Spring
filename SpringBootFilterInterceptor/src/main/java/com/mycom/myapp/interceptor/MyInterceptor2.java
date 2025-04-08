package com.mycom.myapp.interceptor;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor2 implements HandlerInterceptor {

    // return boolean : 더 진행할지 말지
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor2 >>> preHandle " + request.getRequestURI());

        // session 에서 "login" 확인을 통해서 return true 혹은 return false 처리
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println("login : " + login);
        if("success".equals(login)) {
            return true;
        }

        response.getWriter().write("need login");
        return false;
    }

    // return void : 이미 갔다 온 것이므로
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor2 >>> postHandle" + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println("MyInterceptor2 >>> afterCompletion Interceptor Job");
    }
}
