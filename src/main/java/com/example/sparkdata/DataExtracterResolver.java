package com.example.sparkdata;

import java.util.Map;

public class DataExtracterResolver {
    private Map<String, DataExtracter> extractorMap;

    DataExtracter resolve(String pathToData) {
        String fileExtension = pathToData.split("\\.")[1];
        return extractorMap.get(fileExtension);
    }
}
