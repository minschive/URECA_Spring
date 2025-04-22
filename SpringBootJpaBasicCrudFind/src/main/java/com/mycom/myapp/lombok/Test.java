package com.mycom.myapp.lombok;

public class Test {

    public static void main(String[] args) {
        // Builder 패턴 적용
        EmpDto2 empDto2 = EmpDto2.builder()
                                 .employeeId(1)
                                 .firstName("길동")
                                 .lastName("홍")
                                 .email("hong@gildong.com")
                                 .hireDate("2025-04-22")
                                 .departmentId("123")
                                 .build();

        System.out.println(empDto2);

        empDto2.getEmployeeId();
    }

}

// Emp <- Entity, EmpDto 을 Emp 와 별개로 만들어야 하는가 ????
// Entity 와 별개의 Entity 를 표현하는 Dto 를 만드는 게 일반적
