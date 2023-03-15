package com.example.petproject.repository;

import com.example.petproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO переписати на jdbc
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
}
