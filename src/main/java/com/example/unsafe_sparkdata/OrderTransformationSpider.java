package com.example.unsafe_sparkdata;

import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.List;
import java.util.Set;

@Component("orderBy")
public class OrderTransformationSpider implements TransformationSpider {
    @Override
    public Tuple2<SparkTransformation, List<String>> getTransformation(List<String> methodWords, Set<String> fieldNames) {
//        WordsMatcher.findAndRemoveMatchingPiecesIfExists()
        return new Tuple2<>(new SortTransformation(), null);
    }
}
