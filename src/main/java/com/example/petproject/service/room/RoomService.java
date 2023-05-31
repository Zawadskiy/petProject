package com.example.petproject.service.room;

import com.example.petproject.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> getByDormitoryId(long dormitoryId);
    void delete(long id);
    Room create(Room room);
    Room getRoom(long id);
    Room update(Room room);
    List<Room> getRooms(int page, int size);
}
