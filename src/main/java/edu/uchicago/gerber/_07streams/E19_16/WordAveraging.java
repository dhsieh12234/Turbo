package edu.uchicago.gerber._07streams.E19_16;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordAveraging {
    public static Map<Character, Double> getAverageWordLengthByInitial(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(
                        word -> Character.toLowerCase(word.charAt(0)),
                        Collectors.averagingInt(String::length)
                ));
    }
}
