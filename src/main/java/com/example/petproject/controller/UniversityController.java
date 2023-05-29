package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;
import com.example.petproject.model.University;
import com.example.petproject.service.university.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("universities")
public class UniversityController {

    private final UniversityService universityService;

    private final Converter<UniversityRequest, University> universityConverter;
    private final Converter<University, UniversityResponse> responseConverter;

    @Autowired
    public UniversityController(UniversityService universityService,
                          Converter<UniversityRequest, University> universityConverter,
                          Converter<University, UniversityResponse> responseConverter) {
        this.universityService = universityService;
        this.universityConverter = universityConverter;
        this.responseConverter = responseConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityResponse> update(@Valid @RequestBody UniversityRequest request, @PathVariable long id) {

        University convert = universityConverter.convert(request);
        convert.setId(id);

        University update = universityService.update(convert);

        return new ResponseEntity<>(responseConverter.convert(update), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityResponse> get(@PathVariable long id) {

        University University = universityService.getUniversity(id);

        return new ResponseEntity<>(responseConverter.convert(University), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UniversityResponse> create(@Valid @RequestBody UniversityRequest request) {

        University convert = universityConverter.convert(request);

        University university = universityService.create(convert);

        return new ResponseEntity<>(responseConverter.convert(university), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        universityService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<UniversityResponse>> getAll(@RequestParam int page, @RequestParam int size) {

        List<University> universities = universityService.getUniversities(page, size);

        return new ResponseEntity<>(responseConverter.convert(universities), HttpStatus.OK);
    }
}
