package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import lombok.Getter;

import java.awt.*;
import java.util.LinkedList;

@Getter
public class Background extends Sprite {

    private final int width;
    private final int height;

    public Background() {
        // Set team to BACKGROUND
        setTeam(Team.BACKGROUND);

        this.width = Game.DIM.width;
        this.height = Game.DIM.height;
    }



    @Override
    public void draw(Graphics g) {
        // Example: Draw a background (replace with actual implementation)
        g.setColor(Color.BLUE); // Background color
        g.fillRect(0, 0, Game.DIM.width, Game.DIM.height); // Cover entire screen
    }

    @Override
    public void move() {
        // Background does not move, so this is left empty
    }

    @Override
    public void collidingFoe(LinkedList<Movable> list, Movable mov) {
    }

}
