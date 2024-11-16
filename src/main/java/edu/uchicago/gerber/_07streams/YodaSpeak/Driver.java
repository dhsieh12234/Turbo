package edu.uchicago.gerber._07streams.YodaSpeak;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a phrase:  ");
        String phrase = sc.nextLine();

        YodaSpeak yoda = new YodaSpeak();
        String result = yoda.parseInput(phrase);
        System.out.println(result);
    }
}
