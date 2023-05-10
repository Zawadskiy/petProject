package com.example.petproject.repository;

import com.example.petproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByDormitoryIdAndAvailabilityForAccommodation(long dormitory_id, boolean availabilityForAccommodation);

    Optional<Room> findByNumberAndDormitoryId(String number, long dormitory_id);
}
