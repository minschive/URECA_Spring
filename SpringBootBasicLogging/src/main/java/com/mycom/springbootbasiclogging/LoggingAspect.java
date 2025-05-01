package com.mycom.springbootbasiclogging;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// com.mycom.springbootbasiclogging.repository 의 모든 메소드가 호출될 때 기본 로그를 남기는 Aspect
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    // Session
    private final HttpSession session;

    @Pointcut(value = "execution( * com.mycom.springbootbasiclogging.repository.*.*(..) )")
    private void logPointcut() {}

    // 로그인 한 사용자가 호출할 경우, 호출한 사용자의 이름과 시각을 출력
    @Before("logPointcut()")
    public void logRepositoryMethodCall(JoinPoint joinPoint) { // joinPoint 는 위 Pointcut 의 대상 중 실제로 호출된 메서드 진입점
        String username = (String) session.getAttribute("username");
        if(username == null) return;

        String methodName = joinPoint.getSignature().getName(); // 메서드 명
        log.info("User [" + username + "] 가 method : " + methodName + " 을 " + LocalDateTime.now() + " 에 호출했습니다.");
    }
}
