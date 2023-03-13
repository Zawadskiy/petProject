package com.example.petproject.repository;

import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
