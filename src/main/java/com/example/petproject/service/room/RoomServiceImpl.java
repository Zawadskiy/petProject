package com.example.petproject.service.room;

import com.example.petproject.domain.Room;
import com.example.petproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getByDormitoryId(long dormitoryId) {
        return roomRepository.findAllByDormitoryIdAndAvailabilityForAccommodation(dormitoryId, true);
    }

    @Override
    public void delete(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoom(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Room update(Room update) {
        return roomRepository.save(update);
    }

    @Override
    public List<Room> getRooms(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Room> rooms = roomRepository.findAll(pageRequest);

        return rooms.getContent();
    }
}
