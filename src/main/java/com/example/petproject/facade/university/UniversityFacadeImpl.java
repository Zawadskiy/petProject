package com.example.petproject.facade.university;

import com.example.petproject.converter.UniversityConverter;
import com.example.petproject.dto.model.university.UniversityDto;
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
    public UniversityDto updateUniversity(UniversityDto universityDto) {

        University university = universityService.update(universityConverter.toUniversity(universityDto));

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityDto getUniversity(long id) {

        University university = universityService.findById(id);

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityDto createUniversity(UniversityDto universityDto) {

        University university = universityService.create(universityConverter.toUniversity(universityDto));

        return universityConverter.toUniversityDto(university);
    }

    @Override
    public UniversityDto deleteUniversity(long id) {

        universityService.deleteById(id);

        return new UniversityDto();
    }

    @Override
    public List<UniversityDto> getUniversities(int page, int size) {

        List<University> universities = universityService.getUniversities(page, size);

        return universities.stream().map(universityConverter::toUniversityDto).toList();
    }
}
