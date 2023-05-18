package com.example.petproject.service.dormitory;

import com.example.petproject.model.Dormitory;

import java.util.List;

public interface DormitoryService {
    Dormitory getDormitory(long id, long universityId);

    Dormitory getDormitory(long id);

    List<Dormitory> getAllAvailableForAccommodation();

    List<Dormitory> getDormitories(int page, int size);

    Dormitory update(Dormitory dormitory);

    Dormitory create(Dormitory dormitory);

    void delete(long id);
}
