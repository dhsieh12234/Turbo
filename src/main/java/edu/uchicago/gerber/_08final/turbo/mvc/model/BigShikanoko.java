package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BigShikanoko extends Floater {
    private BufferedImage deerImage;

    public BigShikanoko() {
        // Load the deer image
        deerImage = ImageLoader.getImage("/imgs/fal/deer.png");
        setCenter(new Point(Game.DIM.width / 2, -deerImage.getHeight())); // Start above the screen
        setDeltaY(2); // Move down at the same speed as the raceway
        setRadius(deerImage.getWidth() / 2); // Adjust radius based on image size
        setExpiry(300); // Set expiry (e.g., 300 frames)
    }

    @Override
    public void draw(Graphics g) {
        if (deerImage != null) {
            int scaleFactor = 3; // Scale the image
            int width = deerImage.getWidth() * scaleFactor;
            int height = deerImage.getHeight() * scaleFactor;

            g.drawImage(deerImage,
                    getCenter().x - width / 2, // Center horizontally
                    getCenter().y - height / 2, // Center vertically
                    width, height, null);
        } else {
            System.err.println("Deer image not loaded!");
        }
    }

    @Override
    public void move() {
        // Move the deer downwards like the raceway
        Point center = getCenter();
        int speedMultiplier = 5; // Increase speed by a factor of 5
        setCenter(new Point(center.x, center.y + (int) getDeltaY() * speedMultiplier));

        // Remove the deer if it goes off-screen
        if (center.y > Game.DIM.height + getRadius()) {
            setExpiry(0); // Mark for removal
        }
    }
}
