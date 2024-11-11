package edu.uchicago.gerber._06design.E12_4;
import java.util.Random;

public class Problem {
    private int level;
    private int num1;
    private int num2;
    //needs what level question, and then generates two number
    public Problem(int level) {
        this.level = level;
        generateNumbers();
    }
    private void generateNumbers() {
        Random rand = new Random();
        if (level == 1) {
            num1 = rand.nextInt(10);
            num2 = rand.nextInt(10 - num1 + 1);
        } else if (level == 2) {
            num1 = rand.nextInt(10);
            num2 = rand.nextInt(10);
        } else if (level == 3) {
            num1 = rand.nextInt(10);
            num2 = rand.nextInt(10);
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
        }
    }
    public String getQuestion () {
        if (level == 1 || level == 2) {
            return "What is the sum of " + num1 + " and " + num2 + "?";
        } else if (level == 3) {
            return "What is the difference between " + num1 + " and " + num2 + "?";
        }
        return "Invalid Level";
    }

    //answer to the question
    public int getAnswer () {
        if (level == 1 || level == 2) {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }
}
