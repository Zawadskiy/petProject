package com.example.petproject.dto.response;

import java.util.ArrayList;
import java.util.List;

public class StatisticResponse {

    private String universityName;
    private List<DormitoryStatisticResponse> dormitoryStatistic = new ArrayList<>();
    private int studentsCount;
    private long liveInDormitory;

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setDormitoryStatistic(List<DormitoryStatisticResponse> dormitoryStatistic) {
        this.dormitoryStatistic = dormitoryStatistic;
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

    public List<DormitoryStatisticResponse> getDormitoryStatistic() {
        return dormitoryStatistic;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getLiveInDormitory() {
        return liveInDormitory;
    }
}
