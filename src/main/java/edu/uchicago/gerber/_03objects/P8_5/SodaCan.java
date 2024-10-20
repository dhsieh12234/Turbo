package edu.uchicago.gerber._03objects.P8_5;

public class SodaCan {
    int height;
    int radius;
    public SodaCan(int height, int radius) {
        this.height = height;
        this.radius = radius;
    }
    public double SurfaceArea() {
        return 2 * Math.PI * this.radius * this.height + 2 * Math.PI * Math.pow(this.radius,2);
    }
    public double getVolume() {
        return Math.PI * Math.pow(this.radius, 2) * this.height;
    }
}
