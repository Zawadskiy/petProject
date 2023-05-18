package com.example.petproject.facade.university;

import com.example.petproject.converter.UniversityConverter;
import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.model.University;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversityFacadeImpl implements UniversityFacade {

    private final UniversityService universityService;
    private final UniversityConverter universityConverter;

    @Autowired
    public UniversityFacadeImpl(UniversityService universityService, UniversityConverter universityConverter) {
        this.universityService = universityService;
        this.universityConverter = universityConverter;
    }

    @Override
    public UniversityResponse updateUniversity(UniversityRequest universityDto, long id) {

        University university = universityConverter.toUniversity(universityDto);
        university.setId(id);
        universityService.update(university);

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityResponse getUniversity(long id) {

        University university = universityService.getUniversity(id);

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityResponse createUniversity(UniversityRequest universityDto) {

        University university = universityService.create(universityConverter.toUniversity(universityDto));

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityResponse deleteUniversity(long id) {

        universityService.delete(id);

        return new UniversityResponse();
    }

    @Override
    public List<UniversityResponse> getUniversities(int page, int size) {

        List<University> universities = universityService.getUniversities(page, size);

        return universities.stream().map(universityConverter::toUniversityDto).toList();
    }
}
