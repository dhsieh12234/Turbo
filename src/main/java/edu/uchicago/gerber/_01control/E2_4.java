package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E2_4 {
    public static void main(String[] args) {
        Scanner obj1  = new Scanner(System.in);
        System.out.println("Enter Number 1");

        String num1 = obj1.nextLine();

        Scanner obj2  = new Scanner(System.in);
        System.out.println("Enter Number 2");

        String num2 = obj2.nextLine();

        int int_num1 = Integer.parseInt(num1);
        int int_num2 = Integer.parseInt(num2);

        int sum = int_num1 + int_num2;
        int difference = int_num1 - int_num2;
        int product = int_num1 * int_num2;
        int average = sum / 2;
        int distance = Math.abs(difference);
        int maximum = Math.max(int_num1, int_num2);
        int minimum = Math.min(int_num1, int_num2);

        System.out.println("Sum = " + sum);
        System.out.println("Difference = " + difference);
        System.out.println("Product = " + product);
        System.out.println("Average = " + average);
        System.out.println("Distance = " + distance);
        System.out.println("Maximum = " + maximum);
        System.out.println("Minimum = " + minimum);
    }
}
