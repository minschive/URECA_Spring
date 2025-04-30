package com.mycom.springbootbasicjunit.basic;

import java.util.Objects;

public class MyClass {
    int n = 10;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return n == myClass.n;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(n);
    }

    // assertTrue
    public boolean getResult() {
        return true;
    }

    // assertFalse
    public boolean getResult2() {
        return false;
    }

    // assertNull
    public String getString() {
        return null;
    }

    // assertNotNull
    public String getString2() {
        return "hello";
    }

    public int getStringLength(String str) {
        return str.length();
    }
}
