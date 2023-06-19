package com.example.petproject.service.university;

import com.example.petproject.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UniversityService {

    University getUniversity(long id);

    University update(University university);

    University create(University university);

    void delete(long id);

    Page<University> getUniversities(Pageable pageRequest);
}
