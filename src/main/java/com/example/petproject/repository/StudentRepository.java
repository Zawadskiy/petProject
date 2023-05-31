package com.example.petproject.repository;

import com.example.petproject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUniversityId(long id);

    List<Student> findAllByDormitoryId(long id);
}
