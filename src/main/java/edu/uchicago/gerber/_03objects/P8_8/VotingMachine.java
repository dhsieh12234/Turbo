package edu.uchicago.gerber._03objects.P8_8;

public class VotingMachine {
    private int democrat_votes;
    private int republican_votes;

    public VotingMachine() {
        this.democrat_votes = 0;
        this.republican_votes = 0;
    }
    public void voteForDemocrat() {
        democrat_votes++;
    }
    public void voteForRepublican() {
        republican_votes++;
    }
    public void clear() {
        democrat_votes = 0;
        republican_votes = 0;
    }
    public int getDemocratVotes() {
        return this.democrat_votes;
    }
    public int getRepublicanVotes() {
        return this.republican_votes;
    }
    public void print_votes() {
        System.out.println("Republicans: " + getRepublicanVotes());
        System.out.println("Democrats:" + getDemocratVotes());
    }
}
