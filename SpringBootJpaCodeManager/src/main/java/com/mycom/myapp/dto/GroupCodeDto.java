package com.mycom.myapp.dto;

import com.mycom.myapp.entity.GroupCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GroupCodeDto {

    private String groupCode;
    private String groupCodeName;
    private String groupCodeDesc;

    // service layer 등에서 GroupCode -> GroupCodeDto 로 변환할 때 여러 곳에 사용될 builder 코드를 이곳에 하나로 만들어서 사용
    public static GroupCodeDto fromGroupCode(GroupCode groupCode) {
        return GroupCodeDto.builder()
                .groupCode(groupCode.getGroupCode())
                .groupCodeName(groupCode.getGroupCodeName())
                .groupCodeDesc(groupCode.getGroupCodeDesc())
                .build();
    }
}
