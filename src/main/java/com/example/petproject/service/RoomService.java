package com.example.petproject.service;

import com.example.petproject.model.Room;

import java.util.List;

public interface RoomService {
    Room findByNumberAndDormitoryId(String number, long dormitory_id);
    List<Room> findByDormitoryId(long dormitory_id);
}
