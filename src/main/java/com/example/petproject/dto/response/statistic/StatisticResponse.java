package com.example.petproject.dto.response.statistic;

import java.util.List;

public class StatisticResponse {

    private String universityName;
    private List<DormitoryResponse> dormitory;
    private int studentsCount;
    private long liveInDormitory;

    public List<DormitoryResponse> getDormitory() {
        return dormitory;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getLiveInDormitory() {
        return liveInDormitory;
    }

    public void setDormitory(List<DormitoryResponse> dormitory) {
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
