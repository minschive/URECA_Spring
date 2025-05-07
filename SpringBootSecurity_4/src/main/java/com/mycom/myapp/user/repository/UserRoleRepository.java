package com.mycom.myapp.user.repository;

import com.mycom.myapp.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByName(String name);
}
