package com.mycom.springbootbasicjunit.basic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

// assertOOO 메소드를 통해서 판단 (같다, 다르다, null 이다, null 이 아니다 .....)
// assertOOO 메소드의 테스트가 싪패하면 세 번째 message 가 Failure Trace 맨 앞에 표시
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 에 필요
public class Test_Assert_1 {

    @Test
    @Order(1)
    void testAssertEquals() {
//        assertEquals(1, 2, "1 과 1이 같아야 합니다.");
        int legacyNum = LegacySystem.getNum();
        int testNum = TestSystem.getNum();
        assertEquals(legacyNum, testNum, "LegacySystem 과 TestSystem 비교");
    }

    @Test
    @Order(2)
    void testAssertNotEquals() {
        int legacyNum = 2;
        int testNum = 3;
        assertNotEquals(legacyNum, testNum, "LegacySystem 과 TestSystem 비교");
    }

    @Test
    @Order(3)
    void testAssertNotEqualsObject() {
        // MyClass 에 equals(), hashcode() 재정의 하지 않으면 실패, 재정의 후 성공
        MyClass mc = new MyClass();
        MyClass mc2 = new MyClass();
        assertEquals(mc, mc2, "mc 와 mc2 equals 비교");
    }

    @Test
    @Order(4)
    void testAssertTrue() {
        MyClass mc = new MyClass();
        assertTrue(mc.getResult(), "mc.getResult() 의 결과가 true 이어야 한다.");
    }

    // assertFalse 직접 작성
    @Test
    @Order(5)
    void testAssertFalse() {
        MyClass mc = new MyClass();
        assertFalse(mc.getResult2(), "mc.getResult2() 의 결과가 false 이어야 한다.");
    }

    @Test
    @Order(6)
    void testAssertNull() {
        MyClass mc = new MyClass();
        assertNull(mc.getString(), "mc.getString() 의 결과가 null 이어야 한다.");
    }

    // assertNotNUll 직접 작성
    @Test
    @Order(7)
    void testAssertNotNull() {
        MyClass mc = new MyClass();
        assertNotNull(mc.getString2(), "mc.getString() 의 결과가 String 이어야 한다.");
    }
}
