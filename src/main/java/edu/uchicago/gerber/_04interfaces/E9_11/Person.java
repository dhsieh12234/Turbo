package edu.uchicago.gerber._04interfaces.E9_11;

public class Person {
    private String name;
    private int birthyear;
    public Person(String name, int birthyear) {
        this.name = name;
        this.birthyear = birthyear;
    }

    public String getName() {
        return name;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public String toString() {
        return "Person name: " + name + ", birth year: " + birthyear;
    }
}
