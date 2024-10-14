package edu.uchicago.gerber._02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P7_5 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter input file ");
        String inputFileName = console.nextLine();

        try {
            File inputFile = new File(inputFileName);
            Scanner fileScanner = new Scanner(inputFile);

            List<String> rows = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                rows.add(fileScanner.nextLine());
            }

            int num_rows = numberofRows(rows);
            int num_fields = numberofFields(rows, 1);
            String field = field(rows, 1,2);
            System.out.println("Number of rows: " + num_rows);
            System.out.println("Number of fields: " + num_fields);
            System.out.println("Field: " + field);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
    public static int numberofRows(List<String> rows) {
        return rows.size();
    }
    public static int numberofFields(List<String> rows, int row) {
        if (row > 0 && row <= rows.size()) {
            String line = rows.get(row - 1);
            String[] fields = line.split(",");
            return fields.length;
        }
        return 0;
    }
    public static String field(List<String> rows, int row, int column) {
        if (row > 0 && row <= rows.size()) {
            String line = rows.get(row - 1);
            String[] fields = line.split(",");
            if (column > 0 && column <= fields.length) {
                return fields[column - 1].trim();
            }
        }
        return "Field not found";
    }
}
