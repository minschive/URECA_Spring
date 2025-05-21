package com.mycom.myapp.service;

import com.mycom.myapp.dto.CodeResultDto;
import com.mycom.myapp.entity.GroupCode;

public interface GroupCodeService {
    CodeResultDto insertGroupCode(GroupCode groupCode);
    CodeResultDto updateGroupCode(GroupCode groupCode);
    CodeResultDto deleteGroupCode(String groupCode);

    CodeResultDto listGroupCode(int pageNumber, int pageSize);
    CodeResultDto detailGroupCode(String groupCode);
    CodeResultDto countGroupCode();
}
