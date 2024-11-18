package edu.uchicago.gerber._07streams.E13_20;

import java.util.*;

import static edu.uchicago.gerber._07streams.E13_20.PayingBills.ListAllPrices;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter target cost: ");
        int n = sc.nextInt();

        List<List<Integer>> result = ListAllPrices(n);

        System.out.print("Do you want to see the number of combinations or the combinations themselves? (Enter 'number' or 'combinations'): ");
        sc.nextLine();
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("number")) {
            System.out.println("Number of combinations: " + result.size());
        } else if (choice.equalsIgnoreCase("combinations")) {
            System.out.println("The combinations are: ");
            for (List<Integer> combination : result) {
                System.out.println(combination);
            }
        } else {
            System.out.println("Invalid choice. Please enter 'number' or 'combinations'.");
        }
        sc.close();
    }
}
