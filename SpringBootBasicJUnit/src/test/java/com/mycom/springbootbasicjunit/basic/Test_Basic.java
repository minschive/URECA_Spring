package com.mycom.springbootbasicjunit.basic;

import org.junit.jupiter.api.*;

// 테스트하려는 내용을 메소드로 만들고 메소드에 @Test 추가
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 에 필요
public class Test_Basic {

    @Test
    @Order(5)
    void test1() {
        System.out.println("test1()");
    }

    @Test
    @Order(3)
    @DisplayName("test2 메소드를 테스트 합니다.")
    void test2() {
        System.out.println("test2()");
    }

    @Test
    @Order(1)
    void test3() {
        System.out.println("test3()");
    }

    @Test
    @Order(2)
    @DisplayName("RuntimeException 예외 발생")
    void test4() {
        System.out.println("test4()");
        String str = null;
        str.length();
    }

    // @BeforeAll, @AfterAll 는 static
    // 테스트 전 static 으로 호출되고, Test_Basic 객체 생성 후 호출되는 UI
    // 전체 테스트 전 리소스 확인, 테스트 데이터 생성 ...
    @BeforeAll
    @DisplayName("전체 테스트 메소드 수행 전 한번 실행")
    static void beforeAll() {
        System.out.println("before()");
    }

    // 전체 테스트 전 리소스 종료, 테스트 데이터 생성 ...
    @AfterAll
    static void afterAll() {
        System.out.println("after()");
    }

    @BeforeEach
    @DisplayName("개별 테스트 메소드 수행 전 매번 실행")
    void beforeEach() {
        System.out.println("beforeEach()");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach()");
    }
}
