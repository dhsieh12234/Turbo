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
		BLUE_CAR
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
		this.imageState = RANDOM.nextBoolean() ? ImageState.RED_CAR : ImageState.BLUE_CAR;
	}

	/**
	 * Initializes the raster map with images corresponding to different ImageStates.
	 */
	private void initializeRasterMap() {
		Map<ImageState, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(ImageState.RED_CAR, ImageLoader.getImage("/imgs/fal/red_car001.png"));
		rasterMap.put(ImageState.BLUE_CAR, ImageLoader.getImage("/imgs/fal/blue_car001.png"));
		setRasterMap(rasterMap);
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
//		if (img != null) {
//			g.drawImage(img, getCenter().x - getRadius(), getCenter().y - getRadius(),
//					getRadius() * 2, getRadius() * 2, null);
//		} else {
//			// Fallback to drawing a colored circle if image is not available
//			g.setColor(getColor());
//			g.fillOval(getCenter().x - getRadius(), getCenter().y - getRadius(),
//					getRadius() * 2, getRadius() * 2);
//		}
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

		// Enqueue removal after collision
//		CommandCenter.getInstance().getOpsQueue().enqueue(this, GameOp.Action.REMOVE);
	}

	public boolean hasBeenPassed() {
		return hasBeenPassed;
	}

//	/**
//	 * Generates vertices for the Asteroid shape.
//	 *
//	 * @return Array of Points representing vertices.
//	 */
//	private Point[] generateVertices() {
//		// 6.283 radians = 2 * PI
//		final int MAX_RADIANS_X1000 = 6283;
//		final double PRECISION = 1000.0;
//
//		Supplier<PolarPoint> polarPointSupplier = () -> {
//			double r = (800 + RANDOM.nextInt(200)) / PRECISION; // 0.8 to 0.999
//			double theta = RANDOM.nextInt(MAX_RADIANS_X1000) / PRECISION; // 0 to ~6.283
//			return new PolarPoint(r, theta);
//		};
//
//		Function<PolarPoint, Point> polarToCartesian =
//				pp -> new Point(
//						(int) (pp.getR() * PRECISION * Math.sin(pp.getTheta())),
//						(int) (pp.getR() * PRECISION * Math.cos(pp.getTheta()))
//				);
//
//		// Random number of vertices between 25 and 31
//		final int VERTICES = RANDOM.nextInt(7) + 25;
//
//		return Stream.generate(polarPointSupplier)
//				.limit(VERTICES)
//				.sorted(Comparator.comparingDouble(PolarPoint::getTheta))
//				.map(polarToCartesian)
//				.toArray(Point[]::new);
//	}

}
