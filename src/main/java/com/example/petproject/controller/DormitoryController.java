package com.example.petproject.controller;

import com.example.petproject.converter.DormitoryConverter;
import com.example.petproject.converter.DormitoryRequestConverter;
import com.example.petproject.domain.Dormitory;
import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.response.DormitoryResponse;
import com.example.petproject.service.dormitory.DormitoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dormitories")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    private final DormitoryRequestConverter dormitoryRequestConverter;
    private final DormitoryConverter dormitoryConverter;

    @Autowired
    public DormitoryController(DormitoryService dormitoryService,
                               DormitoryRequestConverter dormitoryRequestConverter,
                               DormitoryConverter dormitoryConverter) {
        this.dormitoryService = dormitoryService;
        this.dormitoryRequestConverter = dormitoryRequestConverter;
        this.dormitoryConverter = dormitoryConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DormitoryResponse> update(@Valid @RequestBody DormitoryRequest request, @PathVariable long id) {

        Dormitory dormitory = dormitoryRequestConverter.convert(request, id);
        Dormitory updatedDormitory = dormitoryService.update(dormitory);

        return new ResponseEntity<>(dormitoryConverter.convert(updatedDormitory), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DormitoryResponse> get(@PathVariable long id) {

        Dormitory dormitory = dormitoryService.getDormitory(id);

        return new ResponseEntity<>(dormitoryConverter.convert(dormitory), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DormitoryResponse> create(@Valid @RequestBody DormitoryRequest request) {

        Dormitory convert = dormitoryRequestConverter.convert(request);
        Dormitory dormitory = dormitoryService.create(convert);

        return new ResponseEntity<>(dormitoryConverter.convert(dormitory), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        dormitoryService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Dormitory>> getAll(@PageableDefault Pageable pageable) {

        Page<Dormitory> dormitories = dormitoryService.getAll(pageable);

        return new ResponseEntity<>(dormitories, HttpStatus.OK);
    }
}
