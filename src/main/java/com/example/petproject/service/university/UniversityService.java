package com.example.petproject.service.university;

import com.example.petproject.domain.University;

import java.util.List;

public interface UniversityService {
    University getUniversity(String name);

    University getUniversity(long id);

    University update(University university);

    University create(University university);

    void delete(long id);

    List<University> getUniversities(int page, int size);

    List<University> getAll();
}
