package com.example.petproject.facade.university;

import com.example.petproject.dto.model.university.UniversityDto;

import java.util.List;

public interface UniversityFacade {
    UniversityDto updateUniversity(UniversityDto request);
    UniversityDto getUniversity(long id);
    UniversityDto createUniversity(UniversityDto request);
    UniversityDto deleteUniversity(long id);
    List<UniversityDto> getUniversities(int page, int size);
}
