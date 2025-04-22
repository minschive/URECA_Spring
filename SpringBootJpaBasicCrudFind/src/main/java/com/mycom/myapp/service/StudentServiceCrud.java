package com.mycom.myapp.service;

import com.mycom.myapp.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceCrud {

    List<Student> listStudent();
    Optional<Student> detailStudent(int id);

    Student insertStudent(Student student); // jpa 를 통해서 table 에 insert 하고 영속화 시킨 객체를 return
    Optional<Student> updateStudent(Student student);
    void deleteStudent(int id); // delete 는 return 이 void

    long countStudent();
    List<Student> listStudent(int pageNumber, int pageSize);
}
