package com.example.petproject.converter;

import com.example.petproject.dto.model.university.UniversityDto;
import com.example.petproject.model.University;
import org.springframework.stereotype.Component;

@Component
public class UniversityConverter {
    
    public University toUniversity(UniversityDto universityDto) {
        University university = new University();

        university.setName(universityDto.getName());
        university.setStudyDuration(universityDto.getStudyDuration());

        return university;
    }

    public UniversityDto toUniversityDto(University university) {
        UniversityDto universityDto = new UniversityDto();

        universityDto.setName(university.getName());
        universityDto.setStudyDuration(university.getStudyDuration());

        return universityDto;
    }
}
