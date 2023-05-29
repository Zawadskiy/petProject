package com.example.petproject.converter;

import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.model.University;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversityToUniversityResponse implements Converter<University, UniversityResponse> {

    @Override
    public UniversityResponse convert(University source) {

        UniversityResponse universityDto = new UniversityResponse();

        universityDto.setId(source.getId());
        universityDto.setName(source.getName());
        universityDto.setStudyDuration(source.getStudyDuration());

        return universityDto;
    }

    @Override
    public List<UniversityResponse> convert(List<University> source) {
        return null;
    }
}
