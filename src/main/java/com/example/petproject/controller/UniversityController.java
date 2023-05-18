package com.example.petproject.controller;

import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.facade.university.UniversityFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("universities")
public class UniversityController {

    private final UniversityFacade universityFacade;

    @Autowired
    public UniversityController(UniversityFacade universityFacade) {
        this.universityFacade = universityFacade;
    }

    @PutMapping("/{id}")
    public UniversityResponse update(@Valid @RequestBody UniversityRequest request, @PathVariable long id) {
        return universityFacade.updateUniversity(request, id);
    }

    @GetMapping("/{id}")
    public UniversityResponse get(@PathVariable long id) {
        return universityFacade.getUniversity(id);
    }

    @PostMapping
    public UniversityResponse create(@Valid @RequestBody UniversityRequest request) {
        return universityFacade.createUniversity(request);
    }

    @DeleteMapping("/{id}")
    public UniversityResponse delete(@PathVariable long id) {
        return universityFacade.deleteUniversity(id);
    }

    @GetMapping(params = {"page", "size"})
    public List<UniversityResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return universityFacade.getUniversities(page, size);
    }
}
