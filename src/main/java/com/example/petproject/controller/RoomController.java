package com.example.petproject.controller;

import com.example.petproject.converter.RoomConverter;
import com.example.petproject.converter.RoomRequestConverter;
import com.example.petproject.domain.Room;
import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.service.room.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    private final RoomRequestConverter roomRequestConverter;
    private final RoomConverter roomConverter;

    @Autowired
    public RoomController(RoomService roomService,
                          RoomRequestConverter roomRequestConverter,
                          RoomConverter roomConverter) {
        this.roomService = roomService;
        this.roomRequestConverter = roomRequestConverter;
        this.roomConverter = roomConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@Valid @RequestBody RoomRequest request, @PathVariable long id) {

        Room room = roomRequestConverter.convert(request, id);
        Room updatedRoom = roomService.update(room);

        return new ResponseEntity<>(roomConverter.convert(updatedRoom), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> get(@PathVariable long id) {

        Room room = roomService.getRoom(id);

        return new ResponseEntity<>(this.roomConverter.convert(room), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest request) {

        Room convert = roomRequestConverter.convert(request);

        Room room = roomService.create(convert);

        return new ResponseEntity<>(this.roomConverter.convert(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        roomService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Room>> getAll(@PageableDefault Pageable pageRequest) {

        Page<Room> rooms = roomService.getAll(pageRequest);

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
