package edu.uchicago.gerber._04interfaces.E9_17;

import edu.uchicago.gerber._03objects.P8_5.SodaCan;

public class SodaCan_v2 implements Measurable {
    private int height;
    private int radius;

    public SodaCan_v2(int height, int radius) {
        this.height = height;
        this.radius = radius;
    }
    public double getSurfaceArea() {
        return 2 * Math.PI * radius * height + 2 * Math.PI * Math.pow(radius,2);
    }
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }
    public double getMeasure() {
        return getSurfaceArea();
    }

}
