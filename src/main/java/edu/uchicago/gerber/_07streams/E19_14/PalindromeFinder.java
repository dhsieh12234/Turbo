package edu.uchicago.gerber._07streams.E19_14;

import java.util.List;
import java.util.Optional;

public class PalindromeFinder {
    public static boolean isPalindrome(String word) {
        String lowerCaseWord = word.toLowerCase();
        return lowerCaseWord.equals(new StringBuilder(lowerCaseWord).reverse().toString());
    }

    public static Optional<String> findPalindrome(List<String> words) {
        return words.parallelStream()
                .filter(word -> word.length() >= 5)
                .filter(PalindromeFinder::isPalindrome)
                .findAny();
    }
}
