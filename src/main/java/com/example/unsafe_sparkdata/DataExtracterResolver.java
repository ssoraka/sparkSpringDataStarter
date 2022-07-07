package com.example.unsafe_sparkdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataExtracterResolver {

    @Autowired
    private Map<String, DataExtractor> extractorMap;

    DataExtractor resolve(String pathToData) {
        String fileExtension = pathToData.split("\\.")[1];
        return extractorMap.get(fileExtension);
    }
}
