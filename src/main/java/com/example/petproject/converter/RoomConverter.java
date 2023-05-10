package com.example.petproject.converter;

import com.example.petproject.dto.model.room.RoomDto;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Gender;
import com.example.petproject.model.Room;
import com.example.petproject.service.DormitoryService;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {

    private final DormitoryService dormitoryService;

    public RoomConverter(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    public Room toRoom(RoomDto roomDto) {
        Room room = new Room();

        room.setNumber(roomDto.getNumber());
        room.setCapacity(room.getCapacity());
        room.setAvailabilityForAccommodation(room.isAvailabilityForAccommodation());

        room.setResidentsGender(Gender.valueOf(roomDto.getResidentsGender()));

        Dormitory dormitory = dormitoryService.findByNumber(roomDto.getDormitory());
        room.setDormitory(dormitory);

        return room;
    }

    public RoomDto toRoomDto(Room room) {
        RoomDto roomDto = new RoomDto();

        roomDto.setCapacity(room.getCapacity());
        roomDto.setNumber(room.getNumber());
        roomDto.setDormitory(room.getDormitory().getNumber());
        roomDto.setAvailabilityForAccommodation(room.isAvailabilityForAccommodation());
        roomDto.setResidentsGender(room.getResidentsGender().name());

        return roomDto;
    }
}
