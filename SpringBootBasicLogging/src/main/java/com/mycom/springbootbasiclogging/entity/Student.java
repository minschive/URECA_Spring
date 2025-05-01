package com.mycom.springbootbasiclogging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// insert 는 생성자 이슈 X
// update 는 기본 생성자, 전체 생성자가 필요
// update 의 save 는 select 한 후, update 수행하는 이중구조이므로
// select 한 결과를 Entity 로 setting 할 때 필요
@Data
@Entity
@Table(name="student")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;

}
