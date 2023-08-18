package com.example.petproject.service.room;

import com.example.petproject.domain.Room;
import com.example.petproject.repository.DormitoryRepository;
import com.example.petproject.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, DormitoryRepository dormitoryRepository) {
        this.roomRepository = roomRepository;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Room getRoom(long id) {
        return roomRepository.findByIdEx(id);
    }

    @Override
    public Page<Room> getAll(Pageable pageRequest) {
        return roomRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public void delete(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public Room update(Room update) {

        Room room = roomRepository.findByIdEx(update.getId());

        // TODO: 18.08.2023 переменная?
        room.setDormitory(dormitoryRepository.findByIdEx(update.getDormitory().getId()));
        room.setNumber(update.getNumber());
        room.setResidentsGender(update.getResidentsGender());
        room.setCapacity(update.getCapacity());

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllIn(List<Long> id) {
        return roomRepository.findAllById(id);
    }
}
