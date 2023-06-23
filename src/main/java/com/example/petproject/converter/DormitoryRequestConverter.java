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
public class DormitoryRequestConverter implements Converter<DormitoryRequest, Dormitory> {

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
    // TODO: 22.06.2023 т.е. для каждой общаги ты делаешь запрос н аунивер в бд. Смысл?
    //  Можно по списку общаг получить мапу айди универа-универ и дальше раскидать из нее.
    //  Получится константное число запросов, а не как сейчас.
    //  Отдельный вопрос - нужно ли сетать объект на уровне конвертера из реквеста.
    //  Мб имеет смысл делать на уровне сервиса
    public List<Dormitory> convert(List<DormitoryRequest> source) {

        Map<Long, University> universities = source.stream()
                .map(DormitoryRequest::getUniversity)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), universityService::getUniversity));

        return source.stream()
                .map(dreq -> mapToDormitory(dreq, universities))
                .toList();
    }

    private Dormitory mapToDormitory(DormitoryRequest source, Map<Long, University> universities) {

        Dormitory dormitory = new Dormitory();

        dormitory.setNumberOfRooms(source.getNumberOfRooms());
        dormitory.setNumber(source.getNumber());
        dormitory.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());

        dormitory.setUniversity(universities.get(source.getUniversity()));

        return dormitory;
    }
}
