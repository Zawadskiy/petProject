package com.example.petproject.repository;

import com.example.petproject.domain.Role;

import java.util.Optional;

public interface RoleRepository extends CustomRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
