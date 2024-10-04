package edu.uchicago.gerber._01control;

import java.util.Scanner;

public class E3_14 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter month (1-12):");
        int month = sc.nextInt();
        System.out.println("Enter day (1-31):");
        int day = sc.nextInt();

        String season = "";

        if (month >= 1 && month <= 3) {
            season = "Winter";
        } else if (month >= 4 && month <= 6) {
            season = "Spring";
        } else if (month >= 7 && month <= 9) {
            season = "Summer";
        } else if (month >= 10 && month <= 12) {
            season = "Fall";
        } else {
            System.out.println("Invalid month");
            return;
        }

        if (month % 3 == 0 && day >= 21) {
            if (season.equals("Winter")) {
                season = "Spring";
            } else if (season.equals("Spring")) {
                season = "Summer";
            } else if (season.equals("Summer")) {
                season = "Fall";
            } else {
                season = "Winter";
            }
        }
        System.out.println("Season " + season);

    }
}
