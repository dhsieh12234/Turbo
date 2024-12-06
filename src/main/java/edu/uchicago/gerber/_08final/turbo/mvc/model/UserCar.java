package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.ImageLoader;
import edu.uchicago.gerber._08final.turbo.mvc.controller.SoundLoader;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Data
public class UserCar extends Sprite {

	// ==============================================================
	// FIELDS
	// ==============================================================

	//static fields

	//number of degrees the falcon will turn at each animation cycle if the turnState is LEFT or RIGHT
//	public final static int TURN_STEP = 11;
	//number of frames that the falcon will be protected after a spawn
	public static final int INITIAL_SPAWN_TIME = 48;
	//number of frames falcon will be protected after consuming a NewShieldFloater
	public static final int MAX_SHIELD = 200;
	public static final int MAX_NUKE = 600;

	public static final int MIN_RADIUS = 50;


	//images states
	public enum ImageState {
		FALCON_INVISIBLE, //for pre-spawning
		FALCON, //normal ship
		FALCON_THR, //normal ship thrusting
		FALCON_SHIELD, //shielded ship (cyan)
		FALCON_SHIELD_THR, //shielded ship (cyan) thrusting

	}


	//instance fields (getters/setters provided by Lombok @Data above)
	private int shield;

	private int nukeMeter;
	private int invisible;
	private boolean maxSpeedAttained;

	//showLevel is not germane to the Falcon. Rather, it controls whether the level is shown in the middle of the
	// screen. However, given that the Falcon reference is never null, and that a Falcon is a Movable whose move/draw
	// methods are being called every ~40ms, this is a very convenient place to store this variable.
	private int showLevel;

	/* TODO The enum TurnState as well as the boolean thrusting are examples of the State design pattern. This pattern
	allows an object to change its behavior when its internal state changes. In this case, the boolean thrusting, and
	 the TurnState (with values IDLE, LEFT, and RIGHT) affects how the Falcon moves and draws itself. */
	public enum TurnState {IDLE, LEFT, RIGHT}
	private TurnState turnState = TurnState.IDLE;

	private boolean thrusting;

	// ==============================================================
	// CONSTRUCTOR
	// ==============================================================

	public UserCar() {

		setTeam(Team.FRIEND);

		setRadius(MIN_RADIUS);


		//We use HashMap which has a seek-time of O(1)
		//See the resources directory in the root of this project for pngs.
		//Using enums as keys is safer b/c we know the value exists when we reference the consts later in code.
    	Map<ImageState, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(ImageState.FALCON_INVISIBLE, null );
		rasterMap.put(ImageState.FALCON, ImageLoader.getImage("/imgs/fal/green_car001.png")); //normal ship
		rasterMap.put(ImageState.FALCON_THR, ImageLoader.getImage("/imgs/fal/green_car001.png")); //normal ship thrusting
		rasterMap.put(ImageState.FALCON_SHIELD, ImageLoader.getImage("/imgs/fal/green_car001.png")); //SHIELD
		rasterMap.put(ImageState.FALCON_SHIELD_THR, ImageLoader.getImage("/imgs/fal/green_car001.png")); //S+THR

		setRasterMap(rasterMap);


	}


	// ==============================================================
	// METHODS
	// ==============================================================
	@Override
	public void move() {

		//only call super.move() if falcon is not fixed
		if (!CommandCenter.getInstance().isFalconPositionFixed()) super.move();

		if (invisible > 0) invisible--;
		if (shield > 0) shield--;
		if (nukeMeter > 0) nukeMeter--;
		//The falcon is a convenient place to decrement the showLevel variable as the falcon
		//move() method is being called every frame (~40ms); and the falcon reference is never null.
		if (showLevel > 0) showLevel--;

		final double THRUST = 0.85; // Forward/backward thrust
		final double SIDE_THRUST = 10; // Left/right thrust
		final double DECELERATION_RATE = 0.95; // Friction factor for deceleration
		final double MIN_VELOCITY = 0.05; // Velocity threshold to stop completely
		final int MAX_VELOCITY = 50;


		//apply some thrust vectors using trig.
		if (thrusting) {
			// Forward/backward motion (vertical movement)
			double newDeltaY = getDeltaY() - THRUST; // Reduce Y to move upward

			// Limit vertical velocity to MAX_VELOCITY
			if (Math.abs(newDeltaY) < MAX_VELOCITY) {
				setDeltaY(newDeltaY); // Update Y-axis velocity
				maxSpeedAttained = false; // Not at max speed
			} else {
				maxSpeedAttained = true; // At max speed
			}

		} else {
			// Gradual deceleration when thrusting is not active
			setDeltaY(Math.abs(getDeltaY()) > MIN_VELOCITY ? getDeltaY() * DECELERATION_RATE : 0);
		}
		if (turnState == TurnState.LEFT) {
			setDeltaX(-SIDE_THRUST); // Move left with fixed thrust
		} else if (turnState == TurnState.RIGHT) {
			setDeltaX(+SIDE_THRUST); // Move right with fixed thrust
		} else {
			setDeltaX(0); // Stop horizontal movement when no key is pressed
		}

		setOrientation(270);
	}

	//Since the superclass Spite does not provide an
	// implementation for draw() (contract method from Movable) ,we inherit that contract debt, and therefore must
	// provide an implementation. This is a raster and vector (see drawShield below) implementation of draw().
	@Override
	public void draw(Graphics g) {

		if (nukeMeter > 0) drawNukeHalo(g);

		//set local image-state
		ImageState imageState;
		if (invisible > 0){
			imageState = ImageState.FALCON_INVISIBLE;
		}
		else if (shield > 0){
			imageState = thrusting ? ImageState.FALCON_SHIELD_THR : ImageState.FALCON_SHIELD;
		    drawShieldHalo(g);
		}
		else { //not protected
			imageState = thrusting ? ImageState.FALCON_THR : ImageState.FALCON;
		}

		//down-cast (widen the aperture of) the graphics object to gain access to methods of Graphics2D
		//and render the raster image according to the image-state
		renderRaster((Graphics2D) g, getRasterMap().get(imageState));

	}

	private void drawShieldHalo(Graphics g){
		g.setColor(Color.CYAN);
		g.drawOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() *2, getRadius() *2);
	}

	private void drawNukeHalo(Graphics g){
		if (invisible > 0) return;
		g.setColor(Color.YELLOW);
		g.drawOval(getCenter().x - getRadius()+10, getCenter().y - getRadius()+10, getRadius() *2 -20,
				getRadius() *2-20);
	}

	@Override
	public void removeFromGame(LinkedList<Movable> list) {
		//The falcon is never actually removed from the game-space; instead we decrement numFalcons
		//only execute the decrementFalconNumAndSpawn() method if shield is down.
		if ( shield == 0)  decrementFalconNumAndSpawn();
	}

	@Override
	public void collidingFoe(LinkedList<Movable> list, Movable mov) {
		if (mov instanceof EnemyCars) {
			// Calculate the opposite direction of movement
			int reverseDeltaX = (int) -getDeltaX();
			int reverseDeltaY = (int) -getDeltaY();

			// Apply a backward movement with a damping factor
			double dampingFactor = 0.5; // Adjust this to control the backward speed
			setDeltaX((int) (reverseDeltaX * dampingFactor));
			setDeltaY((int) (reverseDeltaY * dampingFactor));

			// Optionally, move the Falcon slightly back to simulate recoil
			Point currentCenter = getCenter();
			int newX = (int) (currentCenter.x + reverseDeltaX * dampingFactor);
			int newY = (int) (currentCenter.y + reverseDeltaY * dampingFactor);
			setCenter(new Point(newX, newY));

			// Optionally, log the collision event
			System.out.println("Falcon collided! Moving backward with damping factor: " + dampingFactor);

		} else if (mov instanceof Raceway) {
			// Reverse horizontal movement (left or right)
			int reverseDeltaX = (int) -getDeltaX();

			// Apply a horizontal recoil with a damping factor
			double dampingFactor = 0.3; // Adjust for left/right recoil speed
			setDeltaX((int) (reverseDeltaX * dampingFactor));

			// Update position
			Point currentCenter = getCenter();
			int newX = (int) (currentCenter.x + reverseDeltaX * dampingFactor);
			setCenter(new Point(newX, currentCenter.y));

			// Log the event
			System.out.println("Falcon collided with Background! Moving horizontally with damping factor: " + dampingFactor);
		}
	}





	public void decrementFalconNumAndSpawn(){

		CommandCenter.getInstance().setNumFalcons(CommandCenter.getInstance().getNumFalcons() -1);
		if (CommandCenter.getInstance().isGameOver()) return;
		SoundLoader.playSound("shipspawn.wav");
		setShield(UserCar.INITIAL_SPAWN_TIME);
		setInvisible(UserCar.INITIAL_SPAWN_TIME/5);
		setTeam(Team.FRIEND);
		//random number between 0-360 in steps of TURN_STEP
//		setOrientation(Game.R.nextInt(360 / UserCar.TURN_STEP) * UserCar.TURN_STEP);
		setDeltaX(0);
		setDeltaY(0);
		setRadius(UserCar.MIN_RADIUS);
		setMaxSpeedAttained(false);
		setNukeMeter(0);

	}

} //end class
