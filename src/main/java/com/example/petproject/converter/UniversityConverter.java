package com.example.petproject.converter;

import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.domain.University;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UniversityConverter implements Converter<University, UniversityResponse> {

    @Override
    public UniversityResponse convert(University source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<UniversityResponse> convert(List<University> source) {
        return source.stream()
                .map(this::mapToUniversityResponse)
                .toList();
    }

    private UniversityResponse mapToUniversityResponse(University university) {

        UniversityResponse universityDto = new UniversityResponse();

        universityDto.setId(university.getId());
        universityDto.setName(university.getName());
        universityDto.setStudyDuration(university.getStudyDuration());

        return universityDto;
    }
}
