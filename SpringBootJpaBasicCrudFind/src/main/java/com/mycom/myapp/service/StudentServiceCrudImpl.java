package com.mycom.myapp.service;

import com.mycom.myapp.entity.Student;
import com.mycom.myapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceCrudImpl implements StudentServiceCrud {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> detailStudent(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student insertStudent(Student student) {
//        Student student2 = studentRepository.save(student);
        // 추가적인 영속화된 student2 로 Business Logic 처리 가능
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> updateStudent(Student student) { // return 타입을 Student 로 하는 경우도 있음
        // Student 타입으로 return 할 경우, Spring Data Jpa 에게 알아서 하라고 하는 방식
        // id 가 없으면 ?? -> insert 가 된다.
        // id 가 있으면 ?? -> update 가 된다.
        // => update 를 하라고 했는데 insert 가 되는 경우가 발생할 수 있음
//        return studentRepository.save(student);

        // Optional<Student> 타입으로 return 할 경우, 직접 id 를 검사 후 진행하는 방식
        Optional<Student> exstingStudent = studentRepository.findById(student.getId());
        if(exstingStudent.isPresent()) {
            return Optional.of(studentRepository.save(student));
        }
        return Optional.empty(); // null 과 동일한 개념
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public long countStudent() {
        return studentRepository.count();
    }

    @Override
    public List<Student> listStudent(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> page =  studentRepository.findAll(pageable);
        return page.toList();
    }
}
