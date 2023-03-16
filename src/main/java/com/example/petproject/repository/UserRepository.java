package com.example.petproject.repository;

import com.example.petproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//TODO переписати на jdbc
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
