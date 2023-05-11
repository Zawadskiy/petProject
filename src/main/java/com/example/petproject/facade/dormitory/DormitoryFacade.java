package com.example.petproject.facade.dormitory;

import com.example.petproject.dto.model.dormitory.DormitoryDto;

import java.util.List;

public interface DormitoryFacade {
    DormitoryDto updateDormitory(DormitoryDto request);
    DormitoryDto getDormitory(long id);
    DormitoryDto createDormitory(DormitoryDto request);
    DormitoryDto deleteDormitory(long id);
    List<DormitoryDto> getDormitories(int page, int size);
}
