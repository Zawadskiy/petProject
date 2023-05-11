package com.example.petproject.service.room;

import com.example.petproject.model.Room;

import java.util.List;

public interface RoomService {
    Room findByNumberAndDormitoryId(String number, long dormitory_id);
    List<Room> findByDormitoryId(long dormitory_id);
    void deleteById(long id);
    Room create(Room room);
    Room findById(long id);
    Room update(Room room);
    List<Room> getRooms(int page, int size);
}
