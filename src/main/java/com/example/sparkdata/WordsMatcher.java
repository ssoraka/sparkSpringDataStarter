package com.example.sparkdata;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsMatcher {

    public static String findAndRemoveMatchingPiecesIfExists(Set<String> options, List<String> pieces) {
        StringBuilder match = new StringBuilder(pieces.remove(0));
        List<String> remainingOptions = options.stream()
                .filter(option -> option.toLowerCase().startsWith(match.toString()))
                .collect(Collectors.toList());

        if (remainingOptions.isEmpty()) {
            return "";
        }
        while (remainingOptions.size() > 1) {
            match.append(pieces.remove(0));
            remainingOptions.removeIf(option -> !option.toLowerCase().startsWith(match.toString()));
        }
        while (!remainingOptions.get(0).equalsIgnoreCase(match.toString())) {
            match.append(pieces.remove(0));
        }
        return Introspector.decapitalize(match.toString());
    }

    public static List<String> toWordsByJavaConversion(String name) {
        List<String> list = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
             if (Character.isUpperCase(name.charAt(i))) {
                 list.add(word.toString());
                 word.setLength(0);
                 word.append(Character.toLowerCase(name.charAt(i)));
             } else {
                 word.append(name.charAt(i));
             }
        }
        list.add(word.toString());
        return list;
    }
}
