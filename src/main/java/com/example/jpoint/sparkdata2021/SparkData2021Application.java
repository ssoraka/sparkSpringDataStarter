package com.example.jpoint.sparkdata2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SparkData2021Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SparkData2021Application.class);

//        SpeakerRepo speakerRepo = context.getBean(SpeakerRepo.class);
//        List<Speaker> speakers = speakerRepo.findByAgeBetween(20, 35);
//        speakers.forEach(System.out::println);

        CriminalRepo criminalRepo = context.getBean(CriminalRepo.class);
//        List<Criminal> criminals = criminalRepo.findByNumberGreaterThanOrderByNumber(20);
//        criminals.forEach(System.out::println);

        System.out.println(criminalRepo.findByNameContainsCount("ova"));

    }
}
