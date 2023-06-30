package com.example.petproject.service.room;

import com.example.petproject.domain.Room;
import com.example.petproject.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
    // TODO: 23.06.2023 у тебя же жпа. Тут есть нюанс с тем, что объект update(название не очень) не под управлением ем.
    //  Ну и нюанс с тем, что это так не работает, если есть неизменяемые поля. Точнее работает, но надо немного кунг-фу
    public Room update(Room update) {
        return roomRepository.save(update);
    }
}
