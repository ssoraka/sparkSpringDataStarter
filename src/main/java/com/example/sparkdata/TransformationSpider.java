package com.example.sparkdata;

import java.lang.reflect.Method;

public interface TransformationSpider {
    SparkTransformation getTransformation(Method[] methods);
}
