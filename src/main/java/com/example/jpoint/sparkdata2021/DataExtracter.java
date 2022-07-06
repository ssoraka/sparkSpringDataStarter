package com.example.jpoint.sparkdata2021;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.context.ConfigurableApplicationContext;

public interface DataExtracter {
    Dataset<Row> load(String pathToData, ConfigurableApplicationContext context);
}
