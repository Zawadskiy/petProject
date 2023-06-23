package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.domain.University;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UniversityRequestConverter implements Converter<UniversityRequest, University> {

    @Override
    public University convert(UniversityRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<University> convert(List<UniversityRequest> source) {
        return source.stream()
                .map(this::mapToUniversity)
                .toList();
    }

    private University mapToUniversity(UniversityRequest universityRequest) {

        University university = new University();

        university.setName(universityRequest.getName());
        university.setStudyDuration(universityRequest.getStudyDuration());

        return university;
    }
}
