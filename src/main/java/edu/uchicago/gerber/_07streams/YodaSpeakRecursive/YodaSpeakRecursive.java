package edu.uchicago.gerber._07streams.YodaSpeakRecursive;

import edu.uchicago.gerber._07streams.E13_20.PayingBills;

import java.util.Arrays;
import java.util.List;

public class YodaSpeakRecursive {
    public String parseInputRecursive(String input) {
        List<String> words = Arrays.asList(input.split(" "));
        return reverseWords(words, words.size() - 1);
    }
    public String reverseWords(List<String> words, int index) {
        if (index < 0) {
            return "";
        }

        String word = words.get(index);
        if (index == 0) {
            return word;
        } else {
            return word + " " + reverseWords(words, index - 1);
        }
    }
}
