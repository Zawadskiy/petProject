package com.example.petproject.converter;

import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.domain.Room;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RoomConverter implements Converter<Room, RoomResponse> {

    @Override
    public RoomResponse convert(Room source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<RoomResponse> convert(List<Room> source) {
        return source.stream()
                .map(this::mapToRoomResponse)
                .toList();
    }

    private RoomResponse mapToRoomResponse(Room room) {

        RoomResponse roomResponse = new RoomResponse();

        roomResponse.setId(room.getId());
        roomResponse.setCapacity(room.getCapacity());
        roomResponse.setNumber(room.getNumber());
        roomResponse.setAvailabilityForAccommodation(room.isAccommodationAvailability());
        roomResponse.setResidentsGender(room.getResidentsGender());

        if (room.getDormitory() != null) {
            roomResponse.setDormitory(room.getDormitory().getId());
        }

        return roomResponse;
    }
}
