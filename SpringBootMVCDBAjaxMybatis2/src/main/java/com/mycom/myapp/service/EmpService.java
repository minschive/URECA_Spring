package com.mycom.myapp.service;

import com.mycom.myapp.dto.EmpDto;

import java.util.List;
import java.util.Map;

public interface EmpService {
    // emp-mapper.xml 대응
    List<EmpDto> listEmp();
    EmpDto detailEmp(int employeeId);
    int insertEmp(EmpDto empDto);
    int updateEmp(EmpDto empDto);
    int deleteEmp(int employeeId);

    // emp-mapper-2.xml 대응
    List<EmpDto> listEmpLike(String searchWord);
    List<EmpDto> listEmpMap();
    List<EmpDto> listEmpWhereIf(Map<String, String> map);
}
