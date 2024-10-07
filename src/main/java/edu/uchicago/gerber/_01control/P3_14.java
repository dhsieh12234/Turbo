package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class P3_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a year: ");
        int year = sc.nextInt();

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " is a leap year");
            return;
        }
        System.out.println(year + " is not a leap year");
    }
}

