package com.example.petproject.converter;

import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomToRoomRequest implements Converter<Room, RoomResponse> {
    @Override
    public RoomResponse convert(Room source) {

        RoomResponse roomResponse = new RoomResponse();

        roomResponse.setId(source.getId());
        roomResponse.setCapacity(source.getCapacity());
        roomResponse.setNumber(source.getNumber());
        roomResponse.setAvailabilityForAccommodation(source.isAvailabilityForAccommodation());
        roomResponse.setResidentsGender(source.getResidentsGender());

        if (source.getDormitory() != null) {
            roomResponse.setDormitory(source.getDormitory().getId());
        }

        return roomResponse;
    }

    @Override
    public List<RoomResponse> convert(List<Room> source) {
        return null;
    }
}
