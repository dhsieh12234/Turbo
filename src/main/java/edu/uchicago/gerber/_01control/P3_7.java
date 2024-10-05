package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class P3_7 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter income amount to the nearest dollar amount");

        int income = sc.nextInt();
        int income_leftover;
        double tax = 0;

        if (income <= 50000) {
            tax = income * 0.01;
            System.out.println("Tax: " + tax);
            return;
        } else {
            tax = tax + 0.01 * 50000;
            income_leftover = income - 50000;
        }

        if (income_leftover <= 25000) {
            tax = tax + income_leftover * 0.02;
            System.out.println("Tax: " + tax);
            return;
        } else {
            tax = tax + 0.02 * 25000;
            income_leftover = income_leftover - 25000;
        }

        if (income_leftover <= 25000) {
            tax = tax + income_leftover * 0.03;
            System.out.println("Tax: " + tax);
            return;
        } else {
            tax = tax + 0.03 * 25000;
            income_leftover = income_leftover - 25000;
        }

        if (income_leftover <= 150000) {
            tax = tax + income_leftover * 0.04;
            System.out.println("Tax: " + tax);
            return;
        } else {
            tax = tax + 0.04 * 150000;
            income_leftover = income_leftover - 150000;
        }

        if (income_leftover <= 250000) {
            tax = tax + income_leftover * 0.05;
            System.out.println("Tax: " + tax);
            return;
        } else {
            tax = tax + 0.05 * 250000;
            income_leftover = income_leftover - 250000;
        }

        tax = tax + income_leftover * 0.06;

        System.out.println("Tax: $" + tax);




    }
}
