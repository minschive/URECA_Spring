package com.mycom.myapp.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CommonCodeResultDto {
    private String result;
    private Map<String, List<CodeDto>> commonCodeDtoListMap;
}

