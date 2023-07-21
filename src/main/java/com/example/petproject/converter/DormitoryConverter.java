package com.example.petproject.converter;

import com.example.petproject.dto.response.DormitoryResponse;
import com.example.petproject.domain.Dormitory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DormitoryConverter implements Converter<Dormitory, DormitoryResponse> {

    @Override
    public DormitoryResponse convert(Dormitory source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<DormitoryResponse> convert(List<Dormitory> source) {
        return source.stream()
                .map(this::mapToDormitoryResponse)
                .toList();
    }

    private DormitoryResponse mapToDormitoryResponse(Dormitory source) {

        DormitoryResponse dormitoryDto = new DormitoryResponse();

        dormitoryDto.setId(source.getId());

        if (source.getUniversity() != null) {
            dormitoryDto.setUniversity(source.getUniversity().getId());
        }

        dormitoryDto.setNumber(source.getNumber());
        dormitoryDto.setNumberOfRooms(source.getNumberOfRooms());
        dormitoryDto.setAvailabilityForAccommodation(source.isAccommodationAvailability());

        return dormitoryDto;
    }
}
