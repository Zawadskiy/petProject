package com.example.petproject.controller;

import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.response.DormitoryResponse;
import com.example.petproject.facade.dormitory.DormitoryFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dormitories")
public class DormitoryController {

    private final DormitoryFacade dormitoryFacade;

    @Autowired
    public DormitoryController(DormitoryFacade dormitoryFacade) {
        this.dormitoryFacade = dormitoryFacade;
    }

    @PutMapping("/{id}")
    public DormitoryResponse update(@Valid @RequestBody DormitoryRequest request, @PathVariable long id) {
        return dormitoryFacade.updateDormitory(request, id);
    }

    @GetMapping("/{id}")
    public DormitoryResponse get(@PathVariable long id) {
        return dormitoryFacade.getDormitory(id);
    }

    @PostMapping
    public DormitoryResponse create(@Valid @RequestBody DormitoryRequest request) {
        return dormitoryFacade.createDormitory(request);
    }

    @DeleteMapping("/{id}")
    public DormitoryResponse delete(@PathVariable long id) {
        return dormitoryFacade.deleteDormitory(id);
    }

    @GetMapping(params = {"page", "size"})
    // TODO: 16.05.2023 @PageableDefault. Не хочешь заодно фильтрацию сюда прикрутить?
    public List<DormitoryResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return dormitoryFacade.getDormitories(page, size);
    }
}
