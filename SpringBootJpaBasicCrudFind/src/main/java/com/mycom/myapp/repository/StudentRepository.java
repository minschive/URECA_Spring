package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repository 역할을 담당하는 구현 코드를 Spring Data JPA 가 대신 생성, 수행
// extends JpaRepository 코드에 의해 대응되는 Entity 에 대한 기본 crud 및 기타 몇 가지 메소드가 자동으로 생성
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 간단한 crud 및 count, paging 등은 아무런 메소드 선언조차 필요 없다.

    // Find...
    // sql : 다양한 select
    // 표현은 잘 하면 자동으로 jpql 을 만들어 줄게
    List<Student> findByName(String name);
    List<Student> findByEmailAndPhone(String email, String phone);
    List<Student> findByEmailOrPhone(String email, String phone);

    List<Student> findByNameLike(String name);
}
