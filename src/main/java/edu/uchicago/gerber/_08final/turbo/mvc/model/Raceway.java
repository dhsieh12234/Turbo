package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.GameOp;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Raceway extends Sprite {
    @Setter
    private Point center;
    @Getter
    private int width;    // Width of the segment
    @Getter
    private int height;   // Height of the segment
    private Color color;  // Color of the segment (optional)
    private LinkedList<EnemyCars> cars;

    public Raceway(Point point) {
        this.center = new Point(Game.DIM.width / 2, Game.DIM.height / 2);
        this.width = 600;
        this.height = 300;
        this.color = Color.gray;
        this.cars = new LinkedList<>();

        setTeam(Team.RACEWAY);

    }

    @Override
    public void move() {

//        System.out.println("Moving");
        // Move the raceway downward based on Falcon's vertical speed
        double slowingFactor = 0.5; // Optional: Move slower than the Falcon
        center.y -= CommandCenter.getInstance().getUserCar().getDeltaY() * slowingFactor;
        center.x -= CommandCenter.getInstance().getUserCar().getDeltaX() * slowingFactor;

//         If the segment moves off the screen, remove it and add a new one
        if (center.y - height / 2 > Game.DIM.height) {
            System.out.println("NEW SEGMENT");
            // Remove this segment from the game
            CommandCenter.getInstance().getOpsQueue().enqueue(this, GameOp.Action.REMOVE);

            // Create a new segment at the top
            Raceway newRacewaySegment = new Raceway(new Point(Game.DIM.width / 2, Game.DIM.height - 100));
            newRacewaySegment.setCenter(new Point(Game.DIM.width / 2, -height / 2));
            CommandCenter.getInstance().getOpsQueue().enqueue(newRacewaySegment, GameOp.Action.ADD);
        }
    }


    @Override
    public void draw(Graphics g) {
        // Draw the road
        g.setColor(color);
        g.fillRect(center.x - (width / 2), center.y - (height / 2), width, height);

        // Draw cars in this segment
        for (EnemyCars car : cars) {
            car.draw(g);
        }
    }

    private void configureRandomCars() {
        cars.clear(); // Clear existing cars
        Random random = new Random();
        int numberOfCars = random.nextInt(3) + 1; // 1–3 cars per segment

        for (int i = 0; i < numberOfCars; i++) {
            int randomX = random.nextInt(width - 100) + (center.x - (width / 2) + 50); // Random X within road
            int randomY = random.nextInt(height) + (center.y - (height / 2)); // Random Y within segment
            cars.add(new EnemyCars(new Point(randomX, randomY))); // Add new car
        }
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public int getRadius() {
        return width/2;
    }

    @Override
    public Team getTeam() {
        return Team.RACEWAY;
    }

    @Override
    public void addToGame(LinkedList<Movable> list) {
        list.add(this);
    }

    @Override
    public void removeFromGame(LinkedList<Movable> list) {
        list.remove(this);
    }

    @Override
    public void collidingToFriend(LinkedList<Movable> list) {
        // Placeholder implementation (no specific collision behavior for Raceway)
        // You can leave this empty or add logic if necessary
    }
}
