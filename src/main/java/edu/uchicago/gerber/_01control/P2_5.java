package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class P2_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter price: ");

        double price = sc.nextDouble();

        int dollars = (int) price;
        int cents = (int) ((price - dollars) * 100 + 0.5);

        System.out.println("dollar: " + dollars);
        System.out.println("cents: " + cents);

    }
}
