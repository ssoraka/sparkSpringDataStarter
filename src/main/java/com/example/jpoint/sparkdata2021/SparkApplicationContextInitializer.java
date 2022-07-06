package com.example.jpoint.sparkdata2021;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SparkApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        registerSparkBean(context);
    }

    private void registerSparkBean(ConfigurableApplicationContext context) {
        String appName = context.getEnvironment().getProperty("spark.app-name");
        SparkSession orCreate = SparkSession.builder().appName(appName).master("local[*]").getOrCreate();
    }


}
