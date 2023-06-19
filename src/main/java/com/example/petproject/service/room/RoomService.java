package com.example.petproject.service.room;

import com.example.petproject.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {

    void delete(long id);

    Room create(Room room);

    Room getRoom(long id);

    Room update(Room room);

    Page<Room> getRooms(Pageable pageRequest);
}
