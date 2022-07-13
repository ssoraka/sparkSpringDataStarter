package com.example.unsafe_sparkdata;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("csv")
public class CsvDataExtractor implements DataExtractor {
    @Override
    public Dataset<Row> load(String pathToData, ConfigurableApplicationContext context) {

        return context.getBean(SparkSession.class).read().option("header", true)
                .option("inferSchema", true).csv(pathToData);
    }
}
