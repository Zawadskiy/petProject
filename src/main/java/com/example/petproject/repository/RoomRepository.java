package com.example.petproject.repository;

import com.example.petproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r from Room r where r.dormitory.id = :id")
    List<Room> findAllWhereDormitoryIs(@Param("id") long id);
}
