package com.example.jpoint.sparkdata2021;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.ConfigurableApplicationContext;

public class JsonDataExtracter implements DataExtracter {
    @Override
    public Dataset<Row> load(String pathToData, ConfigurableApplicationContext context) {
        return context.getBean(SparkSession.class).read().json(pathToData);
    }
}
