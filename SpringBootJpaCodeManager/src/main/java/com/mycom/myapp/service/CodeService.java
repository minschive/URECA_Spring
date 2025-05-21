package com.mycom.myapp.service;

import com.mycom.myapp.dto.CodeResultDto;
import com.mycom.myapp.entity.Code;
import com.mycom.myapp.entity.key.CodeKey;

public interface CodeService {
    CodeResultDto insertCode(Code code);
    CodeResultDto updateCode(Code code);
    CodeResultDto deleteCode(CodeKey codeKey);

    CodeResultDto listCode(String groupCode, int pageNumber, int pageSize);
    CodeResultDto detailCode(CodeKey codeKey);
    CodeResultDto countCode();
}
