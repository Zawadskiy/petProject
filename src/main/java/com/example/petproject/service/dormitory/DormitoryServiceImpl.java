package com.example.petproject.service.dormitory;

import com.example.petproject.model.Dormitory;
import com.example.petproject.repository.DormitoryRepository;
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

    @Override
    public List<Dormitory> getDormitories(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Dormitory> dormitories = dormitoryRepository.findAll(pageRequest);

        return dormitories.getContent();
    }

    @Override
    public Dormitory update(Dormitory update) {

        Dormitory dormitory = findById(update.getId());

        dormitory.setUniversity(update.getUniversity());
        dormitory.setNumber(update.getNumber());
        //TODO изменение количества комнат при добавлении или удалении комнатіі
        dormitory.setNumberOfRooms(update.getNumberOfRooms());
        dormitory.setAvailabilityForAccommodation(update.isAvailabilityForAccommodation());

        return dormitoryRepository.save(dormitory);
    }

    @Override
    public Dormitory findById(long id) {
        return dormitoryRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Dormitory create(Dormitory dormitory) {
        return dormitoryRepository.save(dormitory);
    }

    @Override
    public void deleteById(long id) {
        dormitoryRepository.deleteById(id);
    }
}
