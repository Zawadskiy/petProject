package com.example.petproject.converter;

import com.example.petproject.dto.model.dormitory.DormitoryDto;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.University;
import com.example.petproject.service.university.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class DormitoryConverter {

    private final UniversityService universityService;

    public DormitoryConverter(UniversityService universityService) {
        this.universityService = universityService;
    }

    public Dormitory toDormitory(DormitoryDto dormitoryDto) {
        Dormitory dormitory = new Dormitory();

        dormitory.setId(dormitoryDto.getId());
        dormitory.setNumberOfRooms(dormitoryDto.getNumberOfRooms());
        dormitory.setNumber(dormitory.getNumber());
        dormitory.setAvailabilityForAccommodation(dormitoryDto.isAvailabilityForAccommodation());

        University university = universityService.findByName(dormitoryDto.getUniversity());
        dormitory.setUniversity(university);

        return dormitory;
    }

    // TODO: 16.05.2023 В целом, вкусовщина. Но я бы сделал интерфейс конвертера
    //  (или заюзал существующие) с одним методом convert()
    //  перегруженным для одиночных сущностей и коллекций.
    //  Последнее полезно, чтобы оптимизировать запросы в бд для получения связей
    public DormitoryDto toDormitoryDto(Dormitory dormitory) {
        DormitoryDto dormitoryDto = new DormitoryDto();

        dormitoryDto.setId(dormitory.getId());
        // TODO: 16.05.2023 зачем имя? логичнее id
        if (dormitory.getUniversity() != null) {
            dormitoryDto.setUniversity(dormitory.getUniversity().getName());
        }

        dormitoryDto.setNumber(dormitory.getNumber());
        dormitoryDto.setNumberOfRooms(dormitory.getNumberOfRooms());
        dormitoryDto.setAvailabilityForAccommodation(dormitory.isAvailabilityForAccommodation());

        return dormitoryDto;
    }
}
