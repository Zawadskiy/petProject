package com.example.petproject.service;

import com.example.petproject.model.Dormitory;
import com.example.petproject.repository.DormitoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Dormitory findByNumberAndUniversityId(String number, long universityId) {
        return dormitoryRepository.findByNumberAndUniversityId(number, universityId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Dormitory findByNumber(String number) {
        return dormitoryRepository.findByNumber(number).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Dormitory> findAll() {
        return dormitoryRepository.findAll();
    }

    @Override
    public List<Dormitory> findAllAvailableForAccommodation() {
        return dormitoryRepository.findAllByAvailabilityForAccommodation(true);
    }
}
