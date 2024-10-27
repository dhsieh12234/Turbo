package edu.uchicago.gerber._04interfaces.E9_11;

public class Instructor extends Person {
    private int salary;

    public Instructor(int salary, String name, int birthyear) {
        super(name, birthyear);
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }
    public String toString() {
        return super.toString() + " salary: " + salary;
    }
}
