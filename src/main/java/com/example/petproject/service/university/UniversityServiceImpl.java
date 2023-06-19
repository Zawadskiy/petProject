package com.example.petproject.service.university;

import com.example.petproject.domain.University;
import com.example.petproject.exception.UniversityNotFoundException;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University getUniversity(long id) {
        return universityRepository.findById(id).orElseThrow(() -> new UniversityNotFoundException(id));
    }

    @Override
    @Transactional
    public University update(University update) {
        return universityRepository.save(update);
    }

    @Override
    @Transactional
    public University create(University university) {
        return universityRepository.save(university);
    }

    @Override
    @Transactional
    public void delete(long id) {
        universityRepository.deleteById(id);
    }

    @Override
    public Page<University> getUniversities(Pageable pageRequest) {
        return universityRepository.findAll(pageRequest);
    }
}
