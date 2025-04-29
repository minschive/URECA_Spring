package com.mycom.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Controller 의 파라미터로 사용되는 Dto 는 기본 생성자 + 전체 생성자를 포함해야 한다.
@Data // RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private int id;
    private String name;
    private String email;
    private String phone;
}
