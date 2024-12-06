package edu.uchicago.gerber._08final.turbo.mvc.Unused;

import edu.uchicago.gerber._08final.turbo.mvc.controller.SoundLoader;
import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;
import edu.uchicago.gerber._08final.turbo.mvc.model.Sprite;
import edu.uchicago.gerber._08final.turbo.mvc.model.UserCar;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Bullet extends Sprite {



    public Bullet(UserCar userCar) {

        setTeam(Team.FRIEND);
        setColor(Color.ORANGE);

        setExpiry(20);
        setRadius(6);


        //everything is relative to the falcon ship that fired the bullet
        setCenter((Point) userCar.getCenter().clone());

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(userCar.getOrientation());

        final double FIRE_POWER = 35.0;
        double vectorX =
                Math.cos(Math.toRadians(getOrientation())) * FIRE_POWER;
        double vectorY =
                Math.sin(Math.toRadians(getOrientation())) * FIRE_POWER;

        //fire force: falcon inertia + fire-vector
        setDeltaX(userCar.getDeltaX() + vectorX);
        setDeltaY(userCar.getDeltaY() + vectorY);

        //we have a reference to the falcon passed into the constructor. Let's create some kick-back.
        //fire kick-back on the falcon: inertia - fire-vector / some arbitrary divisor
        final double KICK_BACK_DIVISOR = 36.0;
        userCar.setDeltaX(userCar.getDeltaX() - vectorX / KICK_BACK_DIVISOR);
        userCar.setDeltaY(userCar.getDeltaY() - vectorY / KICK_BACK_DIVISOR);


        //define the points on a cartesian grid
        List<Point> listPoints = new ArrayList<>();
        listPoints.add(new Point(0, 3)); //top point
        listPoints.add(new Point(1, -1)); //right bottom
        listPoints.add(new Point(0, 0));
        listPoints.add(new Point(-1, -1)); //left bottom

        setCartesians(listPoints.toArray(new Point[0]));




    }


    @Override
    public void draw(Graphics g) {
           renderVector(g);
    }

    @Override
    public void addToGame(LinkedList<Movable> list) {
        super.addToGame(list);
        SoundLoader.playSound("thump.wav");

    }
}
