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

    // TODO: 23.06.2023 accommodationAvailability короче, а смысл +-тот же
    @Column(name = "available_for_accommodation")
    private boolean availabilityForAccommodation;

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

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }
    // TODO: 16.05.2023 как насчет служебных полей? даты создания и обновления
}
