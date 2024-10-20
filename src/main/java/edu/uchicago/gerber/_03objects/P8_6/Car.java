package edu.uchicago.gerber._03objects.P8_6;

public class Car {
    private double fuel_level;
    private final int milespergallon;

    public Car(int milespergallon) {
        this.fuel_level = 0.0;
        this.milespergallon = milespergallon;
    }
    public void drive(int miles) {
        double gallonsUsed = (double) miles / milespergallon;
        if (gallonsUsed <= fuel_level) {
            fuel_level -= gallonsUsed;
        } else {
            System.out.println("Not enough fuel to drive " + miles + " miles.");
        }
    }
    public void addGas(double gallon) {
        this.fuel_level += gallon;
    }
    public int getGasLevel(int fuel_level) {
        return fuel_level;
    }
}
