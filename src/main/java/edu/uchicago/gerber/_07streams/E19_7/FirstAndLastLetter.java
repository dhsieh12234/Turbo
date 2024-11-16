package edu.uchicago.gerber._07streams.E19_7;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstAndLastLetter {
    public static List<String> toLetter(List<String> words) {
        return words.stream()
                .filter(word -> word.length() >= 2)
                .map(word -> word.charAt(0) + "..." + word.charAt(word.length() - 1))
                .collect(Collectors.toList());
    }
}
