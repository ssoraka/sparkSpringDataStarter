package com.example.unsafe_sparkdata;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("findBy")
public class FilterSpider implements TransformationSpider {

    Map<String, FilterTransformation> filterTransformationMap;

    @Override
    public SparkTransformation getTransformation(List<String> methodWords, Set<String> fieldNames) {
        String fieldName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, methodWords);
        String filterName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(filterTransformationMap.keySet(), methodWords);
        return filterTransformationMap.get(filterName);
    }
}
