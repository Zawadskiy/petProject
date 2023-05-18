package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.model.University;
import org.springframework.stereotype.Component;

@Component
public class UniversityConverter {

    public University toUniversity(UniversityRequest universityDto) {

        University university = new University();

        university.setName(universityDto.getName());
        university.setStudyDuration(universityDto.getStudyDuration());

        return university;
    }

    public UniversityResponse toUniversityDto(University university) {

        UniversityResponse universityDto = new UniversityResponse();

        universityDto.setId(university.getId());
        universityDto.setName(university.getName());
        universityDto.setStudyDuration(university.getStudyDuration());

        return universityDto;
    }
}
