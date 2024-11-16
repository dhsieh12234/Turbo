package edu.uchicago.gerber._07streams.YodaSpeak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YodaSpeak {
    public String parseInput (String input) {
        List<String> words = Arrays.asList(input.split(" "));
        List<String> result = new ArrayList<>();
        for (String word : words) {
            result.add(0, word);
        }
        return String.join(" ", result);
    }
}
