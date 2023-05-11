package com.example.petproject.repository;

import com.example.petproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUniversityName(String name);

    List<Student> findByDormitoryNumber(String number);

    Optional<Student> findByName(String name);
}
