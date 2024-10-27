package edu.uchicago.gerber._04interfaces.E9_11;

public class Student extends Person {
    private String major;

    public Student(String major, String name, int birthyear) {
        super(name, birthyear);
        this.major = major;
    }
    public String getMajor() {
        return major;
    }

    public String toString() {
        return super.toString() + " major: " + major;
    }

}
