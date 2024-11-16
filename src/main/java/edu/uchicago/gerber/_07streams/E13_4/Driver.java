package edu.uchicago.gerber._07streams.E13_4;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = sc.nextInt();

        BinaryString bs = new BinaryString();

        System.out.println(bs.toBinaryString(n));

        sc.close();
    }
}
