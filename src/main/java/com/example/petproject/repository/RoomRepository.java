package com.example.petproject.repository;

import com.example.petproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByDormitoryIdAndAvailabilityForAccommodation(long dormitoryId, boolean availabilityForAccommodation);

    Optional<Room> findByIdAndDormitoryId(long roomId, long dormitoryId);
}
