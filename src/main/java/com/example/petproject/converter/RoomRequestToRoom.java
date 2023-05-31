package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.Room;
import com.example.petproject.service.dormitory.DormitoryService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RoomRequestToRoom implements Converter<RoomRequest, Room> {

    private final DormitoryService dormitoryService;

    public RoomRequestToRoom(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @Override
    public Room convert(RoomRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<Room> convert(List<RoomRequest> source) {
        return source.stream()
                .map(this::mapToRoom)
                .toList();
    }

    private Room mapToRoom(RoomRequest roomRequest) {

        Room room = new Room();

        room.setNumber(roomRequest.getNumber());
        room.setCapacity(roomRequest.getCapacity());
        room.setAvailabilityForAccommodation(roomRequest.isAvailabilityForAccommodation());
        room.setResidentsGender(roomRequest.getResidentsGender());

        Dormitory dormitory = dormitoryService.getDormitory(roomRequest.getDormitory());
        room.setDormitory(dormitory);

        return room;
    }
}
