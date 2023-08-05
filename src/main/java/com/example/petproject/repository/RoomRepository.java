package com.example.petproject.repository;

import com.example.petproject.domain.Room;

import java.util.List;

public interface RoomRepository extends CustomRepository<Room, Long> {
    List<Room> findAllByIdIn(List<Long> id);
}
