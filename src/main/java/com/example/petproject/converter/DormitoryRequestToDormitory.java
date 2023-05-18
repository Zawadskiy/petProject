package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.University;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DormitoryRequestToDormitory implements Converter<DormitoryRequest, Dormitory> {

    private final UniversityService universityService;

    @Autowired
    public DormitoryRequestToDormitory(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public Dormitory convert(DormitoryRequest source) {

        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(source.getNumberOfRooms());
        dormitory.setNumber(source.getNumber());
        dormitory.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());

        University university = universityService.getUniversity(source.getUniversity());
        dormitory.setUniversity(university);

        return dormitory;
    }

    @Override
    public List<Dormitory> convert(List<DormitoryRequest> source) {
        return source.stream().map(this::convert).toList();
    }
}
