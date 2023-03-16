package com.example.petproject.dto.response.statistic;

import java.util.List;

public class Statistic {

    private String universityName;
    private List<DormitoryDTO> dormitory;
    private int studentsCount;
    private long liveInDormitory;

    public List<DormitoryDTO> getDormitory() {
        return dormitory;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getLiveInDormitory() {
        return liveInDormitory;
    }

    public void setDormitory(List<DormitoryDTO> dormitory) {
        this.dormitory = dormitory;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setLiveInDormitory(long liveInDormitory) {
        this.liveInDormitory = liveInDormitory;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
