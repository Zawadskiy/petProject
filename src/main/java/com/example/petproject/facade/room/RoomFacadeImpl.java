package com.example.petproject.facade.room;

import com.example.petproject.converter.RoomConverter;
import com.example.petproject.dto.model.room.RoomDto;
import com.example.petproject.model.Room;
import com.example.petproject.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomFacadeImpl implements RoomFacade {

    private final RoomService roomService;
    private final RoomConverter roomConverter;

    @Autowired
    public RoomFacadeImpl(RoomService roomService, RoomConverter roomConverter) {
        this.roomService = roomService;
        this.roomConverter = roomConverter;
    }

    @Override
    public List<RoomDto> getRooms(int page, int size) {

        List<Room> rooms = roomService.getRooms(page, size);

        return rooms.stream().map(roomConverter::toRoomDto).toList();
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto) {

        Room room = roomService.update(roomConverter.toRoom(roomDto));

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomDto getRoom(long id) {

        Room room = roomService.findById(id);

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {

        Room room = roomService.create(roomConverter.toRoom(roomDto));

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomDto deleteRoom(long id) {

        roomService.deleteById(id);

        return new RoomDto();
    }
}
