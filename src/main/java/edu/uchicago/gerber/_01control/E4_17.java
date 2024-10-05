package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E4_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int n = sc.nextInt();

        String result = "";
        int length = 1;

        while (n != 0) {
            int remainder = n % 2;
            n = n /2;

            if (length == 4) {
                result = " " + remainder + result;
                length = 1;
            } else {
                result = remainder + result;
                length++;
            }
        }

        System.out.println(result);
    }
}
