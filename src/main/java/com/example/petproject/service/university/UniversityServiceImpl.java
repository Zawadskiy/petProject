package com.example.petproject.service.university;

import com.example.petproject.model.University;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public University findByName(String name) {
        return universityRepository.findByName(name).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    @Transactional
    public University update(University update) {

        University university = findById(update.getId());

        university.setStudyDuration(update.getStudyDuration());
        university.setName(update.getName());

        return universityRepository.save(university);
    }

    @Override
    public University findById(long id) {
        return universityRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    @Transactional
    public University create(University university) {
        return universityRepository.save(university);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        universityRepository.deleteById(id);
        //TODO проверить каскадное удаление университета из общежитий и студентов) аналогично в других сервисах
    }

    @Override
    public List<University> getUniversities(int page, int size) {
        return null;
    }
}
