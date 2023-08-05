package com.example.petproject.converter;

import com.example.petproject.domain.University;
import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.Room;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.service.dormitory.DormitoryService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RoomRequestConverter implements ConverterEx<RoomRequest, Room> {

    private final DormitoryService dormitoryService;

    public RoomRequestConverter(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @Override
    public Room convert(RoomRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public Room convert(RoomRequest source, long id) {

        Room room = convert(source);
        room.setId(id);

        return room;
    }

    @Override
    public List<Room> convert(List<RoomRequest> source) {

        List<Long> dormitories = source.stream()
                .map(RoomRequest::getDormitory)
                .distinct().toList();
        Map<Long, Dormitory> dormitoryMap = dormitoryService.getAllIn(dormitories)
                .stream()
                .collect(Collectors.toMap(Dormitory::getId, Function.identity()));

        return source.stream()
                .map(request -> mapToRoom(request, dormitoryMap.get(request.getDormitory())))
                .toList();
    }

    private Room mapToRoom(RoomRequest roomRequest, Dormitory dormitory) {

        Room room = new Room();

        room.setNumber(roomRequest.getNumber());
        room.setCapacity(roomRequest.getCapacity());
        room.setAvailabilityForAccommodation(roomRequest.isAvailabilityForAccommodation());
        room.setResidentsGender(roomRequest.getResidentsGender());
        room.setDormitory(dormitory);

        return room;
    }
}
