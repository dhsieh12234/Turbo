package edu.uchicago.gerber._06design.P12_1;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private String coin;
    private final List<Product> products = new ArrayList<>();
    private double totalInsertedAmount;

    public VendingMachine() {
        totalInsertedAmount = 0;
        create_products();
    }

    //translate coin to amount
    public void insertCoin(String coin) {
        switch (coin.toLowerCase()) {
            case "penny":
                totalInsertedAmount += 0.01;
                break;
            case "nickel":
                totalInsertedAmount += 0.05;
                break;
            case "dime":
                totalInsertedAmount += 0.10;
                break;
            case "quarter":
                totalInsertedAmount += 0.25;
                break;
            case "dollar":
                totalInsertedAmount += 1.00;
                break;
            default:
                System.out.println("Invalid coin!");
                return;
        }
        System.out.printf("Inserted %s. Total amount: $%.2f%n", coin, totalInsertedAmount);
    }

    //create products for vending machine
    public void create_products() {
        if (products.isEmpty()) {
            products.add(new Product("Cookie", 2.00));
            products.add(new Product("Coke", 1.50));
            products.add(new Product("Juice", 2.25));
            products.add(new Product("PopTarts", 1.75));
            products.add(new Product("Chips", 0.75));
            products.add(new Product("Water", 1.00));
        }
    }
    //Display Products cost and amount
    public void display_products() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).display());
        }
    }

    //track coin amount
    public void returnCoins() {
        if (totalInsertedAmount > 0) {
            System.out.printf("Returning $%.2f in coins.%n", totalInsertedAmount);
            totalInsertedAmount = 0.0;
        } else {
            System.out.println("No money to return.");
        }
    }

    //give product to person
    public boolean vend_out_product(int productNumber) {
        if (productNumber < 1 || productNumber > products.size()) {
            return false;
        }
        Product selectedProduct = products.get(productNumber - 1);
        if (!selectedProduct.isInStock()) {
            return false;
        }

        if (totalInsertedAmount >= selectedProduct.getCost()) {
            selectedProduct.buyProduct();
            totalInsertedAmount -= selectedProduct.getCost();
            return true;
        } else {
            return false;
        }
    }

}
