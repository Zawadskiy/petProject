package com.example.petproject.dto.request.modify;

public class DormitoryRequest {

    private String number;
    private int numberOfRooms;
    private long university;
    private boolean availabilityForAccommodation;

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

    public long getUniversity() {
        return university;
    }

    public void setUniversity(long university) {
        this.university = university;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }
}
