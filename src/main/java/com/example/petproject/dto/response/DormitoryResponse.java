package com.example.petproject.dto.response;

public class DormitoryResponse {

    private long id;
    private String number;
    private int numberOfRooms;

    private long university;

    private boolean availabilityForAccommodation;

    public void setId(long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setUniversity(long university) {
        this.university = university;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public long getUniversity() {
        return university;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }
}
