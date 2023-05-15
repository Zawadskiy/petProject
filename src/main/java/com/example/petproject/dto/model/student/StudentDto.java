package com.example.petproject.dto.model.student;

public class StudentDto {

    private long id;
    private String name;
    private String gender;
    private String universityName;
    private String dormitoryNumber;
    private String roomNumber;
    private String admissionYear;
    private String deductionDate;
    private boolean liveInDormitory;

    // TODO: 16.05.2023 Боже, ломбок храни
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDormitoryNumber() {
        return dormitoryNumber;
    }

    public void setDormitoryNumber(String dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getDeductionDate() {
        return deductionDate;
    }

    public void setDeductionDate(String deductionDate) {
        this.deductionDate = deductionDate;
    }

    public boolean isLiveInDormitory() {
        return liveInDormitory;
    }

    public void setLiveInDormitory(boolean liveInDormitory) {
        this.liveInDormitory = liveInDormitory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
