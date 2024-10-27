package edu.uchicago.gerber._04interfaces.E9_11;

public class Driver {
    public static void main(String[] args) {
        // Create a Person object
        Person person = new Person("Alice", 1980);

        // Create a Student object
        Student student = new Student("Computer Science", "Bob", 2000);

        // Create an Instructor object
        Instructor instructor = new Instructor(75000,"Dr. Smith", 1975);

        // Display information using toString()
        System.out.println(person);
        System.out.println(student);
        System.out.println(instructor);
    }
}
