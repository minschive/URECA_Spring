package com.mycom.myapp.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.myapp.dto.CodeResultDto;
import com.mycom.myapp.entity.Code;
import com.mycom.myapp.entity.key.CodeKey;
import com.mycom.myapp.service.CodeService;
import lombok.RequiredArgsConstructor;

/**
 * 공통 코드를 관리하는 REST 컨트롤러입니다.
 * 코드의 조회, 등록, 수정, 삭제 및 개수 조회 기능을 제공합니다.
 *
 * @author bbiddagi
 * @since 2025.05.26
 */
//@RestController
@RequiredArgsConstructor
public class CodeControllerJavaDoc {

    private final CodeService codeService;

    /**
     * 지정된 그룹 코드에 대한 페이징된 코드 목록을 조회합니다.
     *
     * @param groupCode  그룹 코드
     * @param pageNumber 페이지 번호
     * @param pageSize   페이지 당 항목 수
     * @return 코드 목록 결과 DTO
     */
    @GetMapping("/codes")
    public CodeResultDto listCode(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize
    ) {
        return codeService.listCode(groupCode, pageNumber, pageSize);
    }

    /**
     * 특정 코드의 상세 정보를 조회합니다.
     *
     * @param groupCode 그룹 코드
     * @param code      코드 값
     * @return 코드 상세 정보 DTO
     */
    @GetMapping("/codes/{groupCode}/{code}")
    public CodeResultDto detailCode(@PathVariable("groupCode") String groupCode, @PathVariable("code") String code) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        return codeService.detailCode(codeKey);
    }

    /**
     * 새로운 코드를 등록합니다.
     *
     * @param groupCode      그룹 코드 (필수)
     * @param code           코드 값 (필수)
     * @param codeName       코드 이름
     * @param codeNameBrief  코드 약칭
     * @param orderNo        정렬 순서
     * @return 등록 결과 DTO
     */
    @PostMapping("/codes")
    public CodeResultDto insertCode(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("code") String code,
            @RequestParam("codeName") String codeName,
            @RequestParam("codeNameBrief") String codeNameBrief,
            @RequestParam("orderNo") String orderNo
    ) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        Code codeEntity = new Code();
        codeEntity.setCodeKey(codeKey);
        codeEntity.setCodeName(codeName);
        codeEntity.setCodeNameBrief(codeNameBrief);
        codeEntity.setOrderNo(orderNo);
        return codeService.insertCode(codeEntity);
    }

    /**
     * 기존 코드를 수정합니다.
     *
     * @param groupCode 그룹 코드 (필수)
     * @param code      코드 값 (필수)
     * @param codeParam 수정할 코드 객체
     * @return 수정 결과 DTO
     */
    @PutMapping("/codes")
    public CodeResultDto updateCode(
            @RequestParam("groupCode") String groupCode,
            @RequestParam("code") String code,
            Code codeParam
    ) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        codeParam.setCodeKey(codeKey);
        return codeService.updateCode(codeParam);
    }

    /**
     * 지정된 그룹 코드와 코드 값을 가진 코드를 삭제합니다.
     *
     * @param groupCode 그룹 코드
     * @param code      코드 값
     * @return 삭제 결과 DTO
     */
    @DeleteMapping("/codes/{groupCode}/{code}")
    public CodeResultDto deleteCode(@PathVariable("groupCode") String groupCode, @PathVariable("code") String code) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        return codeService.deleteCode(codeKey);
    }

    /**
     * 전체 코드의 개수를 조회합니다.
     *
     * @return 코드 수 조회 결과 DTO
     */
    @GetMapping("/codes/count")
    public CodeResultDto countCode() {
        return codeService.countCode();
    }
}
