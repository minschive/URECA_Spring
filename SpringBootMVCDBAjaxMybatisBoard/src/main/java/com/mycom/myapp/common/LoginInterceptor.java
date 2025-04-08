package com.mycom.myapp.common;

import com.mycom.myapp.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final String jsonStr = "{\"result\":\"login\"}";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor >>>>> " + request.getRequestURI()); // 인터셉터가 항상 동작한다. (권장)

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if(userDto == null) { // 로그인하지 않은 사용자일 때
            // ajax 요청
            // client 가 header 에 "ajax":"true" <= board.jsp
            // {"result":"login"} json 문자열 응답
            // (응답 받으면) client 의 javascript 에서 window.location.href 이용해서 페이지 이동
            if( "true".equals(request.getHeader("ajax")) ) {
                System.out.println("LoginInterceptor >>>>> ajax request ");
                response.getWriter().write(jsonStr);

                // page 요청
                // => login 페이지로 이동 (redirection)
            } else {
                System.out.println("LoginInterceptor >>>>> page request ");
                response.sendRedirect("/pages/login");
            }

            return false;
        }
        return true;
    }
}
