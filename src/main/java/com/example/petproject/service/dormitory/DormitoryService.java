package com.example.petproject.service.dormitory;

import com.example.petproject.model.Dormitory;

import java.util.List;

public interface DormitoryService {
    Dormitory findByNumberAndUniversityId(String number, long id);

    Dormitory findByNumber(String number);

    List<Dormitory> findAll();

    List<Dormitory> findAllAvailableForAccommodation();

    List<Dormitory> getDormitories(int page, int size);

    Dormitory update(Dormitory dormitory);

    Dormitory findById(long id);

    Dormitory create(Dormitory dormitory);

    void deleteById(long id);
}
