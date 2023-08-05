package com.example.petproject.repository;

import com.example.petproject.domain.Dormitory;

import java.util.List;

public interface DormitoryRepository extends CustomRepository<Dormitory, Long> {
    List<Dormitory> findAllByIdIn(List<Long> id);
}
