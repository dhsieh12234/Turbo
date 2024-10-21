package edu.uchicago.gerber._03objects.P8_7;

import java.util.Random;

public class ComboLock {
    private int secret1;
    private int secret2;
    private int secret3;

    private int dial;
    private int step;

    public ComboLock(int secret1, int secret2, int secret3) {
        this.secret1 = secret1;
        this.secret2 = secret2;
        this.secret3 = secret3;
        reset();
    }

    public void reset() {
        dial = 0;
        step = 0;
    }
    public void turnLeft(int ticks) {
        dial = (dial - ticks + 40) % 40;
        if (step == 1 && dial == secret2) {
            step++;
        }
    }
    public void turnRight(int ticks) {
        dial = (dial + ticks) % 40;
        if (step == 0 && dial == secret1) {
            step++;
        } else if (step == 2 && dial == secret3) {
            step++;
        }
    }
    public boolean open() {
        return step == 3;
    }
}
