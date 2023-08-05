package com.example.petproject.repository;

import com.example.petproject.domain.Student;
import com.example.petproject.domain.University;

import java.util.List;

public interface StudentRepository extends CustomRepository<Student, Long> {
    List<Student> findAllByIdIn(List<Long> id);
}
