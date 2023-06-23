package com.example.petproject.repository;

import com.example.petproject.domain.User;

import java.util.Optional;

public interface UserRepository extends CustomRepo<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
