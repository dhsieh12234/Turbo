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
    }
    public void switchPowerLevel() {
        if (this.powerLevel == 1) {
            this.powerLevel = 2;
        } else {
            this.powerLevel = 1;
        }
    }
    public void reset() {
        this.time = 0;
        this.powerLevel = 1;
    }
    public void start() {
        System.out.println("Cooking for " + this.time + " seconds at level " + this.powerLevel + ".");
    }
}
