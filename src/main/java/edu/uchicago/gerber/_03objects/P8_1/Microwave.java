package edu.uchicago.gerber._03objects.P8_1;

public class Microwave {
    private int time;
    private int powerLevel;

    public Microwave() {
        this.time = 0;
        this.powerLevel = 1;
    }
    public void increase_time() {
        this.time += 30;
        System.out.println("Added 30 seconds for time: " + this.time);
    }
    public void switchPowerLevel() {
        if (this.powerLevel == 1) {
            this.powerLevel = 2;
        } else {
            this.powerLevel = 1;
        }
        System.out.println("Power level switched to: " + powerLevel);
    }
    public void reset() {
        this.time = 0;
        this.powerLevel = 1;
        System.out.println("System Resetted to: Power Level " + powerLevel + "and time " + time);
    }
    public void start() {
        System.out.println("Cooking for " + this.time + " seconds at level " + this.powerLevel + ".");
    }
}
