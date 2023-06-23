package com.example.petproject.service.university;

import com.example.petproject.domain.University;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University getUniversity(long id) {
        return universityRepository.findByIdCustom(id);
    }

    @Override
    public Page<University> getAll(Pageable pageRequest) {
        return universityRepository.findAll(pageRequest);
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


}
