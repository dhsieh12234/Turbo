package edu.uchicago.gerber._02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class E7_4 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter input file ");
        String inputFileName = console.nextLine();

        System.out.print("Enter output file: ");
        String outputFileName = console.nextLine();

        try {
            File inputFile = new File(inputFileName);
            Scanner in = new Scanner(inputFile);

            PrintWriter out = new PrintWriter(outputFileName);

            int lineNumber = 1;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                out.println("/* " + lineNumber + " */ " + line);
                lineNumber++;
            }

            in.close();
            out.close();
            System.out.println("Process done");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
