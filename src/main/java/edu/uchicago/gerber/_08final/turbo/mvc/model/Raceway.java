package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import lombok.Getter;

import java.awt.*;
import java.util.LinkedList;

public class Raceway implements Movable{
    private Point center;
    @Getter
    private int width;    // Width of the segment
    @Getter
    private int height;   // Height of the segment
    private Color color;  // Color of the segment (optional)

    public Raceway() {
        this.center = new Point(Game.DIM.width / 2, Game.DIM.height / 2);
        this.width = 600;
        this.height = 100000;
        this.color = Color.gray;
    }

    @Override
    public void move() {
        // Move the raceway downward based on Falcon's vertical speed
        double slowingFactor = 0.5; // Optional: Move slower than the Falcon
        center.y += CommandCenter.getInstance().getFalcon().getDeltaY() * slowingFactor;

        // Move the raceway horizontally in the opposite direction of the Falcon
        center.x -= CommandCenter.getInstance().getFalcon().getDeltaX() * slowingFactor;

        // If the segment moves off the top, reset it to the bottom
        if (center.y + height / 2 < 0) {
            center.y = Game.DIM.height + height / 2; // Reset to the bottom
        }
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(center.x - (width / 2), center.y - (height / 2), width, height);
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
        return Team.FRIEND;
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
