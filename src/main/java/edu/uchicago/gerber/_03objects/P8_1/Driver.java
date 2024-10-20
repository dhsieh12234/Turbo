package edu.uchicago.gerber._03objects.P8_1;

public class Driver {
    public static void main(String[] args) {
        Microwave microwave = new Microwave();

        microwave.switchPowerLevel();
        microwave.increase_time();
        microwave.switchPowerLevel();
        microwave.start();
        microwave.reset();
        microwave.increase_time();
        microwave.start();
    }
}
