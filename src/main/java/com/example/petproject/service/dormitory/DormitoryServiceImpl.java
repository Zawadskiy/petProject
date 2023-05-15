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
        // TODO: 16.05.2023 одна строчка - одна точка.
        //  К слову, лучше уж дефолтный эксепшн тут. И мтеод референс
        return dormitoryRepository.findByNumber(number).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Dormitory> findAll() {
        return dormitoryRepository.findAll();
    }

    @Override
    // TODO: 16.05.2023 параметр логичнее было бы принимать, а не хардкодить.
    //  И как насчет criteria api?)
    public List<Dormitory> findAllAvailableForAccommodation() {
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
    // TODO: 16.05.2023 транзакции?
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
    // TODO: 16.05.2023 параметр удаления очевиден по параметру метода
    public void deleteById(long id) {
        dormitoryRepository.deleteById(id);
    }
}
