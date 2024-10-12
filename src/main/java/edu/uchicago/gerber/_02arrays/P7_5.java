package edu.uchicago.gerber._02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P7_5 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter input file ");
        String inputFileName = console.nextLine();

        try {
            File inputFile = new File(inputFileName);
            Scanner fileScanner = new Scanner(inputFile);

            int num_rows = numberofRows(fileScanner);
            int num_fields = numberofFields(fileScanner, 0);
            String field = field(fileScanner, 0,1);
            System.out.println("Number of rows: " + num_rows);
            System.out.println("Number of fields: " + num_fields);
            System.out.println("Field: " + field);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
    public static int numberofRows(Scanner fileScanner) {
        int rowCount = 0;
        while (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
            rowCount++;
        }
        return rowCount;
    }
    public static int numberofFields(Scanner fileScanner, int row) {
        int currentRow = 0;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            currentRow++;
            if (currentRow == row) {
                String[] fields = line.split(",");
                return fields.length;
            }
        }
        return 0;
    }
    public static String field(Scanner fileScanner, int row, int column) {
        int currentRow = 0;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            currentRow++;
            if (currentRow == row) {
                String[] fields = line.split(",");
                if (column <= fields.length) {
                    return fields[column - 1];
                } else {
                    return "Field not found";
                }
            }
        }
        return "Row not found";
    }
}
