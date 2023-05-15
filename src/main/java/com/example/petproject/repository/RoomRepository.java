package com.example.petproject.repository;

import com.example.petproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // TODO: 16.05.2023 что с именами переменных случилось?
    List<Room> findAllByDormitoryIdAndAvailabilityForAccommodation(long dormitory_id, boolean availabilityForAccommodation);

    Optional<Room> findByNumberAndDormitoryId(String number, long dormitory_id);
}
