package com.example.petproject.facade.dormitory;

import com.example.petproject.converter.DormitoryConverter;
import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.response.DormitoryResponse;
import com.example.petproject.model.Dormitory;
import com.example.petproject.service.dormitory.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DormitoryFacadeImpl implements DormitoryFacade {

    private final DormitoryService dormitoryService;
    private final DormitoryConverter dormitoryConverter;

    @Autowired
    public DormitoryFacadeImpl(DormitoryService dormitoryService, DormitoryConverter dormitoryConverter) {
        this.dormitoryService = dormitoryService;
        this.dormitoryConverter = dormitoryConverter;
    }

    @Override
    // TODO: 16.05.2023 Почти не смотрел остальные фасады.
    //  Выглядит как бесполезный слой, который стоит с конвертером объединить
    public List<DormitoryResponse> getDormitories(int page, int size) {

        List<Dormitory> dormitories = dormitoryService.getDormitories(page, size);

        return dormitories.stream().map(dormitoryConverter::toDormitoryDto).toList();
    }

    @Override
    public DormitoryResponse updateDormitory(DormitoryRequest dormitoryDto, long id) {

        Dormitory dormitory = dormitoryConverter.toDormitory(dormitoryDto);
        dormitory.setId(id);

        dormitoryService.update(dormitory);

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryResponse getDormitory(long id) {

        Dormitory dormitory = dormitoryService.getDormitory(id);

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryResponse createDormitory(DormitoryRequest dormitoryDto) {

        Dormitory dormitory = dormitoryService.create(dormitoryConverter.toDormitory(dormitoryDto));

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryResponse deleteDormitory(long id) {

        dormitoryService.delete(id);

        //TODO
        return new DormitoryResponse();
    }
}
