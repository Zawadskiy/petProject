package com.example.petproject.controller;

import com.example.petproject.dto.model.university.UniversityDto;
import com.example.petproject.facade.university.UniversityFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("university")
public class UniversityController {

    private final UniversityFacade universityFacade;

    @Autowired
    public UniversityController(UniversityFacade universityFacade) {
        this.universityFacade = universityFacade;
    }

    //TODO якщо роль не співпадаю з енамом, то ми отримаємо
    // "title": "Bad Request",
    // "status": 400
    // exception прокидуеться раніше на моменті десеріалізації...знайти варіант кастомізували відповідь сервера...
    @PutMapping("/update")
    public UniversityDto updateUniversity(@Valid @RequestBody UniversityDto request) {
        return universityFacade.updateUniversity(request);
    }

    @GetMapping("/get/{id}")
    public UniversityDto getUniversity(@PathVariable long id) {
        return universityFacade.getUniversity(id);
    }

    @PostMapping("/new")
    public UniversityDto createUniversity(@Valid @RequestBody UniversityDto request) {
        return universityFacade.createUniversity(request);
    }

    @DeleteMapping("/delete/{id}")
    public UniversityDto deleteUniversity(@PathVariable long id) {
        return universityFacade.deleteUniversity(id);
    }

    @GetMapping(value = "/get/all", params = {"page", "size"})
    public List<UniversityDto> getUniversities(@RequestParam("page") int page, @RequestParam("size") int size) {
        return universityFacade.getUniversities(page, size);
    }
}
