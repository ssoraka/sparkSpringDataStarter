package com.example.jpoint.sparkdata2021;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface Finalizer {
    Object doAction(Dataset<Row> dataset);
}
