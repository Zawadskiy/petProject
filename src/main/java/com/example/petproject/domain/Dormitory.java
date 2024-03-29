package com.example.petproject.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "dormitory")
public class Dormitory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;

    private int numberOfRooms;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Column(name = "accommodation_availability")
    private boolean accommodationAvailability;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public boolean isAccommodationAvailability() {
        return accommodationAvailability;
    }

    public void setAccommodationAvailability(boolean accommodationAvailability) {
        this.accommodationAvailability = accommodationAvailability;
    }
    // TODO: 16.05.2023 как насчет служебных полей? даты создания и обновления
}
