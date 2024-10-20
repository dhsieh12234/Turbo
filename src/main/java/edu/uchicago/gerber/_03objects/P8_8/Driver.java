package edu.uchicago.gerber._03objects.P8_8;

public class Driver {
    public static void main(String[] args) {
        VotingMachine machine = new VotingMachine();

        machine.voteForDemocrat();
        machine.voteForDemocrat();
        machine.voteForDemocrat();

        System.out.println("Democrat votes: " + machine.getDemocratVotes());
        System.out.println("Republican votes: " + machine.getRepublicanVotes());

        machine.clear();
        System.out.println("After reset:");
        System.out.println("Democrat votes: " + machine.getDemocratVotes());
        System.out.println("Republican votes: " + machine.getRepublicanVotes());
    }
}
