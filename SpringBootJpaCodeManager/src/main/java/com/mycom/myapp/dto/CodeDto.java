package com.mycom.myapp.dto;

import com.mycom.myapp.entity.Code;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CodeDto {

    private String groupCode;
    private String code;
    private String codeName;
    private String codeNameBrief;
    private String orderNo;

    public static CodeDto fromCode(Code code) {
        return CodeDto.builder()
                .groupCode(code.getCodeKey().getGroupCode())
                .code(code.getCodeKey().getCode())
                .codeName(code.getCodeName())
                .codeNameBrief(code.getCodeNameBrief())
                .orderNo(code.getOrderNo())
                .build();
    }
}
