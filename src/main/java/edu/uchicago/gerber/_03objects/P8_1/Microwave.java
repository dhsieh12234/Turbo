package edu.uchicago.gerber._03objects.P8_1;

public class Microwave {
    private int time;
    private int powerLevel;

    public Microwave() {
        time = 0;
        powerLevel = 1;
    }
    public void increase_time() {
        time += 30;
        System.out.println("Added 30 seconds for time: " + time);
    }
    public void switchPowerLevel() {
        if (powerLevel == 1) {
            powerLevel = 2;
        } else {
            powerLevel = 1;
        }
        System.out.println("Power level switched to: " + powerLevel);
    }
    public void reset() {
        time = 0;
        powerLevel = 1;
        System.out.println("System Reset to: Power Level " + powerLevel + " and time " + time);
    }
    public void start() {
        System.out.println("Cooking for " + time + " seconds at level " + powerLevel + ".");
    }
}
