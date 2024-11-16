package edu.uchicago.gerber._07streams.YodaSpeakRecursive;

import edu.uchicago.gerber._07streams.YodaSpeak.YodaSpeak;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a phrase:  ");
        String phrase = sc.nextLine();

        YodaSpeakRecursive yoda = new YodaSpeakRecursive();
        String result = yoda.parseInputRecursive(phrase);
        System.out.println(result);
    }
}
