package com.example.petproject.dto.request.modify;

import jakarta.validation.constraints.NotNull;

public class DormitoryRequest {
    // TODO: 23.06.2023 для строк - советую нотЕмпти/нотБланк
    @NotNull
    private String number;

    @NotNull
    private int numberOfRooms;

    @NotNull
    private long university;

    @NotNull
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
