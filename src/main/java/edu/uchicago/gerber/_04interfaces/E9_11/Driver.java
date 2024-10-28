package edu.uchicago.gerber._04interfaces.E9_11;

public class Driver {
    public static void main(String[] args) {
        Person person = new Person("Derek", 2002);

        Student student = new Student("Computer Science", "Viral", 2002);

        Instructor instructor = new Instructor(75000,"Dr. Huang", 2001);

        System.out.println(person);
        System.out.println(student);
        System.out.println(instructor);
    }
}
