package com.mycom.myapp.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Spring 을 통해서 WAS(Tomcat) 에 filter 등록
// @Component + implements Filter
//@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter >>> Before Filter Job");
        // filter 처리 ( controller 이전 작업 )
        // 작업 결과에 따라 통과, 거절 처리
        // 현재 이 filter 는 무조건 통과하도록

        chain.doFilter(request, response);

        System.out.println("MyFilter >>> After Filter Job");
    }
}
