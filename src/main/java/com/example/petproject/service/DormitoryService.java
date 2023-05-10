package com.example.petproject.service;

import com.example.petproject.model.Dormitory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DormitoryService {
    Dormitory findByNumberAndUniversityId(String number, long id);
    Dormitory findByNumber(String number);
    List<Dormitory> findAll();
    List<Dormitory> findAllAvailableForAccommodation();
}
