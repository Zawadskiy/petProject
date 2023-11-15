package com.example.petproject.service.dormitory;

import com.example.petproject.domain.Dormitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DormitoryService {

    Dormitory getDormitory(long id);

    Page<Dormitory> getAll(Pageable pageRequest);

    List<Dormitory> getAllIn(List<Long> id);

    Dormitory update(Dormitory dormitory);

    Dormitory create(Dormitory dormitory);

    void delete(long id);
}
