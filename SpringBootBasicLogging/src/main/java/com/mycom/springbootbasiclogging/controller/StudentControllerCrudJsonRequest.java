package com.mycom.springbootbasiclogging.controller;

import com.mycom.springbootbasiclogging.dto.StudentDto;
import com.mycom.springbootbasiclogging.dto.StudentResultDto;
import com.mycom.springbootbasiclogging.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// REST 를 적용하면 /api/v1
// get list : /students
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json")
public class StudentControllerCrudJsonRequest {

    private final StudentServiceCrud studentServiceCrud;

    @GetMapping("/students")
    public StudentResultDto listStudent() {
        return studentServiceCrud.listStudent();
    }

    @GetMapping("/students/{id}")
    public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
        return studentServiceCrud.detailStudent(id);
    }

    // 등록, 수정에 사용되는 StudentDto 를 Client 에서 JSON 으로 보낸다.
    @PostMapping("/students")
    public StudentResultDto insertStudent(StudentDto studentDto) {
        return studentServiceCrud.insertStudent(studentDto);
    }

    @PutMapping("/students/{id}")
    public StudentResultDto updateStudent(@PathVariable("id") Integer id, StudentDto studentDto) {
        studentDto.setId(id);
        return studentServiceCrud.updateStudent(studentDto);
    }

    @DeleteMapping("/students/{id}")
    public  StudentResultDto deleteStudent(@PathVariable("id") Integer id) {
        return studentServiceCrud.deleteStudent(id);
    }

    @GetMapping("/students/count")
    public StudentResultDto countStudent() {
        return studentServiceCrud.countStudent();
    }

    @GetMapping("/students/page")
    public StudentResultDto listStudent(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        return studentServiceCrud.listStudent(pageNumber, pageSize);
    }
}
