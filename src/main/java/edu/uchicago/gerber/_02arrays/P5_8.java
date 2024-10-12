package edu.uchicago.gerber._02arrays;

public class P5_8 {
    public static void main(String[] args) {
        int year = 2001;
        System.out.println("Is " + year + " a leap year? " + isLeapYear(year));
    }
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }
}
