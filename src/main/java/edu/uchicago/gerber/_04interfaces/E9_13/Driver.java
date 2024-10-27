package edu.uchicago.gerber._04interfaces.E9_13;

public class Driver {
    public static void main(String[] args) {
        BetterRectangle rect = new BetterRectangle(0, 0, 5, 10);

        System.out.println("Perimeter: " + rect.getPerimeter());
        System.out.println("Area: " + rect.getArea());
    }
}
