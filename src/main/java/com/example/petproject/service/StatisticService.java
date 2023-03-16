package com.example.petproject.service;

import com.example.petproject.dto.response.statistic.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getStatistic();
}
