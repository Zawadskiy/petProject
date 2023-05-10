package com.example.petproject.converter;

import com.example.petproject.dto.model.dormitory.DormitoryDto;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.University;
import com.example.petproject.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class DormitoryConverter {

    private final UniversityService universityService;

    public DormitoryConverter(UniversityService universityService) {
        this.universityService = universityService;
    }

    public Dormitory toDormitory(DormitoryDto dormitoryDto) {
        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(dormitoryDto.getNumberOfRooms());
        dormitory.setNumber(dormitory.getNumber());
        dormitory.setAvailabilityForAccommodation(dormitoryDto.isAvailabilityForAccommodation());

        University university = universityService.findByName(dormitoryDto.getUniversity());
        dormitory.setUniversity(university);

        return dormitory;
    }

    public DormitoryDto toDormitoryDto(Dormitory dormitory) {
        DormitoryDto dormitoryDto = new DormitoryDto();

        if (dormitory.getUniversity() != null) {
            dormitoryDto.setUniversity(dormitory.getUniversity().getName());
        }

        dormitoryDto.setNumber(dormitory.getNumber());
        dormitoryDto.setNumberOfRooms(dormitory.getNumberOfRooms());
        dormitoryDto.setAvailabilityForAccommodation(dormitory.isAvailabilityForAccommodation());

        return dormitoryDto;
    }
}
