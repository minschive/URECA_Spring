package com.mycom.springbootbasiclogging.controller;

import com.mycom.springbootbasiclogging.dto.StudentDto;
import com.mycom.springbootbasiclogging.dto.StudentResultDto;
import com.mycom.springbootbasiclogging.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// REST 를 적용하면 /api/v1
// get list : /students
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class StudentControllerCrud {

    private final StudentServiceCrud studentServiceCrud;

    @GetMapping("/students")
    public StudentResultDto listStudent() {

        log.debug("listStudent() debug");
        log.info("listStudent() info");
        log.warn("listStudent() warn");

        return studentServiceCrud.listStudent();
    }

//    @Operation(summary = "학생 상세 조회", description = "개별 학생을 조회합니다.", deprecated = true)
    @GetMapping("/students/{id}")
    public StudentResultDto detailStudent(@PathVariable("id") Integer id) {
        return studentServiceCrud.detailStudent(id);
    }

//    @Operation(summary = "학생 등록", description = "신규 학생을 등록합니다.", hidden = true)
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
