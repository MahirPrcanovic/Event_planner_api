package com.example.dogadjaji213.repository;

import com.example.dogadjaji213.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
}
