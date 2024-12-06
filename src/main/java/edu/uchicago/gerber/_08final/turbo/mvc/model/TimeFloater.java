package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.ImageLoader;
import edu.uchicago.gerber._08final.turbo.mvc.controller.SoundLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class TimeFloater extends Floater {

	//spawn every 12 seconds
	public static final int SPAWN_TIME_FLOATER = Game.FRAMES_PER_SECOND * 27;
	private BufferedImage timerImage;

	public TimeFloater() {
		setColor(Color.YELLOW);
		setExpiry(120);
		timerImage = ImageLoader.getImage("/imgs/fal/timer.png");
	}

	public void draw(Graphics g) {
		if (timerImage != null) {
			g.drawImage(timerImage, getCenter().x - getRadius(), getCenter().y - getRadius(),
					getRadius() * 2, getRadius() * 2, null);
		} else {
			System.err.println("Deer image not loaded!");
		}
	}

	@Override
	public void removeFromGame(LinkedList<Movable> list) {
		super.removeFromGame(list);
		//if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
		if (getExpiry() > 0) {
			SoundLoader.playSound("nuke-up.wav");

			GameTimer gameTimer = Game.getInstance().getGameTimer();
			if (gameTimer != null) {
				gameTimer.addTime(5000); // Add 5 seconds to the game timer
				System.out.println("Added 5 seconds to the game timer!");
			}
		}

	}


}
