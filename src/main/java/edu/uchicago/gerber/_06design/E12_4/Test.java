package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;

public class Test {
    private int level;
    private int tries;
    // keeps track what level it is
    public Test (){
        this.level = 1;
        this.tries = 1;
    }
    // resets the tries
    public boolean count_tries() {
        tries++;
        if (tries >= 2) {
            tries = 0;
            return true;
        }
        return false;
    }
    //reset the level of problem and restarts the player at 1
    public void fail() {
        level = 1;
        tries = 0;
    }

    //keeps track of the play
    public String play () {
        Scanner console = new Scanner(System.in);
        while (level <= 3) {
            System.out.println("Level " + level + " Start!");
            int score = 0;
            Problem problem = new Problem(level);
            while (score < 5) {

                System.out.println(problem.getQuestion());
                int answer = console.nextInt();
                if (answer == problem.getAnswer()) {
                    System.out.println("Correct!");
                    score++;
                    tries = 0;
                    if (score < 5) {
                        problem = new Problem(level);
                    }
                } else {
                    System.out.println("Incorrect. Try again.");
                    if (count_tries()) {
                        System.out.println("You ran out of tries. Restarting level " + level + ".");
                        score = 0;
                        break;
                    }
                }
            }
            if (score == 5) {
                System.out.println("Level " + level + " completed!");
                level++;
            }
        }
        level = 1;
        tries = 0;
        console.close();
        return "You finished all levels!";
    }
}
