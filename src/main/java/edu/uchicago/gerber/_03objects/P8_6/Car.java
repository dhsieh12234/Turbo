package edu.uchicago.gerber._03objects.P8_6;

public class Car {
    private double fuel_level;
    private final int milesPerGallon;

    public Car(int milesPerGallon) {
        this.fuel_level = 0.0;
        this.milesPerGallon = milesPerGallon;
    }
    public void drive(int miles) {
        double gallonsUsed = (double) miles / milesPerGallon;
        if (gallonsUsed <= fuel_level) {
            fuel_level -= gallonsUsed;
        } else {
            System.out.println("Not enough fuel to drive " + miles + " miles.");
        }
    }
    public void addGas(double gallon) {
        this.fuel_level += gallon;
    }
    public double getGasLevel() {
        return this.fuel_level;
    }
}
