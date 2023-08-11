package com.example.petproject.dto.request.modify;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StudentRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String gender;

    @NotNull
    private long university;

    @NotNull
    private long dormitory;

    @NotNull
    private long room;

    @NotNull
    private boolean liveInDormitory;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate admissionYear;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deductionDate;

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

    public LocalDate getAdmissionYear() {
        return admissionYear;
    }

    public LocalDate getDeductionDate() {
        return deductionDate;
    }
}
