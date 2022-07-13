package com.example.jpoint.sparkdata2021;

import com.example.unsafe_sparkdata.SparkRepository;

import java.util.List;

public interface CriminalRepo extends SparkRepository<Criminal> {
    List<Criminal> findByNumberGreaterThanOrderByNumber(int min);
    long findByNameContainsCount(String s);
    List<Criminal>  findByNameContains(String s);
}
