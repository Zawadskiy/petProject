package com.example.petproject.service.dormitory;

import com.example.petproject.model.Dormitory;
import com.example.petproject.repository.DormitoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    @Autowired
    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Dormitory getDormitory(long id, long universityId) {
        return dormitoryRepository.findByIdAndUniversityId(id, universityId)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Dormitory getDormitory(long id) {
        return dormitoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    // TODO: 16.05.2023 параметр логичнее было бы принимать, а не хардкодить.
    //  И как насчет criteria api?)
    public List<Dormitory> getAllAvailableForAccommodation() {
        return dormitoryRepository.findAllByAvailabilityForAccommodation(true);
    }

    @Override
    // TODO: 16.05.2023 почему не Page в возвращаемом типе?
    public List<Dormitory> getDormitories(int page, int size) {
// TODO: 16.05.2023 Зачем вручную создавать?
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Dormitory> dormitories = dormitoryRepository.findAll(pageRequest);

        return dormitories.getContent();
    }

    @Override
    @Transactional
    public Dormitory update(Dormitory update) {
        return dormitoryRepository.save(update);
    }

    @Override
    public Dormitory create(Dormitory dormitory) {
        return dormitoryRepository.save(dormitory);
    }

    @Override
    public void delete(long id) {
        dormitoryRepository.deleteById(id);
    }
}
