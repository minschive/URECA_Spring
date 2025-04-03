package com.mycom.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycom.myapp.dto.EmpDto;
import com.mycom.myapp.service.EmpService;

@Controller
@ResponseBody
@RequestMapping("/emps")
public class EmpController {
    // emps.jsp 이동 => PageController
    // 사원관리 json 처리

    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    // emp-mapper.xml 대응
    // 목록
    @GetMapping("/list")
    public List<EmpDto> listEmp(){
        return empService.listEmp();
    }

    // 상세
    @GetMapping("/detail/{employeeId}")
    public EmpDto detailEmp(@PathVariable("employeeId") int employeeId){
        return empService.detailEmp(employeeId);
    }

    // 등록
    @PostMapping("/insert")
    public Map<String, String> empInsert(EmpDto empDto) {

        int ret = empService.insertEmp(empDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }

        return map;
    }

    // 수정
    @PostMapping("/update")
    public Map<String, String> empUpdate(EmpDto empDto) {

        int ret = empService.updateEmp(empDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }

        return map;
    }

    // 삭제
    @GetMapping("/delete/{employeeId}")
    public Map<String, String> deleteEmp(@PathVariable("employeeId") int employeeId){

        int ret = empService.deleteEmp(employeeId);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }

        return map;
    }

    // emp-mapper-2.xml 대응
    // postman 이용
    // Like
    @GetMapping("/listEmpLike")
    public List<EmpDto> listEmpLike(@RequestParam String searchWord){
        return empService.listEmpLike(searchWord);
    }

    // listEmpMap 에서 사용한 map 이 3개 컬러만 처리
    // 나머지 컬멍의 값 처리 X => EmpDto의 해당 필드는 null
    @GetMapping("/listEmpMap")
    public List<EmpDto> listEmpMap(){
        return empService.listEmpMap();
    }

    @GetMapping("/listEmpWhereIf")
    public List<EmpDto> listEmpWhereIf(@RequestParam Map<String, String> map){
        return empService.listEmpWhereIf(map);
    }
}
