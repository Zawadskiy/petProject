package com.example.petproject.converter;

import com.example.petproject.dto.model.room.RoomDto;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Gender;
import com.example.petproject.model.Room;
import com.example.petproject.service.dormitory.DormitoryService;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter {

    private final DormitoryService dormitoryService;

    public RoomConverter(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    public Room toRoom(RoomDto roomDto) {
        Room room = new Room();

        room.setId(roomDto.getId());
        room.setNumber(roomDto.getNumber());
        room.setCapacity(room.getCapacity());
        room.setAvailabilityForAccommodation(room.isAvailabilityForAccommodation());

        room.setResidentsGender(Gender.valueOf(roomDto.getResidentsGender()));

        Dormitory dormitory = dormitoryService.findByNumber(roomDto.getDormitoryNumber());
        room.setDormitory(dormitory);

        return room;
    }

    public RoomDto toRoomDto(Room room) {
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setNumber(room.getNumber());
        roomDto.setDormitoryNumber(room.getDormitory().getNumber());
        roomDto.setAvailabilityForAccommodation(room.isAvailabilityForAccommodation());
        roomDto.setResidentsGender(room.getResidentsGender().name());

        return roomDto;
    }
}
