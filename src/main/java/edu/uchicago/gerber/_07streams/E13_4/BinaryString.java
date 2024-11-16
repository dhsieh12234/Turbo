package edu.uchicago.gerber._07streams.E13_4;

import java.util.Scanner;

public class BinaryString {
    public String toBinaryString(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        if (n % 2 == 0) {
            return toBinaryString(n / 2)  + "0";
        } else {
            return toBinaryString(n / 2) + "1";
        }
    }
}
