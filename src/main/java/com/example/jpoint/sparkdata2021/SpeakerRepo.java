package com.example.jpoint.sparkdata2021;

import com.example.unsafe_sparkdata.SparkRepository;

import java.util.List;

public interface SpeakerRepo extends SparkRepository<Speaker> {
    List<Speaker> findByAgeBetween(int min, int max);
    List<Speaker> findByAgeGreaterThan(int min);
}
