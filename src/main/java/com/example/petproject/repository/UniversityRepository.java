package com.example.petproject.repository;

import com.example.petproject.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends CustomRepo<University, Long> {
}
