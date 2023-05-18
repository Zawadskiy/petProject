package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.response.DormitoryResponse;
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

    public Dormitory toDormitory(DormitoryRequest dormitoryDto) {

        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(dormitoryDto.getNumberOfRooms());
        dormitory.setNumber(dormitoryDto.getNumber());
        dormitory.setAvailabilityForAccommodation(dormitoryDto.isAvailabilityForAccommodation());

        University university = universityService.getUniversity(dormitoryDto.getUniversity());
        dormitory.setUniversity(university);

        return dormitory;
    }

    // TODO: 16.05.2023 В целом, вкусовщина. Но я бы сделал интерфейс конвертера
    //  (или заюзал существующие) с одним методом convert()
    //  перегруженным для одиночных сущностей и коллекций.
    //  Последнее полезно, чтобы оптимизировать запросы в бд для получения связей
    public DormitoryResponse toDormitoryDto(Dormitory dormitory) {

        DormitoryResponse dormitoryDto = new DormitoryResponse();

        dormitoryDto.setId(dormitory.getId());

        if (dormitory.getUniversity() != null) {
            dormitoryDto.setUniversity(dormitory.getUniversity().getId());
        }

        dormitoryDto.setNumber(dormitory.getNumber());
        dormitoryDto.setNumberOfRooms(dormitory.getNumberOfRooms());
        dormitoryDto.setAvailabilityForAccommodation(dormitory.isAvailabilityForAccommodation());

        return dormitoryDto;
    }
}
