package com.example.petproject.service.dormitory;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.repository.DormitoryRepository;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository, UniversityRepository universityRepository) {
        this.dormitoryRepository = dormitoryRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Dormitory getDormitory(long id) {
        return dormitoryRepository.findByIdEx(id);
    }

    @Override
    public Page<Dormitory> getAll(Pageable pageRequest) {
        return dormitoryRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public Dormitory update(Dormitory update) {

        Dormitory dormitory = dormitoryRepository.findByIdEx(update.getId());
        dormitory.setNumber(update.getNumber());
        dormitory.setUniversity(universityRepository.findByIdEx(update.getUniversity().getId()));
        dormitory.setAccommodationAvailability(update.isAccommodationAvailability());
        dormitory.setNumberOfRooms(update.getNumberOfRooms());

        return dormitoryRepository.save(update);
    }

    @Override
    @Transactional
    public Dormitory create(Dormitory dormitory) {
        return dormitoryRepository.save(dormitory);
    }

    @Override
    @Transactional
    public void delete(long id) {
        dormitoryRepository.deleteById(id);
    }

    @Override
    public List<Dormitory> getAllIn(List<Long> id) {
        return dormitoryRepository.findAllById(id);
    }
}
