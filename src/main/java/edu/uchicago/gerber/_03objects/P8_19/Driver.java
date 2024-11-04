package edu.uchicago.gerber._03objects.P8_19;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input from user
        System.out.print("Enter the initial x-position: ");
        double initialX = scanner.nextDouble();

        System.out.print("Enter the firing angle (degrees): ");
        double angle = scanner.nextDouble();

        System.out.print("Enter the initial velocity: ");
        double velocity = scanner.nextDouble();

        // Create the Cannonball object and shoot
        Cannonball cannonball = new Cannonball(initialX);
        cannonball.shoot(angle, velocity);
    }
}
