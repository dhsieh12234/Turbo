package edu.uchicago.gerber._03objects.P8_7;

import java.util.Random;

public class ComboLock {
    int secret1;
    int secret2;
    int secret3;
    private boolean firstTurnCorrect;
    private boolean secondTurnCorrect;
    private boolean thirdTurnCorrect;
    private int turnCount;
    private int currentPosition;

    public ComboLock(int secret1, int secret2, int secret3) {
        Random rand = new Random();
        secret1 = rand.nextInt(39);
        secret2 = rand.nextInt(39);
        secret3 = rand.nextInt(39);
    }

    public void reset() {
        currentPosition = 0;
        turnCount = 0;
        firstTurnCorrect = false;
        secondTurnCorrect = false;
        thirdTurnCorrect = false;
    }
    public void turnLeft(int ticks) {
        currentPosition = (currentPosition + ticks) % 40;
        turnCount++;
        if (turnCount == 1 && currentPosition == secret1) {
            secondTurnCorrect = true;
        }
    }
    public void turnRight(int ticks) {
        currentPosition = (currentPosition - ticks + 40) % 40;
        turnCount++;
        if (turnCount == 2 && currentPosition == secret2) {
            firstTurnCorrect = true;
        } else if (turnCount == 3 && currentPosition == secret3) {
            thirdTurnCorrect = true;
        }
    }
    public boolean open() {
        return firstTurnCorrect && secondTurnCorrect && thirdTurnCorrect;
    }
}
