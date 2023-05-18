package com.example.petproject.facade.room;

import com.example.petproject.converter.RoomConverter;
import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;
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
    public List<RoomResponse> getRooms(int page, int size) {

        List<Room> rooms = roomService.getRooms(page, size);

        return rooms.stream().map(roomConverter::toRoomDto).toList();
    }

    @Override
    public RoomResponse updateRoom(RoomRequest roomDto, long id) {

        Room room = roomConverter.toRoom(roomDto);
        room.setId(id);
        roomService.update(room);

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomResponse getRoom(long id) {

        Room room = roomService.getRoom(id);

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomDto) {

        Room room = roomService.create(roomConverter.toRoom(roomDto));

        return roomConverter.toRoomDto(room);
    }

    @Override
    public RoomResponse deleteRoom(long id) {

        roomService.delete(id);

        return new RoomResponse();
    }
}
