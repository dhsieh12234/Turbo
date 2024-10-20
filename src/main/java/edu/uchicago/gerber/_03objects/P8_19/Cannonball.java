package edu.uchicago.gerber._03objects.P8_19;


public class Cannonball {
    private double x_position;
    private double y_position;
    private double x_velocity;
    private double y_velocity;

    public static final double GRAVITY = -9.81;

    public Cannonball(double x_position) {
        this.x_position = x_position;
        this.y_position = 0;
        this.x_velocity = 0;
        this.y_velocity = 0;
    }

    public void move(double sec) {
        x_position += x_velocity * sec;
        y_position += y_velocity * sec;

        y_velocity += GRAVITY * sec;
    }

    public double getX() {
        return x_position;
    }

    public double getY() {
        return y_position;
    }

    public void shoot(double angle, double velocity) {
        double radians = Math.toRadians(angle);

        x_velocity = velocity * Math.cos(radians);
        y_velocity = velocity * Math.sin(radians);

        while (y_position >= 0) {
            move(0.1);
            System.out.printf("Position -> X: %.2f, Y: %.2f%n", getX(), getY());
        }
    }
}
