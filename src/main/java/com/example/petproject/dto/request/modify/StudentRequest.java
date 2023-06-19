package com.example.petproject.dto.request.modify;

import jakarta.validation.constraints.NotNull;

public class StudentRequest {

    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private long university;

    @NotNull
    private long dormitory;

    @NotNull
    private long room;

    @NotNull
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
