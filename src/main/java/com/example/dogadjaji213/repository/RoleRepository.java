package com.example.dogadjaji213.repository;

import com.example.dogadjaji213.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
