package com.example.petproject.facade.room;

import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;

import java.util.List;

public interface RoomFacade {
    RoomResponse updateRoom(RoomRequest request, long id);
    RoomResponse getRoom(long id);
    RoomResponse createRoom(RoomRequest request);
    RoomResponse deleteRoom(long id);
    List<RoomResponse> getRooms(int page, int size);
}
