package com.example.sparkdata;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.beans.Introspector;
import java.lang.reflect.Proxy;
import java.util.Set;

public class SparkApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        registerSparkBean(context);
        String packToScan = context.getEnvironment().getProperty("spark.packages-to-scan");

        Reflections scanner = new Reflections(packToScan);
        Set<Class<? extends SparkRepository>> sparkRepoInterfaces = scanner.getSubTypesOf(SparkRepository.class);
        sparkRepoInterfaces.forEach(sparkRepoInterface -> {
            Object golem = Proxy.newProxyInstance(sparkRepoInterface.getClassLoader(),
                    new Class[]{sparkRepoInterface},
                    ih );
            context.getBeanFactory()
                    .registerSingleton(Introspector.decapitalize(sparkRepoInterface.getSimpleName()), golem);
        });


    }

    private void registerSparkBean(ConfigurableApplicationContext context) {
        String appName = context.getEnvironment().getProperty("spark.app-name");
        SparkSession sparkSession = SparkSession.builder().appName(appName).master("local[*]").getOrCreate();

        JavaSparkContext sparkContext = new JavaSparkContext(sparkSession.sparkContext());
        context.getBeanFactory().registerSingleton("sparkContext", sparkContext);
        context.getBeanFactory().registerSingleton("sparkSession", sparkSession);
    }


}
