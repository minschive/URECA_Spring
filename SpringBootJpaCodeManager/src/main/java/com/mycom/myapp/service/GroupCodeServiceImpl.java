package com.mycom.myapp.service;

import com.mycom.myapp.dto.CodeResultDto;
import com.mycom.myapp.dto.GroupCodeDto;
import com.mycom.myapp.entity.GroupCode;
import com.mycom.myapp.repository.GroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupCodeServiceImpl implements GroupCodeService {

    private final GroupCodeRepository groupCodeRepository;

    @Override
    public CodeResultDto insertGroupCode(GroupCode groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try {
            // isNew true
            groupCode.setNew(true);
            groupCodeRepository.save(groupCode);
            codeResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto updateGroupCode(GroupCode groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try {
            // isNew false
            groupCode.setNew(false);
            groupCodeRepository.save(groupCode);
            codeResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto deleteGroupCode(String groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try {
            groupCodeRepository.deleteById(groupCode);
            codeResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto listGroupCode(int pageNumber, int pageSize) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<GroupCode> page = groupCodeRepository.findAll(pageable);
            List<GroupCodeDto> groupCodeDtoList = new ArrayList<>();

            // Page<GroupCode> -> List<GroupCodeDto>
            page.toList().forEach( groupCode -> groupCodeDtoList.add( GroupCodeDto.fromGroupCode(groupCode) ) );
            codeResultDto.setGroupCodeDtoList(groupCodeDtoList);

            // count
            Long count = groupCodeRepository.count();
            codeResultDto.setCount(count);

            codeResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto detailGroupCode(String groupCode) {
        CodeResultDto codeResultDto = new CodeResultDto();
        try {
            Optional<GroupCode> optionalGroupCode = groupCodeRepository.findById(groupCode);

            optionalGroupCode.ifPresentOrElse(
                    detailGroupCode -> {
                        codeResultDto.setGroupCodeDto( GroupCodeDto.fromGroupCode(detailGroupCode) );
                        codeResultDto.setResult("success");
                    },
                    () -> codeResultDto.setResult("fail")
            );
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }
        return codeResultDto;
    }

    @Override
    public CodeResultDto countGroupCode() {
        CodeResultDto codeResultDto = new CodeResultDto();

        try {
            Long count = groupCodeRepository.count();
            codeResultDto.setCount(count);
            codeResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            codeResultDto.setResult("fail");
        }

        return codeResultDto;
    }
}
