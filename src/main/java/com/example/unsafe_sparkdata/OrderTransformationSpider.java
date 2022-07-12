package com.example.unsafe_sparkdata;

import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("orderBy")
public class OrderTransformationSpider implements TransformationSpider {
    @Override
    public Tuple2<SparkTransformation, List<String>> getTransformation(List<String> methodWords, Set<String> fieldNames) {
        String sortColumn = WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, methodWords);
        List<String> additional = new ArrayList<>();
        additional.add(sortColumn);
        while (!methodWords.isEmpty() && methodWords.get(0).equals("and")) {
            methodWords.remove(0);
            additional.add(WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, methodWords));
        }

        return new Tuple2<>(new SortTransformation(), additional);
    }
}
