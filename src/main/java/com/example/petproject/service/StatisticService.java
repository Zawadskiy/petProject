package com.example.petproject.service;

import com.example.petproject.dto.response.statistic.StatisticResponse;

import java.util.List;

public interface StatisticService {
    List<StatisticResponse> getStatistic();
}
