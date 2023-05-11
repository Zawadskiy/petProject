package com.example.petproject.service.university;

import com.example.petproject.model.University;

import java.util.List;

public interface UniversityService {
    University findByName(String name);

    List<University> findAll();

    University update(University university);

    University findById(long id);

    University create(University university);

    void deleteById(long id);

    List<University> getUniversities(int page, int size);
}
