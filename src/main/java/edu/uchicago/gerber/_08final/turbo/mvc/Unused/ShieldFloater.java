package edu.uchicago.gerber._08final.turbo.mvc.Unused;

import edu.uchicago.gerber._08final.turbo.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.SoundLoader;
import edu.uchicago.gerber._08final.turbo.mvc.model.Floater;
import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;
import edu.uchicago.gerber._08final.turbo.mvc.model.UserCar;

import java.awt.*;
import java.util.LinkedList;

public class ShieldFloater extends Floater {
	//spawn every 25 seconds
	public static final int SPAWN_SHIELD_FLOATER = Game.FRAMES_PER_SECOND * 5;
	public ShieldFloater() {
		setColor(Color.CYAN);
		setExpiry(120);
	}

	@Override
	public void removeFromGame(LinkedList<Movable> list) {
		super.removeFromGame(list);
		//if getExpiry() > 0, then this remove was the result of a collision, rather than natural mortality
		if (getExpiry() > 0) {
			SoundLoader.playSound("shieldup.wav");
			UserCar userCar = CommandCenter.getInstance().getUserCar();
//			userCar.setTeam(Team.FOE);
			userCar.setShield(UserCar.MAX_SHIELD);
		}

	}
}
