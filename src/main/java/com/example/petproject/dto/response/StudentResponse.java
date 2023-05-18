package com.example.petproject.dto.response;

public class StudentResponse {

    private long id;
    private String name;
    private String gender;
    private long university;
    private long dormitory;
    private long room;
    private String admissionYear;
    private String deductionDate;
    private boolean liveInDormitory;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUniversity(long university) {
        this.university = university;
    }

    public void setDormitory(long dormitory) {
        this.dormitory = dormitory;
    }

    public void setRoom(long room) {
        this.room = room;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public void setDeductionDate(String deductionDate) {
        this.deductionDate = deductionDate;
    }

    public void setLiveInDormitory(boolean liveInDormitory) {
        this.liveInDormitory = liveInDormitory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getUniversity() {
        return university;
    }

    public long getDormitory() {
        return dormitory;
    }

    public long getRoom() {
        return room;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public String getDeductionDate() {
        return deductionDate;
    }

    public boolean isLiveInDormitory() {
        return liveInDormitory;
    }
}
