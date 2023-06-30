package com.example.petproject.controller;

import com.example.petproject.converter.UniversityConverter;
import com.example.petproject.converter.UniversityRequestConverter;
import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.domain.University;
import com.example.petproject.service.university.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    private final UniversityRequestConverter universityRequestConverter;
    private final UniversityConverter universityConverter;

    @Autowired
    public UniversityController(UniversityService universityService,
                                UniversityRequestConverter universityRequestConverter,
                                UniversityConverter universityConverter) {
        this.universityService = universityService;
        this.universityRequestConverter = universityRequestConverter;
        this.universityConverter = universityConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityResponse> update(@Valid @RequestBody UniversityRequest request, @PathVariable long id) {

        University university = universityRequestConverter.convert(request, id);
        University updatedUniversity = universityService.update(university);

        return new ResponseEntity<>(universityConverter.convert(updatedUniversity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> get(@PathVariable long id) {

        University University = universityService.getUniversity(id);

        return new ResponseEntity<>(universityConverter.convert(University), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UniversityResponse> create(@Valid @RequestBody UniversityRequest request) {

        University convert = universityRequestConverter.convert(request);

        University university = universityService.create(convert);

        return new ResponseEntity<>(universityConverter.convert(university), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        universityService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<University>> getAll(@PageableDefault Pageable pageRequest) {

        Page<University> universities = universityService.getAll(pageRequest);

        return new ResponseEntity<>(universities, HttpStatus.OK);
    }
}
