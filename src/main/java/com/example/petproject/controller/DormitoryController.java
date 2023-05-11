package com.example.petproject.controller;

import com.example.petproject.dto.model.dormitory.DormitoryDto;
import com.example.petproject.facade.dormitory.DormitoryFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    private final DormitoryFacade dormitoryFacade;

    @Autowired
    public DormitoryController(DormitoryFacade dormitoryFacade) {
        this.dormitoryFacade = dormitoryFacade;
    }

    @PutMapping("/update")
    public DormitoryDto updateDormitory(@Valid @RequestBody DormitoryDto request) {
        return dormitoryFacade.updateDormitory(request);
    }

    @GetMapping("/get/{id}")
    public DormitoryDto getDormitory(@PathVariable long id) {
        return dormitoryFacade.getDormitory(id);
    }

    @PostMapping("/new")
    public DormitoryDto createDormitory(@Valid @RequestBody DormitoryDto request) {
        return dormitoryFacade.createDormitory(request);
    }

    @DeleteMapping("/delete/{id}")
    public DormitoryDto deleteDormitory(@PathVariable long id) {
        return dormitoryFacade.deleteDormitory(id);
    }

    @GetMapping(value = "/get/all", params = {"page", "size"})
    public List<DormitoryDto> getDormitories(@RequestParam("page") int page, @RequestParam("size") int size) {
        return dormitoryFacade.getDormitories(page, size);
    }
}
