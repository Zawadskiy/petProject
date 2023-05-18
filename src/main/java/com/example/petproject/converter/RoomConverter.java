package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Room;
import com.example.petproject.service.dormitory.DormitoryService;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {

    private final DormitoryService dormitoryService;

    public RoomConverter(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    public Room toRoom(RoomRequest roomDto) {

        Room room = new Room();

        room.setNumber(roomDto.getNumber());
        room.setCapacity(roomDto.getCapacity());
        room.setAvailabilityForAccommodation(roomDto.isAvailabilityForAccommodation());
        room.setResidentsGender(roomDto.getResidentsGender());

        Dormitory dormitory = dormitoryService.getDormitory(roomDto.getDormitory());
        room.setDormitory(dormitory);

        return room;
    }

    public RoomResponse toRoomDto(Room room) {

        RoomResponse roomDto = new RoomResponse();

        roomDto.setId(room.getId());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setNumber(room.getNumber());
        roomDto.setAvailabilityForAccommodation(room.isAvailabilityForAccommodation());
        roomDto.setResidentsGender(room.getResidentsGender());

        if (room.getDormitory() != null) {
            roomDto.setDormitory(room.getDormitory().getId());
        }

        return roomDto;
    }
}
