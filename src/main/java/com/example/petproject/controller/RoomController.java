package com.example.petproject.controller;

import com.example.petproject.dto.request.modify.RoomRequest;
import com.example.petproject.dto.response.RoomResponse;
import com.example.petproject.facade.room.RoomFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomFacade roomFacade;

    @Autowired
    public RoomController(RoomFacade roomFacade) {
        this.roomFacade = roomFacade;
    }

    @PutMapping("/{id}")
    public RoomResponse update(@Valid @RequestBody RoomRequest request, @PathVariable long id) {
        return roomFacade.updateRoom(request, id);
    }

    @GetMapping("/{id}")
    public RoomResponse get(@PathVariable long id) {
        return roomFacade.getRoom(id);
    }

    @PostMapping
    public RoomResponse create(@Valid @RequestBody RoomRequest request) {
        return roomFacade.createRoom(request);
    }

    @DeleteMapping("/{id}")
    public RoomResponse delete(@PathVariable long id) {
        return roomFacade.deleteRoom(id);
    }

    @GetMapping(params = {"page", "size"})
    public List<RoomResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return roomFacade.getRooms(page, size);
    }
}
