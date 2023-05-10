package com.example.petproject.service;

import com.example.petproject.model.University;

import java.util.List;

public interface UniversityService {

    University findByName(String name);

    List<University> findAll();
}
