package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E4_1 {
    static public void main(String[] args) {
//        Problem A
        int total_a = 0;
        for (int i = 2; i <= 100; i += 2) {
            total_a += i;
        }
        System.out.println("Total for E4.2.a: " + total_a);

//        Problem B
        int total_b = 0;
        for (int i = 1; i <= 100; i++) {
            total_b += (int)Math.pow(i,2);
        }
        System.out.println("Total for E4.2.b: " + total_b);

//        Problem C
        int total_c = 0;
        for (int i = 0; i <= 20; i++) {
            total_c += (int)Math.pow(2,i);
        }
        System.out.println("Total for E4.2.c: " + total_c);

//        Problem D
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Number A:");
        int a = sc1.nextInt();

        Scanner sc2 = new Scanner(System.in);
        System.out.println("Number B:");
        int b = sc2.nextInt();

        if (b < a) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a % 2 == 0) {
            a += 1;
        }
        int total_d = 0;
        for (int i = a; i <= b; i += 2) {
            total_d += i;
        }
        System.out.println("Total for E4.2.d: " + total_d);

//        Problem E
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Number:");
        int number = sc3.nextInt();
        int total_e = 0;
        int i = 1;
        while (number != 0) {
            int remainder = number % 10;
            number = number / 10;

            if (remainder  % 2== 1) {
                total_e += remainder;
            }
        }
        System.out.println("Total for E4.2.e: " + total_e);
    }
}


