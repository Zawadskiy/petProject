package com.example.petproject.service;

import com.example.petproject.model.University;
import com.example.petproject.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University findByName(String name) {
        return universityRepository.findByName(name).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }
}
