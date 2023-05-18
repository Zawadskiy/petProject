package com.example.petproject.converter;

import com.example.petproject.dto.response.DormitoryResponse;
import com.example.petproject.model.Dormitory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DormitoryToDormitoryResponse implements Converter<Dormitory, DormitoryResponse> {
    @Override
    public DormitoryResponse convert(Dormitory source) {

        DormitoryResponse dormitoryDto = new DormitoryResponse();

        dormitoryDto.setId(source.getId());

        if (source.getUniversity() != null) {
            dormitoryDto.setUniversity(source.getUniversity().getId());
        }

        dormitoryDto.setNumber(source.getNumber());
        dormitoryDto.setNumberOfRooms(source.getNumberOfRooms());
        dormitoryDto.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());

        return dormitoryDto;
    }

    @Override
    public List<DormitoryResponse> convert(List<Dormitory> source) {
        return source.stream().map(this::convert).toList();
    }
}
