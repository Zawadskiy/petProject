package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.University;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
// TODO: 22.06.2023 не советую терять постиксы. Зато некоторые вещи можно упростить:
//  DormitoryRequestConverter - если мы всегда только в одну сущность конвертим, зачем усложнять, пока нет причины?
public class DormitoryRequestToDormitory implements Converter<DormitoryRequest, Dormitory> {

    private final UniversityService universityService;

    @Autowired
    public DormitoryRequestToDormitory(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public Dormitory convert(DormitoryRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    // TODO: 22.06.2023 т.е. для каждой общаги ты делаешь запрос н аунивер в бд. Смысл?
    //  Можно по списку общаг получить мапу айди универа-универ и дальше раскидать из нее.
    //  Получится константное число запросов, а не как сейчас.
    //  Отдельный вопрос - нужно ли сетать объект на уровне конвертера из реквеста.
    //  Мб имеет смысл делать на уровне сервиса
    public List<Dormitory> convert(List<DormitoryRequest> source) {
        return source.stream()
                .map(this::mapToDormitory)
                .toList();
    }

    private Dormitory mapToDormitory(DormitoryRequest source) {

        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(source.getNumberOfRooms());
        dormitory.setNumber(source.getNumber());
        dormitory.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());

        University university = universityService.getUniversity(source.getUniversity());
        dormitory.setUniversity(university);

        return dormitory;
    }
}
