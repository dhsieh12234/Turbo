package edu.uchicago.gerber._04interfaces.E9_17;

public class Driver {
    public static void main(String[] args) {
        SodaCan_v2[] cans = {
                new SodaCan_v2(10, 3),
                new SodaCan_v2(12, 4),
                new SodaCan_v2(8, 2)
        };

        double averageSurfaceArea = average(cans);
        System.out.println("Average Surface Area: " + averageSurfaceArea);
    }

    public static double average(Measurable[] objects) {
        double sum = 0;
        for (Measurable obj : objects) {
            sum += obj.getMeasure();
        }
        return sum / objects.length;
    }
}
