package com.mycom.myapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Spring 을 통해서 WAS(Tomcat) 에 filter 등록
// @Component + implements Filter
// /xyx 의 요청은 거부 403 Forbidden 응답코드를 내려주도록
//@Component
public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter2 >>> Before Filter Job");
        // 요청 url check
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if( httpRequest.getRequestURI().contains("/xyz") ) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 금지된 url 입니다.");
            return; // doFilter()로 이동하지 않도록
        }

        chain.doFilter(request, response);

        System.out.println("MyFilter2 >>> After Filter Job");
    }
}
