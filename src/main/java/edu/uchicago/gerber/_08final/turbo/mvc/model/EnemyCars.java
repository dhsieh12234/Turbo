package edu.uchicago.gerber._08final.turbo.mvc.model;

import edu.uchicago.gerber._08final.turbo.mvc.controller.*;
import edu.uchicago.gerber._08final.turbo.mvc.model.prime.PolarPoint;
import edu.uchicago.gerber._08final.turbo.mvc.controller.ImageLoader;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EnemyCars extends Sprite {

	// Constants
	private static final int CAR_RADIUS = 50; // Fixed radius for all cars
//	private static final int CAR_SPEED = 5;
	private static final Random RANDOM = new Random();

	public int getSize() {
		return CAR_RADIUS;
	}

	@Setter
    private boolean hasBeenPassed = false;

	// Enum for image states
	public enum ImageState {
		RED_CAR, // Different color cars
		BLUE_CAR,
		GREEN_CAR
	}

	private final ImageState imageState;

	/**
	 * Single constructor for Asteroid.
	 *
	 * @param center The center point where the Asteroid (car) will be placed.
	 */
	public EnemyCars(Point center) {
		// Initialize position
		setCenter(center);

		// Assign team to prevent NullPointerException
		setTeam(Team.FOE);

		// Set fixed radius for all cars
		setRadius(CAR_RADIUS);

//		// Set color (can be overridden by imageState)
//		setColor(Color.WHITE);

//		// Initialize movement deltas opposite to Falcon's current velocity with slight randomness
		setDeltaX(-CommandCenter.getInstance().getUserCar().getDeltaX() + RANDOM.nextInt(3) - 1);
		setDeltaY(-CommandCenter.getInstance().getUserCar().getDeltaY() + RANDOM.nextInt(3) - 1);

//		// Set spin (if applicable)
//		setSpin(0); // Adjust as needed

//		// Configure vertices (if necessary)
//		setCartesians(generateVertices());

		// Initialize raster map for image states
		initializeRasterMap();

		// Randomly assign image state
		this.imageState = getRandomImageState();
	}

	/**
	 * Initializes the raster map with images corresponding to different ImageStates.
	 */
	private void initializeRasterMap() {
		Map<ImageState, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(ImageState.RED_CAR, ImageLoader.getImage("/imgs/fal/red_car001.png"));
		rasterMap.put(ImageState.BLUE_CAR, ImageLoader.getImage("/imgs/fal/blue_car001.png"));
		rasterMap.put(ImageState.GREEN_CAR, ImageLoader.getImage("/imgs/fal/green_car002.png"));
		setRasterMap(rasterMap);
	}

	private ImageState getRandomImageState() {
		ImageState[] states = ImageState.values();
		return states[RANDOM.nextInt(states.length)];
	}

	@Override
	public void move() {
		// Get Falcon's deltaX and deltaY
		UserCar userCar = CommandCenter.getInstance().getUserCar();
		double falconDeltaX = userCar.getDeltaX();
		double falconDeltaY = userCar.getDeltaY();

		// Apply slowing effect to the Asteroid's own deltaX and deltaY
		// For example, reduce deltaX and deltaY by a factor of 0.5 each frame
		setDeltaX(getDeltaX() * 0.5);
		setDeltaY(getDeltaY() * 0.5);

		// Update position based on delta values relative to the Falcon
		Point currentCenter = getCenter();
		int newX = (int) (currentCenter.x + getDeltaX() - falconDeltaX);
		int newY = (int) (currentCenter.y + getDeltaY() - falconDeltaY);
		setCenter(new Point(newX, newY));
	}


	@Override
	public void draw(Graphics g) {
		// Render the image based on the current ImageState
		BufferedImage img = getRasterMap().get(imageState);
		g.drawImage(img, getCenter().x - getRadius(), getCenter().y - getRadius(),
				getRadius() * 2, getRadius() * 2, null);

	}

	@Override
	public void removeFromGame(LinkedList<Movable> list) {
		super.removeFromGame(list);

		// Award points for destroying the car (Asteroid)
		CommandCenter.getInstance().setScore(
				CommandCenter.getInstance().getScore() + 10L // Fixed points
		);

		// Play sound
//		SoundLoader.playSound("kapow.wav");
	}

	@Override
	public void collidingFoe(LinkedList<Movable> list, Movable mov) {
		Point asteroidCenter = getCenter();

		if (mov.getTeam() == Team.FRIEND) {
			// Apply shaking effect to simulate collision
			for (int i = 0; i < 5; i++) {
				int shakeOffsetX = (int) (Math.random() * 10 - 5); // -5 to +5
				int shakeOffsetY = (int) (Math.random() * 10 - 5); // -5 to +5

				// Update the center to simulate shaking
				setCenter(new Point(
						asteroidCenter.x + shakeOffsetX,
						asteroidCenter.y + shakeOffsetY
				));
			}
		} else if (mov.getTeam() == Team.RACEWAY){
			int backgroundWidth = Game.DIM.width / 2; // Assume background width is half the game screen width
			if (asteroidCenter.x < backgroundWidth) {
				// Collided on the left side, teleport 100 blocks to the right
				setCenter(new Point(asteroidCenter.x + 100, asteroidCenter.y));
			} else {
				// Collided on the right side, teleport 100 blocks to the left
				setCenter(new Point(asteroidCenter.x - 100, asteroidCenter.y));
			}
		}
	}

	public boolean hasBeenPassed() {
		return hasBeenPassed;
	}

}
