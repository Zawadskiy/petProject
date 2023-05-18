package com.example.petproject.dto.request.modify;

public class StudentRequest {

    private String name;
    private String gender;
    private long university;
    private long dormitory;
    private long room;
    private boolean liveInDormitory;

    public String getGender() {
        return gender;
    }

    public long getUniversity() {
        return university;
    }

    public String getName() {
        return name;
    }

    public long getDormitory() {
        return dormitory;
    }

    public long getRoom() {
        return room;
    }

    public boolean isLiveInDormitory() {
        return liveInDormitory;
    }
}
