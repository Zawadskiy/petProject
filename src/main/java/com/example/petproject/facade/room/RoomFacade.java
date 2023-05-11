package com.example.petproject.facade.room;

import com.example.petproject.dto.model.room.RoomDto;

import java.util.List;

public interface RoomFacade {
    RoomDto updateRoom(RoomDto request);
    RoomDto getRoom(long id);
    RoomDto createRoom(RoomDto request);
    RoomDto deleteRoom(long id);
    List<RoomDto> getRooms(int page, int size);
}
