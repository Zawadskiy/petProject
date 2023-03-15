package com.example.petproject.repository;

import com.example.petproject.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
