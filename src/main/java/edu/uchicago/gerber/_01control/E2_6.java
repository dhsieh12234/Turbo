package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E2_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter measurement in meters: ");
        String meters = sc.nextLine();

        double meters_double = Double.parseDouble(meters);

        double miles = Math.floor(meters_double / 1609.34);
        int miles_int = (int) miles;
        meters_double = meters_double - miles * 1609.34;

        double feet = Math.floor(meters_double / 0.3048);
        int feet_int = (int) feet;
        meters_double = meters_double - feet * 0.3048;

        double inches = Math.floor(meters_double / 0.0254);
        int inches_int = (int) inches;

        System.out.println(meters + " meters equals to");
        System.out.println("Miles = " + miles_int);
        System.out.println("Feet = " + feet_int);
        System.out.println("Inches = " + inches_int);
//        System.out.println(meters_double);



    }
}
