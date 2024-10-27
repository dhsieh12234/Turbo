package edu.uchicago.gerber._04interfaces.P9_1;

public class Driver {
    public static void main(String[] args) {
        Clock localClock = new Clock();
        System.out.println("Local Time: " + localClock.getTime());

        WorldClock worldClock = new WorldClock(3);
        System.out.println("World Clock Time (offset +3): " + worldClock.getTime());

        WorldClock negativeOffsetClock = new WorldClock(-5);
        System.out.println("World Clock Time (offset -5): " + negativeOffsetClock.getTime());
    }
}
