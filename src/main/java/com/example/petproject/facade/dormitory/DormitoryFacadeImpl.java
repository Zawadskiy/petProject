package com.example.petproject.facade.dormitory;

import com.example.petproject.converter.DormitoryConverter;
import com.example.petproject.dto.model.dormitory.DormitoryDto;
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
    public List<DormitoryDto> getDormitories(int page, int size) {

        List<Dormitory> dormitories = dormitoryService.getDormitories(page, size);

        return dormitories.stream().map(dormitoryConverter::toDormitoryDto).toList();
    }

    @Override
    public DormitoryDto updateDormitory(DormitoryDto dormitoryDto) {

        Dormitory dormitory = dormitoryService.update(dormitoryConverter.toDormitory(dormitoryDto));

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryDto getDormitory(long id) {

        Dormitory dormitory = dormitoryService.findById(id);

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryDto createDormitory(DormitoryDto dormitoryDto) {

        Dormitory dormitory = dormitoryService.create(dormitoryConverter.toDormitory(dormitoryDto));

        return dormitoryConverter.toDormitoryDto(dormitory);
    }

    @Override
    public DormitoryDto deleteDormitory(long id) {

        dormitoryService.deleteById(id);

        //TODO
        return new DormitoryDto();
    }
}
