package com.example.petproject.service.university;

import com.example.petproject.model.Student;
import com.example.petproject.model.University;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public University getUniversity(String name) {
        return universityRepository.findByName(name).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public University getUniversity(long id) {
        return universityRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
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
        //TODO проверить каскадное удаление университета из общежитий и студентов) аналогично в других сервисах
    }

    @Override
    public List<University> getUniversities(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<University> universities = universityRepository.findAll(pageRequest);

        return universities.getContent();
    }
}
