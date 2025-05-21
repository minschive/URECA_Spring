package com.mycom.myapp.service;

import com.mycom.myapp.dto.CommonCodeResultDto;

import java.util.List;

public interface CommonCodeService {
    CommonCodeResultDto getCommonCodeList(List<String> groupCodes);
}
