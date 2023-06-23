package com.example.petproject.repository;

import com.example.petproject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CustomRepo<Student, Long> {
}
