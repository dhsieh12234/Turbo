package edu.uchicago.gerber._03objects.P8_5;

public class Driver {
    public static void main(String[] args) {
        SodaCan can = new SodaCan(10, 3);
        double surfaceArea = can.getSurfaceArea();
        double volume = can.getVolume();

        System.out.printf("Surface Area: %.2f square units%n", surfaceArea);
        System.out.printf("Volume: %.2f cubic units%n", volume);
    }
}
