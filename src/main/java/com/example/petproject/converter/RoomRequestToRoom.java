package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Room;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomRequestToRoom implements Converter<RoomRequest, Room> {

    private final DormitoryService dormitoryService;

    public RoomRequestToRoom(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @Override
    public Room convert(RoomRequest source) {

        Room room = new Room();

        room.setNumber(source.getNumber());
        room.setCapacity(source.getCapacity());
        room.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());
        room.setResidentsGender(source.getResidentsGender());

        Dormitory dormitory = dormitoryService.getDormitory(source.getDormitory());
        room.setDormitory(dormitory);

        return room;
    }

    @Override
    public List<Room> convert(List<RoomRequest> source) {
        return null;
    }
}
