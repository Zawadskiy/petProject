package com.example.petproject.service;

import com.example.petproject.model.Room;
import com.example.petproject.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findByNumberAndDormitoryId(String number, long dormitory_id) {
        return roomRepository.findByNumberAndDormitoryId(number, dormitory_id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Room> findByDormitoryId(long dormitory_id) {
        return roomRepository.findAllByDormitoryIdAndAvailabilityForAccommodation(dormitory_id, true);
    }
}
