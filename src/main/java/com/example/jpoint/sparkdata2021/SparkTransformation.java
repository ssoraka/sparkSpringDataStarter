package com.example.jpoint.sparkdata2021;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface SparkTransformation {
    Dataset<Row> transform(Dataset<Row> dataset);
}
