package com.example.petproject.converter;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.University;
import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DormitoryRequestConverter implements ConverterEx<DormitoryRequest, Dormitory> {

    private final UniversityService universityService;

    @Autowired
    public DormitoryRequestConverter(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public Dormitory convert(DormitoryRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public Dormitory convert(DormitoryRequest source, long id) {

        Dormitory dormitory = convert(source);
        dormitory.setId(id);

        return dormitory;
    }

    @Override
    public List<Dormitory> convert(List<DormitoryRequest> source) {

        List<Long> universities = source.stream()
                .map(DormitoryRequest::getUniversity)
                .distinct()
                .toList();
        Map<Long, University> universityMap = universityService.getAllIn(universities)
                .stream()
                .collect(Collectors.toMap(University::getId, Function.identity()));

        return source.stream()
                .map(request -> mapToDormitory(request, universityMap.get(request.getUniversity())))
                .toList();
    }

    private Dormitory mapToDormitory(DormitoryRequest source, University university) {

        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(source.getNumberOfRooms());
        dormitory.setNumber(source.getNumber());
        dormitory.setAccommodationAvailability(source.isAvailabilityForAccommodation());
        dormitory.setUniversity(university);

        return dormitory;
    }
}
