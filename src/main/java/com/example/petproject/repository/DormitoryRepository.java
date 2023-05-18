package com.example.petproject.repository;

import com.example.petproject.model.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {
    Optional<Dormitory> findByIdAndUniversityId(long id, long universityId);

    Optional<Dormitory> findById(long id);

    List<Dormitory> findAllByAvailabilityForAccommodation(boolean availabilityForAccommodation);
}
