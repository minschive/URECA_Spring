package com.mycom.myapp.service;

import com.mycom.myapp.entity.Student;
import com.mycom.myapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceFindImpl implements StudentServiceFind {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> findByEmailAndPhone(String email, String phone) {
        return studentRepository.findByEmailAndPhone(email, phone);
    }

    @Override
    public List<Student> findByEmailOrPhone(String email, String phone) {
        return studentRepository.findByEmailOrPhone(email, phone);
    }

    @Override
    public List<Student> findByNameLike(String name) {
//        return studentRepository.findByNameLike("%" + name);
        return studentRepository.findByNameLike("%" + name + "%");
//        return studentRepository.findByNameLike(name + "%");
    }
}
