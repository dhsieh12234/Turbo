package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class P3_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an positive integer: ");
        int n = sc.nextInt();

        int remainder;

        int thousands = n / 1000;
        remainder = n % 1000;
        int hundreds = remainder / 100;
        remainder = remainder - hundreds * 100;
        int tens = remainder / 10;
        remainder =  remainder - tens * 10;
        int ones = remainder;

//        For thousands
        String thousands_str;
        if (thousands == 1) {
            thousands_str = "M";
        } else if (thousands == 2) {
            thousands_str = "MM";
        } else if (thousands == 3) {
            thousands_str = "MMM";
        } else {
            thousands_str = "";
        }

        // For Hundreds
        String hundreds_str;
        switch (hundreds) {
            case 1: hundreds_str = "C"; break;
            case 2: hundreds_str = "CC"; break;
            case 3: hundreds_str = "CCC"; break;
            case 4: hundreds_str = "CD"; break;
            case 5: hundreds_str = "D"; break;
            case 6: hundreds_str = "DC"; break;
            case 7: hundreds_str = "DCC"; break;
            case 8: hundreds_str = "DCCC"; break;
            case 9: hundreds_str = "CM"; break;
            default: hundreds_str = ""; break;
        }

        // For Tens
        String tens_str;
        switch (tens) {
            case 1: tens_str = "X"; break;
            case 2: tens_str = "XX"; break;
            case 3: tens_str = "XXX"; break;
            case 4: tens_str = "XL"; break;
            case 5: tens_str = "L"; break;
            case 6: tens_str = "LX"; break;
            case 7: tens_str = "LXX"; break;
            case 8: tens_str = "LXXX"; break;
            case 9: tens_str = "XC"; break;
            default: tens_str = ""; break;
        }

        // For Ones
        String ones_str;
        switch (ones) {
            case 1: ones_str = "I"; break;
            case 2: ones_str = "II"; break;
            case 3: ones_str = "III"; break;
            case 4: ones_str = "IV"; break;
            case 5: ones_str = "V"; break;
            case 6: ones_str = "VI"; break;
            case 7: ones_str = "VII"; break;
            case 8: ones_str = "VIII"; break;
            case 9: ones_str = "IX"; break;
            default: ones_str = ""; break;
        }

        System.out.println(thousands_str + hundreds_str + tens_str + ones_str);
    }
}
