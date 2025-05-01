package com.mycom.springbootbasiclogging.service;

import com.mycom.springbootbasiclogging.dto.StudentDto;
import com.mycom.springbootbasiclogging.dto.StudentResultDto;

public interface StudentServiceCrud {

    StudentResultDto listStudent();
    StudentResultDto detailStudent(int id);

    StudentResultDto insertStudent(StudentDto studentDto); // jpa 를 통해서 table 에 insert 하고 영속화 시킨 객체를 return
    StudentResultDto updateStudent(StudentDto studentDto);
    StudentResultDto deleteStudent(int id); // delete 는 return 이 void

    StudentResultDto countStudent();
    StudentResultDto listStudent(int pageNumber, int pageSize);
}
