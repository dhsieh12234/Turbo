package edu.uchicago.gerber._07streams.E19_14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the file path
        System.out.print("Enter the path to the file: ");
        String filePath = scanner.nextLine();

        try {
            List<String> words = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));

            Optional<String> palindrome = PalindromeFinder.findPalindrome(words);

            palindrome.ifPresentOrElse(
                    word -> System.out.println("Found palindrome: " + word),
                    () -> System.out.println("No palindrome found")
            );

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
