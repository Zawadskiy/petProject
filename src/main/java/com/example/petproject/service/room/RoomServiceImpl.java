package com.example.petproject.service.room;

import com.example.petproject.model.Room;
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
    public Room findByNumberAndDormitoryId(String number, long dormitory_id) {
        return roomRepository.findByNumberAndDormitoryId(number, dormitory_id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Room> findByDormitoryId(long dormitory_id) {
        return roomRepository.findAllByDormitoryIdAndAvailabilityForAccommodation(dormitory_id, true);
    }

    @Override
    public void deleteById(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Room update(Room update) {
        Room room = findById(update.getId());

        room.setCapacity(update.getCapacity());
        room.setDormitory(update.getDormitory());
        room.setResidentsGender(update.getResidentsGender());
        room.setAvailabilityForAccommodation(update.isAvailabilityForAccommodation());
        room.setNumber(update.getNumber());

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRooms(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Room> rooms = roomRepository.findAll(pageRequest);

        return rooms.getContent();
    }
}
