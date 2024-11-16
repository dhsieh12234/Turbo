package edu.uchicago.gerber._07streams.E19_7;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter words separated by spaces: ");
        String input = scanner.nextLine();

        List<String> words = Arrays.asList(input.split(" "));

        List<String> transformedWords = FirstAndLastLetter.toLetter(words);

        transformedWords.forEach(System.out::println);

        scanner.close();
    }
}
