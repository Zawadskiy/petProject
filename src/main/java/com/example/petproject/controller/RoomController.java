package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.domain.Room;
import com.example.petproject.service.room.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    private final Converter<RoomRequest, Room> roomConverter;
    private final Converter<Room, RoomResponse> responseConverter;

    @Autowired
    public RoomController(RoomService roomService,
                               Converter<RoomRequest, Room> roomConverter,
                               Converter<Room, RoomResponse> responseConverter) {
        this.roomService = roomService;
        this.roomConverter = roomConverter;
        this.responseConverter = responseConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@Valid @RequestBody RoomRequest request, @PathVariable long id) {

        Room convert = roomConverter.convert(request);
        convert.setId(id);

        Room update = roomService.update(convert);

        return new ResponseEntity<>(responseConverter.convert(update), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> get(@PathVariable long id) {

        Room room = roomService.getRoom(id);

        return new ResponseEntity<>(responseConverter.convert(room), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest request) {

        Room convert = roomConverter.convert(request);

        Room room = roomService.create(convert);

        return new ResponseEntity<>(responseConverter.convert(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        roomService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<RoomResponse>> getAll(@RequestParam int page, @RequestParam int size) {

        List<Room> rooms = roomService.getRooms(page, size);

        return new ResponseEntity<>(responseConverter.convert(rooms), HttpStatus.OK);
    }
}
