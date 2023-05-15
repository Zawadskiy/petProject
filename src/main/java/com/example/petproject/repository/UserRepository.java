package com.example.petproject.repository;

import com.example.petproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);// TODO: 16.05.2023 Местами форматирование кода плывет. Пустая строка?
    Boolean existsByUsername(String username);
}
