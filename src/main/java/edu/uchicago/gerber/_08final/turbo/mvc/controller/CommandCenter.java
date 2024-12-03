package edu.uchicago.gerber._08final.turbo.mvc.controller;


import edu.uchicago.gerber._08final.turbo.mvc.model.*;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.util.*;

//The CommandCenter is a singleton that manages the state of the game.
//the lombok @Data gives us automatic getters and setters on all members
@Data
public class CommandCenter {

	public enum Universe {
		FREE_FLY,
		CENTER,
		BIG,
		HORIZONTAL,
		VERTICAL,
		DARK

	}
    // Manual getter method
    @Getter
    private final Raceway raceway = new Raceway(new Point(Game.DIM.width / 2, Game.DIM.height - 100)); // Example position

    private Universe universe;
	private  int numFalcons;
	private  int level;
	private  long score;
	private  boolean paused;
	private  boolean themeMusic;
	private boolean radar; //to toggle on/off the mini-map
	//this value is used to count the number of frames (full animation cycles) in the game
	private long frame;

	//the falcon is located in the movFriends list, but since we use this reference a lot, we keep track of it in a
	//separate reference. Use final to ensure that the falcon ref always points to the single falcon object on heap.
	//Lombok will not provide setter methods on final members
	private final Falcon falcon  = new Falcon();
	//miniDimHash associates dimension with the Universe.
	private final Map<Universe, Dimension> miniDimHash = new HashMap<>();
	private final MiniMap miniMap = new MiniMap();

	/*
	 TODO The following LinkedList<Movable> are examples of the Composite design pattern which is used to allow
	 compositions of objects to be treated uniformly. Here are the elements of the Composite design pattern:

     Component: Movable serves as the component interface. It defines common methods (move(), draw(Graphics g), etc.)
     that all concrete implementing classes must provide.

     Leaf: Concrete classes that implement Movable (e.g., Bullet, Asteroid) are the leaf nodes. They implement the
     Movable interface and provide specific behavior.

     Composite: The LinkedLists below that aggregate Movable objects (e.g., movFriends, movFoes) act as
     composites. They manage collections of Movable objects and provide a way to iterate over and operate on them as a
     group.

	 */
	private final LinkedList<Movable> movDebris = new LinkedList<>();
	private final LinkedList<Movable> movFoes = new LinkedList<>();
	private final LinkedList<Movable> movFriends = new LinkedList<>();
	private final LinkedList<Movable> movFloaters = new LinkedList<>();
	private final LinkedList<Movable> movRaceway = new LinkedList<>();

	private final GameOpsQueue opsQueue = new GameOpsQueue();



	/* TODO This is an example of the Singleton design pattern. The Singleton ensures that a class has one (and only
	one) instance on the heap and provides a global point of access at instance. This is useful when you need to
	coordinate actions among objects in your system or manage state. CommandCenter manages the state of the game.
	 */
	private static CommandCenter instance = null;

    // Check if input is enabled
    @Getter
    private boolean inputEnabled = true;

	private final long CAR_SPAWN_INTERVAL = 2000; // Spawn a car every 2 seconds
	private long lastCarSpawnTime = 0;

	// Define the screen boundaries
	private final int SCREEN_WIDTH = Game.DIM.width;
	private final int SCREEN_HEIGHT = Game.DIM.height;

	// Constructor made private
	private CommandCenter() {}

    //this class maintains game state - make this a singleton.
	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}

	// Disable user input
	public void disableInput() {
		inputEnabled = false;
		System.out.println("User input disabled.");
	}

	// Enable user input
	public void enableInput() {
		inputEnabled = true;
		System.out.println("User input enabled.");
	}

    private void setLevelFromEnv(){
		String strLevel = System.getenv("LEVEL");
		String envLevel = (strLevel != null) ? strLevel : "6";
		try {
			setLevel(Integer.parseInt(envLevel) - 1);
		} catch (NumberFormatException e) {
			setLevel(0);
		}
	}

	public void update() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastCarSpawnTime > CAR_SPAWN_INTERVAL) {
			spawnCar();
			lastCarSpawnTime = currentTime;
		}

		// Remove cars that are off-screen
		Iterator<Movable> iterator = movFoes.iterator();
		while (iterator.hasNext()) {
			Movable movable = iterator.next();
			if (movable instanceof Asteroid) {
				Asteroid car = (Asteroid) movable;
				Point center = car.getCenter();
				if (center.x < -car.getRadius() || center.x > SCREEN_WIDTH + car.getRadius() ||
						center.y < -car.getRadius() || center.y > SCREEN_HEIGHT + car.getRadius()) {
					// Car is off-screen
					iterator.remove();
					// Enqueue removal if necessary
					opsQueue.enqueue(car, GameOp.Action.REMOVE);
					// Spawn a new car
					spawnCar();
				}
			}
		}

		// Increment frame
		frame = frame < Long.MAX_VALUE ? frame + 1 : 0;
	}

	private void spawnCar() {
		Random random = new Random();
		int x = random.nextInt(SCREEN_WIDTH - 100) + 50; // Spawn within screen width with padding
		int y = -100; // Start above the screen

		Asteroid newCar = new Asteroid(new Point(x, y));
		opsQueue.enqueue(newCar, GameOp.Action.ADD);
		System.out.println("Spawned new car at (" + x + ", " + y + ")");
	}


	public void initGame(){
		clearAll();
		generateStarField();
		setDimHash();
		setLevelFromEnv();
		setScore(0);
		setPaused(false);
		setUniverse(Universe.VERTICAL);
		//set to one greater than number of falcons lives in your game as decrementFalconNumAndSpawn() also decrements
		setNumFalcons(3);

		movRaceway.clear(); // Clear any previous raceway segments

		int segmentHeight = 300; // Height of each raceway segment
		int numSegments = (int) Math.ceil((double) Game.DIM.height / segmentHeight) + 1;
		System.out.println("NUMSEGMENTS" + numSegments);// Enough segments to cover the screen

		for (int i = 0; i < numSegments; i++) {
			Raceway racewaySegment = new Raceway(new Point(Game.DIM.width / 2, Game.DIM.height - 100));
			int yPosition = i * segmentHeight - (segmentHeight / 2); // Position segments one after another
			racewaySegment.setCenter(new Point(Game.DIM.width / 2, yPosition));
			opsQueue.enqueue(racewaySegment, GameOp.Action.ADD);

			System.out.println("Segment " + i + " - Height: " + racewaySegment.getHeight() + ", Width: " + racewaySegment.getWidth());
		}
		spawnCar();

		falcon.decrementFalconNumAndSpawn();
		opsQueue.enqueue(falcon, GameOp.Action.ADD);
		opsQueue.enqueue(miniMap, GameOp.Action.ADD);

	}

	private void setDimHash(){
		//initialize with values that define the aspect ratio of the Universe. See checkNewLevel() of Game class.
		miniDimHash.put(Universe.FREE_FLY, new Dimension(1,1));
		miniDimHash.put(Universe.CENTER, new Dimension(1,1));
		miniDimHash.put(Universe.BIG, new Dimension(2,2));
		miniDimHash.put(Universe.HORIZONTAL, new Dimension(3,1));
		miniDimHash.put(Universe.VERTICAL, new Dimension(1,3));
		miniDimHash.put(Universe.DARK, new Dimension(4,4));
	}


	private void generateStarField(){

		int count = 100;
		while (count-- > 0){
			opsQueue.enqueue(new Star(), GameOp.Action.ADD);
		}
	}

	public void incrementFrame(){
		frame = frame < Long.MAX_VALUE ? frame + 1 : 0;
	}

	private void clearAll(){
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
		movRaceway.clear();
	}

	public boolean isGameOver() {		//if the number of falcons is zero, then game over
		return numFalcons < 1;
	}

	public Dimension getUniDim(){
		return miniDimHash.get(universe);
	}

	public boolean isFalconPositionFixed(){
		return universe != Universe.FREE_FLY;
	}






}
