package com.example.petproject.controller;

import com.example.petproject.dto.model.room.RoomDto;
import com.example.petproject.facade.room.RoomFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomFacade roomFacade;

    @Autowired
    public RoomController(RoomFacade roomFacade) {
        this.roomFacade = roomFacade;
    }

    @PutMapping("/update")
    public RoomDto updateRoom(@Valid @RequestBody RoomDto request) {
        return roomFacade.updateRoom(request);
    }

    @GetMapping("/get/{id}")
    public RoomDto getRoom(@PathVariable long id) {
        return roomFacade.getRoom(id);
    }

    @PostMapping("/new")
    public RoomDto createRoom(@Valid @RequestBody RoomDto request) {
        return roomFacade.createRoom(request);
    }

    @DeleteMapping("/delete/{id}")
    public RoomDto deleteRoom(@PathVariable long id) {
        return roomFacade.deleteRoom(id);
    }

    @GetMapping(value = "/get/all", params = {"page", "size"})
    public List<RoomDto> getUniversities(@RequestParam("page") int page, @RequestParam("size") int size) {
        return roomFacade.getRooms(page, size);
    }
}
