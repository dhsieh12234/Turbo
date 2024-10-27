package edu.uchicago.gerber._04interfaces.P9_6;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        new Onetime(2024, 11, 15, "Doctor's appointment");
        new Daily(2024,8,12,"Morning exercise");
        new Monthly(2025, 3,28, "Pay rent");
        new Onetime(2024, 11, 27,"Conference");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();

        System.out.println("Appointments on " + year + "-" + month + "-" + day + ":");
        boolean found = false;
        for (Appointment appointment : Appointment.appointments) {
            if (appointment.occursOn(year, month, day)) {
                System.out.println(appointment.getDescription());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments on this date.");
        }

        scanner.close();
    }
}
