package com.example.unsafe_sparkdata;

import lombok.Builder;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Builder
public class SparkInvocationHandler implements InvocationHandler {

    private Class<?> modelClass;
    private String pathToData;
    private DataExtractor dataExtractor;
    private Map<Method, List<SparkTransformation>> transformationChain;
    private Map<Method, Finalizer> finalizerMap;
    private ConfigurableApplicationContext context;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Dataset<Row> dataset = dataExtractor.load(pathToData, context);

        for (SparkTransformation transformation : transformationChain.get(method)) {
            dataset = transformation.transform(dataset);
        }


        return finalizerMap.get(method).doAction(dataset, modelClass);
    }
}
