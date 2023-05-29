package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.model.University;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversityRequestToUniversity implements Converter<UniversityRequest, University> {
    @Override
    public University convert(UniversityRequest source) {
        University university = new University();

        university.setName(source.getName());
        university.setStudyDuration(source.getStudyDuration());

        return university;
    }

    @Override
    public List<University> convert(List<UniversityRequest> source) {
        return null;
    }
}
