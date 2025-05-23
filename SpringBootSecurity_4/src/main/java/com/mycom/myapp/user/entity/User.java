package com.mycom.myapp.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    // User 를 가져올 때, 항상 UserRole 함께 가져와라.
    @OneToMany(fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // UserServiceImple #5
    @ToString.Exclude
    private List<UserRole> userRoles;
}
