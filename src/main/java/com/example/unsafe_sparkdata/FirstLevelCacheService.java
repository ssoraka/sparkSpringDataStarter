package com.example.unsafe_sparkdata;

import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstLevelCacheService {

    private Map<Class<?>, Dataset<Row>> model2DataSet = new HashMap<>();

    @Autowired
    private DataExtractorResolver extracterResolver;

    public List readDataFor(long ownerId, Class<?> modelClass, String pathToSource, String foreignKey, ConfigurableApplicationContext context) {
        if (!model2DataSet.containsKey(modelClass)) {
            DataExtractor extractor = extracterResolver.resolve(pathToSource);
            Dataset<Row> dataset = extractor.load(pathToSource, context);
            dataset.persist();
            model2DataSet.put(modelClass, dataset);
        }
        Encoder<?> encoder = Encoders.bean(modelClass);
        return model2DataSet.get(modelClass).filter(functions.col(foreignKey).equalTo(ownerId))
                .as(encoder).collectAsList();
    }
}
