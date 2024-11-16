package edu.uchicago.gerber._07streams.P13_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class TelephoneNumber {
    private Set<String> validWords;

    public TelephoneNumber(String dictionaryFilePath) {
        validWords = new HashSet<>();
        try (Stream<String> lines = Files.lines(Path.of(dictionaryFilePath))) {
            lines.map(String::toLowerCase)
                    .forEach(validWords::add);
        } catch (IOException e) {
            System.err.println("Error loading the dictionary file: " + e.getMessage());
        }
    }

    public Map<Integer, String[]> telephoneNumber() {
        Map<Integer, String[]> map = new HashMap<>();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = 0;

        for (int i = 2; i <= 9; i++) {
            if (i == 7 || i == 9) {
                map.put(i, new String[]{
                        String.valueOf(letters.charAt(index)),
                        String.valueOf(letters.charAt(index + 1)),
                        String.valueOf(letters.charAt(index + 2)),
                        String.valueOf(letters.charAt(index + 3))
                });
                index += 4;
            } else {
                map.put(i, new String[]{
                        String.valueOf(letters.charAt(index)),
                        String.valueOf(letters.charAt(index + 1)),
                        String.valueOf(letters.charAt(index + 2))
                });
                index += 3;
            }
        }
        return map;
    }
    public List<Integer> numbersToDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(0, num % 10);
            num /= 10;
        }
        return digits;
    }
    public List<String> allTelephoneNumbers(int num) {
        Map<Integer, String[]> map = telephoneNumber();
        List<Integer> digits = numbersToDigits(num);
        List<String> result = new ArrayList<>();

        generateCombinations(map, digits, 0, "", result);

        return result;
    }
    private void generateCombinations(Map<Integer, String[]> map, List<Integer> digits, int index, String current, List<String> result) {
        if (index == digits.size()) {
            if (checkIfWord(current)) {
                result.add(current);
            }
            return;
        }

        int currentDigit = digits.get(index);
        if (map.containsKey(currentDigit)) {
            for (String letter : map.get(currentDigit)) {
                generateCombinations(map, digits, index + 1, current + letter, result);
            }
        }
    }
    public Map<String, List<String>> createNumberToWordsDictionary(List<String> numbers) {
        Map<String, List<String>> numberToWords = new HashMap<>();

        for (String number : numbers) {
            int num = Integer.parseInt(number);
            List<String> combinations = allTelephoneNumbers(num);
            numberToWords.put(number, combinations);
        }
        return numberToWords;
    }
    public List<String> createSequences(String input) {
        String[] parts = input.split("-");
        List<String> numbers = Arrays.asList(parts);

        // Create the dictionary with String keys
        Map<String, List<String>> numberToWords = createNumberToWordsDictionary(numbers);

        // Generate all combinations
        List<String> results = new ArrayList<>();
        generateSequencesStringKeys(numberToWords, numbers, 0, "", results);

        return results;
    }

    private void generateSequencesStringKeys(Map<String, List<String>> dictionary, List<String> numbers, int index, String currentCombination, List<String> results) {
        if (index == numbers.size()) {
            results.add(currentCombination.trim());
            return;
        }

        String currentNumber = numbers.get(index);

        List<String> words = dictionary.get(currentNumber);

        if (words != null) {
            for (String word : words) {
                generateSequencesStringKeys(dictionary, numbers, index + 1, currentCombination + " " + word, results);
            }
        }
    }

    private boolean checkIfWord(String word) {
        return validWords.contains(word.toLowerCase());
    }
}
