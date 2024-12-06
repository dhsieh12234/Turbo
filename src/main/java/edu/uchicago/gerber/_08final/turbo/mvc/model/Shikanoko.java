package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Shikanoko extends Floater{

    //spawn every 12 seconds
    public static final int SPAWN_SHIKANOKO_FLOATER = Game.FRAMES_PER_SECOND * 5;
    private BufferedImage deerImage;
    private boolean showBigImage = false; // To control image visibility
    private long displayStartTime; // To track when the display starts

    public Shikanoko() {
        setColor(Color.YELLOW);
        setExpiry(120);


        deerImage = ImageLoader.getImage("/imgs/fal/deer.png");
    }


    @Override
    public void draw(Graphics g) {
        if (deerImage != null) {
            g.drawImage(deerImage, getCenter().x - getRadius(), getCenter().y - getRadius(),
                    getRadius() * 2, getRadius() * 2, null);
        } else {
            System.err.println("Deer image not loaded!");
        }
    }


    public void showBigImageFor3Seconds() {
        showBigImage = true;
        displayStartTime = System.currentTimeMillis(); // Record the start time
    }

    public void big_draw(Graphics g) {
        if (showBigImage && deerImage != null) {
            // Check if 3 seconds have passed since the image started displaying
            if (System.currentTimeMillis() - displayStartTime > 3000) {
                showBigImage = false; // Stop showing the image
                return;
            }

            int scaleFactor = 4; // Larger scaling factor
            int width = deerImage.getWidth() * scaleFactor;
            int height = deerImage.getHeight() * scaleFactor;

            g.drawImage(deerImage,
                    Game.DIM.width / 2 - width / 2, // Center horizontally on the screen
                    Game.DIM.height / 2 - height / 2, // Center vertically on the screen
                    width, // Scaled width
                    height, // Scaled height
                    null);
        } else if (deerImage == null) {
            System.err.println("Deer image not loaded!");
        }
    }



    @Override
    public void removeFromGame(LinkedList<Movable> list) {
        super.removeFromGame(list);
        //if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
        if (getExpiry() > 0) {
            SoundLoader.playSound("nuke-up.wav");
            CommandCenter.getInstance().getOpsQueue().enqueue(new BigShikanoko(), GameOp.Action.ADD);
        }
    }
}
