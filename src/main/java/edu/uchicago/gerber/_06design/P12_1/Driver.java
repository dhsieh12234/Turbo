package edu.uchicago.gerber._06design.P12_1;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.create_products();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Vending Machine!");

        boolean running = true;

        while (running) {
            // Display available products and costs
            vendingMachine.display_products();

            // Insert coins
            System.out.println("Please insert coins (penny, nickel, dime, quarter, dollar). Type 'done' when finished:");
            String coinInput;
            while (true) {
                System.out.print("Insert coin (or type 'done'): ");
                coinInput = scanner.nextLine().trim().toLowerCase();
                if (coinInput.equals("done")) {
                    break;
                }
                vendingMachine.insertCoin(coinInput);
            }

            // Choose a product
            System.out.println("Choose a product by entering its number (1-6), or type 'exit' to leave:");
            String choiceInput = scanner.nextLine().trim().toLowerCase();
            if (choiceInput.equals("exit")) {
                running = false;
                System.out.println("Thank you for using the vending machine!");
                continue;
            }

            try {
                int productChoice = Integer.parseInt(choiceInput);
                boolean success = vendingMachine.vend_out_product(productChoice);
                if (success) {
                    System.out.println("Product dispensed successfully!");
                } else {
                    System.out.println("Could not dispense product. Returning coins.");
                    vendingMachine.returnCoins();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid product number.");
            }

            System.out.println("Would you like to make another purchase? (yes/no)");
            String continueInput = scanner.nextLine().trim().toLowerCase();
            if (!continueInput.equals("yes")) {
                running = false;
            }
        }

        System.out.println("Finalizing transaction. Returning any remaining coins.");
        vendingMachine.returnCoins();
        System.out.println("Thank you for using the vending machine!");

        scanner.close();
    }
}
