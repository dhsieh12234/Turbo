package edu.uchicago.gerber._02arrays;

public class P5_24 {
    public static void main(String[] args) {
        String roman = "MCMLXXVIII";
        int result = roman_to_int(roman);
        System.out.println("The integer value of " + roman + " is: " + result);
    }
    public static int roman_str_to_int(char s) {
        switch (s) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:
                System.out.println("invalid character: " + s);
                return -1;
        }
    }
    public static int roman_to_int(String s) {
        int total = 0;
        int i = 0;
        while (i < s.length()) {
            if (i == s.length() - 1 || roman_str_to_int(s.charAt(i)) >= roman_str_to_int(s.charAt(i + 1))) {
                int value = roman_str_to_int(s.charAt(i));
                total = total + value;
                i++;
            } else {
                int difference = roman_str_to_int(s.charAt(i + 1)) - roman_str_to_int(s.charAt(i));
                total = total + difference;
                i += 2;
            }
        }
        return total;
    }
}
