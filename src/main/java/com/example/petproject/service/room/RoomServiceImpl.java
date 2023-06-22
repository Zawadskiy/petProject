package com.example.petproject.service.room;

import com.example.petproject.domain.Room;
import com.example.petproject.exception.RoomNotFoundException;
import com.example.petproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public void delete(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoom(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    // TODO: 23.06.2023 у тебя же жпа. Тут есть нюанс с тем, что объект update(название не очень) не под управлением ем.
    //  Ну и нюанс с тем, что это так не работает, если есть неизменяемые поля. Точнее работает, но надо немного кунг-фу
    public Room update(Room update) {
        return roomRepository.save(update);
    }

    @Override
    public Page<Room> getRooms(Pageable pageRequest) {
        return roomRepository.findAll(pageRequest);
    }
}
