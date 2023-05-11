package com.example.petproject.dto.model.room;

public class RoomDto {

    private long id;

    private String number;
    private String dormitoryNumber;
    private int capacity;
    private boolean availabilityForAccommodation;
    private String residentsGender;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public String getResidentsGender() {
        return residentsGender;
    }

    public void setResidentsGender(String residentsGender) {
        this.residentsGender = residentsGender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDormitoryNumber() {
        return dormitoryNumber;
    }

    public void setDormitoryNumber(String dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }
}
