package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E2_4 {
    public static void main(String[] args) {
        Scanner obj1  = new Scanner(System.in);
        System.out.println("Enter Number 1");

        int num1 = obj1.nextInt();

        Scanner obj2  = new Scanner(System.in);
        System.out.println("Enter Number 2");

        int num2 = obj2.nextInt();


        int sum = num1 + num2;
        int difference = num1 - num2;
        int product = num1 * num2;
        double average = sum /2.0;
        int distance = Math.abs(difference);
        int maximum = Math.max(num1, num2);
        int minimum = Math.min(num1, num2);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.printf("Average: %.2f\n", average);
        System.out.println("Distance: " + distance);
        System.out.println("Maximum: " + maximum);
        System.out.println("Minimum: " + minimum);
    }
}
