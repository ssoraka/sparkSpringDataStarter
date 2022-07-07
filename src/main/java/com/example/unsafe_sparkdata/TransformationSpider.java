package com.example.unsafe_sparkdata;

import scala.Tuple2;

import java.util.List;
import java.util.Set;

public interface TransformationSpider {
    Tuple2<SparkTransformation, List<String>> getTransformation(List<String> methodWords, Set<String> fieldNames);
}
