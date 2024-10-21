package edu.uchicago.gerber._03objects.P8_5;

public class SodaCan {
    private int height;
    private int radius;
    public SodaCan(int height, int radius) {
        this.height = height;
        this.radius = radius;
    }
    public double getSurfaceArea() {
        return 2 * Math.PI * radius * height + 2 * Math.PI * Math.pow(radius,2);
    }
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }
}
