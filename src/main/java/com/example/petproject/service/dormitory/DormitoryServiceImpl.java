package com.example.petproject.service.dormitory;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.repository.DormitoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    @Autowired
    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Dormitory getDormitory(long id) {
        return dormitoryRepository.findByIdCustom(id);
    }

    @Override
    public Page<Dormitory> getAll(Pageable pageRequest) {
        return dormitoryRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public Dormitory update(Dormitory update) {
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
}
