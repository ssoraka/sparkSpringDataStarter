package com.example.unsafe_sparkdata;

import java.util.List;
import java.util.Set;

public interface TransformationSpider {
    SparkTransformation getTransformation(List<String> methodWords, Set<String> fieldNames);
}
