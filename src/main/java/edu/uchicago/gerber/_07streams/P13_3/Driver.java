package edu.uchicago.gerber._07streams.P13_3;

import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String dictionaryPath = "/Users/derekhsieh/IdeaProjects/projava-dhsieh12234/src/main/java/edu/uchicago/gerber/_07streams/P13_3/Dictionary";

        TelephoneNumber tn = new TelephoneNumber(dictionaryPath);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sequence of numbers separated by '-' (e.g., 263-346-5282): ");
        String input = scanner.nextLine();

        String[] parts = input.split("-");
        boolean invalidInput = false;

        for (String part : parts) {
            if (!isValidNumber(part)) {
                invalidInput = true;
                System.out.println("Invalid input! Each part must be numeric and contain only digits 2-9.");
                break;
            }
        }

        if (!invalidInput) {
            List<String> results = tn.createSequences(input);

            System.out.println("All possible combinations:");
            for (String result : results) {
                System.out.println(result);
            }
        }

        scanner.close();
    }

    private static boolean isValidNumber(String number) {
        return number.matches("[2-9]+");
    }
}
