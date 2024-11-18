package edu.uchicago.gerber._07streams.E19_16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the file: ");
        String filePath = scanner.nextLine();

        try {
            List<String> words = Files.lines(Paths.get(filePath))
                    .flatMap(line -> Stream.of(line.split("\\W+")))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toList());

            Map<Character, Double> averageLengthByInitial = WordAveraging.getAverageWordLengthByInitial(words);

            averageLengthByInitial.forEach((initial, avgLength) ->
                    System.out.println("Initial: " + initial + ", Average Length: " + avgLength));

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
