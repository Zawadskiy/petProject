package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
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

    // TODO: 22.06.2023 я бы здесь рекомендовал наследника указывать типом.
    //  Спринг скушает и так, но смысл от избыточного обобщения?
    private final Converter<DormitoryRequest, Dormitory> dormitoryConverter;
    private final Converter<Dormitory, DormitoryResponse> responseConverter;

    @Autowired
    public DormitoryController(DormitoryService dormitoryService,
                               Converter<DormitoryRequest, Dormitory> dormitoryConverter,
                               Converter<Dormitory, DormitoryResponse> responseConverter) {
        this.dormitoryService = dormitoryService;
        this.dormitoryConverter = dormitoryConverter;
        this.responseConverter = responseConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DormitoryResponse> update(@Valid @RequestBody DormitoryRequest request, @PathVariable long id) {
// TODO: 22.06.2023 неудачное название переменной. Почему бы в конвертере не сетать айди?
//  Можно ведь расширить интерфейс конвертера, сделав в т.ч. конрвертер, кроме сорса и таргета принимающего метаинфу.
//  Ну и непонятно, почему базовый интерфейс конвертера не хочешь спринговый стянуть
        Dormitory convert = dormitoryConverter.convert(request);
        convert.setId(id);

        Dormitory update = dormitoryService.update(convert);

        return new ResponseEntity<>(responseConverter.convert(update), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DormitoryResponse> get(@PathVariable long id) {

        Dormitory dormitory = dormitoryService.getDormitory(id);

        return new ResponseEntity<>(responseConverter.convert(dormitory), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DormitoryResponse> create(@Valid @RequestBody DormitoryRequest request) {

        Dormitory convert = dormitoryConverter.convert(request);

        Dormitory dormitory = dormitoryService.create(convert);

        return new ResponseEntity<>(responseConverter.convert(dormitory), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        dormitoryService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    // TODO: 16.05.2023 Не хочешь заодно фильтрацию сюда прикрутить?
    //  pageRequest -> pageable. выглядит логичнее
    public ResponseEntity<Page<Dormitory>> getAll(@PageableDefault Pageable pageRequest) {

        Page<Dormitory> dormitories = dormitoryService.getAll(pageRequest);

        return new ResponseEntity<>(dormitories, HttpStatus.OK);
    }
}
