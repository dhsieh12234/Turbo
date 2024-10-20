package edu.uchicago.gerber._03objects.P8_7;

public class Driver {
    public static void main(String[] args) {
        ComboLock lock = new ComboLock(10, 20, 30);

        lock.reset();

        lock.turnRight(10);

        lock.turnLeft(30);

        lock.turnRight(10);

        if (lock.open()) {
            System.out.println("Lock opened successfully!");
        } else {
            System.out.println("Failed to open the lock.");
        }
    }
}
