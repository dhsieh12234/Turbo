package edu.uchicago.gerber._08final.turbo.mvc.controller;

import edu.uchicago.gerber._08final.turbo.mvc.Unused.Nuke;
import edu.uchicago.gerber._08final.turbo.mvc.Unused.ShieldFloater;
import edu.uchicago.gerber._08final.turbo.mvc.model.*;
import edu.uchicago.gerber._08final.turbo.mvc.view.GamePanel;
import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;


// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game implements Runnable, KeyListener {

    // ===============================================
    // FIELDS
    // ===============================================

    public static final Dimension DIM = new Dimension(1500, 950); //the dimension of the game-screen.
    private static final int LARGE_RADIUS = 100;


    private final GamePanel gamePanel;

    //this is used throughout many classes.
    public static final Random R = new Random();

    public final static int ANIMATION_DELAY = 40; // milliseconds between frames

    public final static int FRAMES_PER_SECOND = 1000 / ANIMATION_DELAY;

    private final Thread animationThread;

    @Getter
    public GameTimer gameTimer;

    @Getter
    public static Game instance;


    //key-codes
    private static final int
            PAUSE = 80, // p key
            QUIT = 81, // q key
            LEFT = 37, // rotate left; left arrow
            RIGHT = 39, // rotate right; right arrow
            TURBO = 38, // thrust; up arrow
            ACCELERATE = 83, // s key
//            FIRE = 32, // space key
            MUTE = 77; // m-key mute
//            NUKE = 70, // f-key
//            RADAR = 65; // a-key

    // ===============================================
    // ==CONSTRUCTOR
    // ===============================================

    public Game() {

        gamePanel = new GamePanel(DIM, this);
        gamePanel.addKeyListener(this); //Game object implements KeyListener

        CommandCenter.getInstance().initGame();

        gameTimer = new GameTimer(60_000);
        gameTimer.start();

        //fire up the animation thread
        animationThread = new Thread(this); // pass the animation thread a runnable object, the Game object
        //set as daemon so as not to block the main thread from exiting
        animationThread.setDaemon(true);
        animationThread.start();
        instance = this;
    }

    // ===============================================
    // ==METHODS
    // ===============================================

    public static void main(String[] args) {


        //typical Swing application start; we pass EventQueue a Runnable object.
        EventQueue.invokeLater(Game::new);
    }

    // Game implements runnable, and must have run method
    @Override
    public void run() {

        // lower animation thread's priority, thereby yielding to the 'Event Dispatch Thread' or EDT
        // thread which listens to keystrokes
        animationThread.setPriority(Thread.MIN_PRIORITY);

        // and get the current time
        long startTime = System.currentTimeMillis();

        // this thread animates the scene
        while (Thread.currentThread() == animationThread) {

            //update game timer
            if (CommandCenter.getInstance().getGameState() == CommandCenter.GameState.PLAYING) {
                gameTimer.update();

                // Check if time is up
                if (gameTimer.isTimeUp()) {
                    CommandCenter.getInstance().setGameState(CommandCenter.GameState.TIME_UP);
                    int carPassed = CommandCenter.getInstance().getCarsPassed();
                    int carTarget = CommandCenter.getInstance().getCAR_PASS_THRESHOLD();
                    if (carPassed >= carTarget) {
//                        System.out.println("GOOD JOB YOU PASS");
                        CommandCenter.getInstance().CAR_PASS_THRESHOLD += 5;
                        gameTimer = new GameTimer(60_000);

                        int level = CommandCenter.getInstance().getLevel();
                        level = level + 1;
                        CommandCenter.getInstance().setLevel(level);
                        CommandCenter.getInstance().initGame();

                    } else {
                        CommandCenter.getInstance().CAR_PASS_THRESHOLD = 25;
                        CommandCenter.getInstance().setGameState(CommandCenter.GameState.GAME_OVER);
                    }

                    continue; // Skip the rest of the loop to stop game updates
                }
            }

            //updates car position
            CommandCenter.getInstance().update();

            //this call will cause all movables to move() and draw() themselves every ~40ms
            // see GamePanel class for details
            gamePanel.update(gamePanel.getGraphics());

            checkCollisions();
            keepSpawningEnemies();
            checkFloaters();
            updateCarsPassed();
            //this method will execute addToGame() and removeFromGame() callbacks on Movable objects
            processGameOpsQueue();
            //keep track of the frame for development purposes
            CommandCenter.getInstance().incrementFrame();

            // surround the sleep() in a try/catch block
            // this simply controls delay time between
            // the frames of the animation
            try {
                // The total amount of time is guaranteed to be at least ANIMATION_DELAY long.  If processing (update)
                // between frames takes longer than ANIMATION_DELAY, then the difference between startTime -
                // System.currentTimeMillis() will be negative, then zero will be the sleep time
                startTime += ANIMATION_DELAY;

                Thread.sleep(Math.max(0,
                        startTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // do nothing (bury the exception), and just continue, e.g. skip this frame -- no big deal
            }
        } // end while
    } // end run

    private void checkFloaters() {

        spawnTimeFloater();
//        spawnShieldFloater();
        spawnShikanokoFloater();
    }

    private void updateCarsPassed() {
        UserCar playerCar = CommandCenter.getInstance().getUserCar();
        int playerY = playerCar.getCenter().y;

        for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
            if (movFoe instanceof EnemyCars enemyCar) {
                if (!enemyCar.hasBeenPassed()) {
                    int enemyY = enemyCar.getCenter().y;

                    // Assuming the game coordinate system where y increases downwards
                    // Check if the player's car has moved ahead of the enemy car
//                    System.out.println(enemyY);
                    if (playerY < enemyY) {
                        // Player has passed this enemy car
                        enemyCar.setHasBeenPassed(true);
                        CommandCenter.getInstance().incrementCarsPassed();
                    }
                }
            }
        }
    }

    /*
    TODO The following two methods are an example of the Command design pattern. This approach involves deferring
    mutations to collections (linked lists of Movables) while iterating over them, and then processing the mutations
    later (in the processGameOpsQueue() method below). The Command design pattern decouples the request for an
    operation from the  execution of the operation itself. We do this because mutating a data structure while iterating it
    is dangerous and may lead to null-pointer or array-index-out-of-bounds exceptions, or other erroneous behavior.
     */

    //Involves calcualting whether a friend is within the raceway. I understand you wanted to avoid doing rectangle
    //intersection but this was the only solution I could think of to keep the cars within the raceway bounds. The way
    //I set up is the raceway on top of background so there was no way I could do a collision with either to detect it.
    private boolean isTouchingRaceway(Movable friend, Raceway raceway) {
        // Get the bounding rectangle of the FRIEND
        Rectangle friendBounds = new Rectangle(
                friend.getCenter().x - friend.getRadius(),
                friend.getCenter().y - friend.getRadius(),
                friend.getRadius() * 2,
                friend.getRadius() * 2
        );

        // Get the bounding rectangle of the RACEWAY
        Rectangle racewayBounds = new Rectangle(
                raceway.getCenter().x - raceway.getWidth() / 2,
                raceway.getCenter().y - raceway.getHeight() / 2,
                raceway.getWidth(),
                raceway.getHeight()
        );

        // Check if the raceway contains the movable
        return racewayBounds.contains(friendBounds);
    }


    private void checkCollisions() {

        //This has order-of-growth of O(FOES * FRIENDS)
        Point pntFriendCenter, pntFoeCenter;
        int radFriend, radFoe;
        for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
            for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {

            //Check the type of the movable
//                if (movFriend.getTeam() == Movable.Team.FRIEND) {
//                    System.out.println("This is a FRIEND: " + movFriend);
//                } else if (movFriend.getTeam() == Movable.Team.FOE) {
//                    System.out.println("This is a FOE: " + movFriend);
//                } else {
//                    System.out.println("This is another type: " + movFriend.getTeam());
//                }

                pntFriendCenter = movFriend.getCenter();
                pntFoeCenter = movFoe.getCenter();
                radFriend = movFriend.getRadius();
                radFoe = movFoe.getRadius();

                //detect collision
                if (pntFriendCenter.distance(pntFoeCenter) < (radFriend + radFoe)) {
                    System.out.println("RECOGNIZED COLLISION BETWEEN FRIEND AND FOE");
                    //enqueue the Action to collide
                    CommandCenter.getInstance().getOpsQueue().enqueue(movFriend, movFoe, GameOp.Action.COLLIDE);
                    CommandCenter.getInstance().getOpsQueue().enqueue(movFoe, movFriend, GameOp.Action.COLLIDE);
                }
            }//end inner for
        }//end outer for

        // Check collisions for FRIENDS not fully inside any RACEWAY
        for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
            boolean fullyInsideAnyRaceway = false;
            Raceway collidedRaceway = null;

            // Check if the FRIEND is fully inside any raceway segment
            for (Movable movRaceway : CommandCenter.getInstance().getMovRaceway()) {
                Raceway raceway = (Raceway) movRaceway;

                if (isTouchingRaceway(movFriend, raceway)) {
                    fullyInsideAnyRaceway = true;
                    // The car is fully inside this raceway segment, no need to check further
                    break;
                } else {
                    // The car is not fully inside this raceway segment
                    // Keep track of the raceway the car is currently interacting with
                    collidedRaceway = raceway;
                }
            }
//            System.out.println(fullyInsideAnyRaceway);

            // Trigger the collision if the FRIEND is not fully inside any RACEWAY
            if (!fullyInsideAnyRaceway) {
                // Pass the raceway object when enqueuing the collision
                System.out.println("RECOGNIZED COLLISION BETWEEN BACKGROUND AND FRIEND");
                CommandCenter.getInstance().getOpsQueue().enqueue(movFriend, collidedRaceway, GameOp.Action.COLLIDE);
            }
        }

        // Check if FOES are not fully inside any raceway
        for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
            boolean fullyInsideAnyRaceway = false;
            Raceway collidedRaceway = null;

            // Check if the FOE is fully inside any raceway segment
            for (Movable movRaceway : CommandCenter.getInstance().getMovRaceway()) {
                Raceway raceway = (Raceway) movRaceway;

                if (isTouchingRaceway(movFoe, raceway)) {
                    fullyInsideAnyRaceway = true;
                    break; // Fully inside, no further checks needed
                } else {
                    collidedRaceway = raceway; // Track the collided raceway
                }
            }

            // If FOE is not fully inside any raceway, trigger a collision
            if (!fullyInsideAnyRaceway) {
                System.out.println("RECOGNIZED COLLISION BETWEEN FOE AND RACEWAY");
                CommandCenter.getInstance().getOpsQueue().enqueue(movFoe, collidedRaceway, GameOp.Action.COLLIDE);
            }
        }

        //check for collisions between falcon and floaters. Order of growth of O(FLOATERS)
        Point pntFalCenter = CommandCenter.getInstance().getUserCar().getCenter();
        int radFalcon = CommandCenter.getInstance().getUserCar().getRadius();

        Point pntFloaterCenter;
        int radFloater;
        for (Movable movFloater : CommandCenter.getInstance().getMovFloaters()) {
            pntFloaterCenter = movFloater.getCenter();
            radFloater = movFloater.getRadius();
            //detect collision
            if (pntFalCenter.distance(pntFloaterCenter) < (radFalcon + radFloater)) {
                System.out.println("RECOGNIZED COLLISION BETWEEN FLOATER AND FRIEND");
                //enqueue the floater
                CommandCenter.getInstance().getOpsQueue().enqueue(movFloater, GameOp.Action.REMOVE);
            }//end if
        }//end for

    }//end meth


    //This method adds and removes movables to/from their respective linked-lists.
    private void processGameOpsQueue() {

        //deferred mutation: these operations are done AFTER we have completed our collision detection to avoid
        // mutating the movable linkedlists while iterating them above.
        while (!CommandCenter.getInstance().getOpsQueue().isEmpty()) {

            GameOp gameOp = CommandCenter.getInstance().getOpsQueue().dequeue();

            //given team, determine which linked-list this object will be added-to or removed-from
            LinkedList<Movable> list;
            Movable mov = gameOp.getMovable();

            switch (mov.getTeam()) {
                case FOE:
                    list = CommandCenter.getInstance().getMovFoes();
                    break;
                case FRIEND:
                    list = CommandCenter.getInstance().getMovFriends();
                    break;
                case FLOATER:
                    list = CommandCenter.getInstance().getMovFloaters();
                    break;
                case DEBRIS:
                    list = CommandCenter.getInstance().getMovDebris();
                    break;
                case RACEWAY:
                    list = CommandCenter.getInstance().getMovRaceway();
                    break;
                case BACKGROUND:
                    list = CommandCenter.getInstance().getMovBackground();
                    break;
                default:
                    System.err.println("Unknown team: " + mov.getTeam());
                    continue;
            }

            //pass the appropriate linked-list from above
            //this block will execute the addToGame() or removeFromGame() callbacks in the Movable models.
            GameOp.Action action = gameOp.getAction();
            if (action == GameOp.Action.ADD) {
                mov.addToGame(list);
            } else if (action == GameOp.Action.COLLIDE) {
                Movable other = gameOp.getOther();
                mov.collidingFoe(list, other);
            } else //REMOVE
                mov.removeFromGame(list);
        }//end while
    }

//Floaters
    private void spawnShieldFloater() {
        if (CommandCenter.getInstance().getFrame() % ShieldFloater.SPAWN_SHIELD_FLOATER == 0) {
            System.out.println("SPAWNNN SHIELD");
            CommandCenter.getInstance().getOpsQueue().enqueue(new ShieldFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnTimeFloater() {
//        System.out.println("Frame: " + CommandCenter.getInstance().getFrame() +
//                ", Time Floater Interval: " + TimeFloater.SPAWN_TIME_FLOATER +
//                ", Spawn Condition: " + (CommandCenter.getInstance().getFrame() % TimeFloater.SPAWN_TIME_FLOATER == 0));
        if (CommandCenter.getInstance().getFrame() % TimeFloater.SPAWN_TIME_FLOATER == 0) {
            System.out.println("SPAWNNN NUKE");
            CommandCenter.getInstance().getOpsQueue().enqueue(new TimeFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnShikanokoFloater() {
//        System.out.println(CommandCenter.getInstance().getFrame() % Shikanoko.SPAWN_SHIKANOKO_FLOATER == 0);
        if (CommandCenter.getInstance().getFrame() % Shikanoko.SPAWN_SHIKANOKO_FLOATER == 0) {
            System.out.println("SPAWNNN SHIKANOKO");
            CommandCenter.getInstance().getOpsQueue().enqueue(new Shikanoko(), GameOp.Action.ADD);
        }
    }


    //this method spawns new Large (0) Asteroids
    // This method spawns new Enemy Cars, tried to do many but makes it too ahrd
    private void spawnEnemyCar(int num) {
        CommandCenter commandCenter = CommandCenter.getInstance();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            Point spawnPoint;

            // Determine spawn position based on the car's index
            if (i == 0) {
                // Spawn the first car at the top of the screen
                spawnPoint = determineSpawnPointWithinRaceway(commandCenter.getRaceway()); // Adjust for car's height
            } else if (i == 1) {
                // Spawn the second car midway down the screen
                spawnPoint = new Point(Game.DIM.width / 2, Game.DIM.height / 2);
            } else {
                // Spawn other cars randomly within the raceway
                spawnPoint = determineSpawnPointWithinRaceway(commandCenter.getRaceway());
            }

            // Create a new Enemy Car at the determined position
            EnemyCars enemyCar = new EnemyCars(spawnPoint);

            // Enqueue the car to be added to the game
            commandCenter.getOpsQueue().enqueue(enemyCar, GameOp.Action.ADD);

            // Log the spawn event
            System.out.println("Spawned Enemy Car at: " + spawnPoint);
        }
    }


    /**
     * Determines a random spawn point within the given raceway.
     *
     * @param raceway The current raceway within which to spawn the asteroid.
     * @return A Point representing the spawn location.
     */
    private Point determineSpawnPointWithinRaceway(Raceway raceway) {
        if (raceway == null) {
            // Fallback to random position if raceway is not defined
            int x = Game.R.nextInt(Game.DIM.width);
            int y = Game.R.nextInt(Game.DIM.height);
            return new Point(x, y);
        }

        Point racewayCenter = raceway.getCenter();
        int racewayWidth = raceway.getWidth();
        int racewayHeight = raceway.getHeight();

        // Define spawn boundaries within the raceway
        int minX = racewayCenter.x - (racewayWidth / 2) + LARGE_RADIUS;
        int maxX = racewayCenter.x + (racewayWidth / 2) - LARGE_RADIUS;
        int minY = racewayCenter.y - (racewayHeight / 2) - LARGE_RADIUS; // Spawn above the raceway
        int maxY = racewayCenter.y + (racewayHeight / 2) - LARGE_RADIUS; // Optionally, spawn within or beyond raceway

        // Randomly select a spawn position within the boundaries
        int x = Game.R.nextInt(maxX - minX + 1) + minX;
        int y = minY; // Spawning just above the raceway for cars to enter

        return new Point(x, 0);
    }





    // BOTh of these make sure there is an endless loop going on when spawning enemies. If an enemy is not on the screen
    // a new one will pop up
    private boolean enemyOnScreen() {
        //if there are no more Cars on the screen
        boolean enemyFree = true;
        for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
            if (movFoe instanceof EnemyCars) {
                enemyFree = false;
                break;
            }
        }
//        System.out.println(enemyFree);
        return enemyFree;
    }

    private void keepSpawningEnemies() {

        //short-circuit if level not yet cleared
        if (!enemyOnScreen()) {
//            CommandCenter.getInstance().setGameState(CommandCenter.GameState.GAME_OVER);
            return;
        }

        //center the falcon at each level-clear
        CommandCenter.getInstance().getUserCar().setCenter(new Point(Game.DIM.width / 2, (int)(Game.DIM.height * 0.8)));;

        //spawn some big new asteroids
        spawnEnemyCar(1);
    }

    // ===============================================
    // KEYLISTENER METHODS
    // ===============================================

    @Override
    public void keyPressed(KeyEvent e) {
        UserCar userCar = CommandCenter.getInstance().getUserCar();
        int keyCode = e.getKeyCode();
        switch (keyCode) {
//            case FIRE:
//                CommandCenter.getInstance().getOpsQueue().enqueue(new Bullet(userCar), GameOp.Action.ADD);
//                break;
//            case NUKE:
//                CommandCenter.getInstance().getOpsQueue().enqueue(new Nuke(userCar), GameOp.Action.ADD);
//                break;
            case TURBO:
                userCar.setThrusting(true);
                SoundLoader.playSound("whitenoise_loop.wav");
                break;
            case LEFT:
                userCar.setTurnState(UserCar.TurnState.LEFT);
                break;
            case RIGHT:
                userCar.setTurnState(UserCar.TurnState.RIGHT);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        UserCar userCar = CommandCenter.getInstance().getUserCar();
        int keyCode = e.getKeyCode();
        // Show the key-code in the console
        System.out.println(keyCode);

        CommandCenter.GameState gameState = CommandCenter.getInstance().getGameState();

        // Check if 'S' key is released to start or restart the game
        if (keyCode == ACCELERATE) { // 'S' key
            if (gameState == CommandCenter.GameState.START_SCREEN ||
                    gameState == CommandCenter.GameState.GAME_OVER ||
                    gameState == CommandCenter.GameState.TIME_UP) {
                // Start or restart the game
                CommandCenter.getInstance().initGame();
                CommandCenter.getInstance().setGameState(CommandCenter.GameState.PLAYING);
                gameTimer.start(); // Restart the timer
                return;
            }
        }

        // Ignore other inputs if the game is not in PLAYING state
        if (gameState != CommandCenter.GameState.PLAYING) {
            return;
        }

        switch (keyCode) {

            // Releasing either the LEFT or RIGHT arrow key will set the TurnState to IDLE
            case LEFT:
            case RIGHT:
                userCar.setTurnState(UserCar.TurnState.IDLE);
                break;
            case TURBO:
                userCar.setThrusting(false);
                SoundLoader.stopSound("whitenoise_loop.wav");
                break;
            case PAUSE:
                // Toggle pause state
                boolean paused = !gameTimer.isPaused();
                if (paused) {
                    gameTimer.pause();
                } else {
                    gameTimer.resume();
                }
                CommandCenter.getInstance().setPaused(paused);
                break;
            case QUIT:
                System.exit(0);
                break;
//            case RADAR:
//                // Toggle the radar display
//                CommandCenter.getInstance().setRadar(!CommandCenter.getInstance().isRadar());
//                break;
            case MUTE:
                // Toggle the music
                if (CommandCenter.getInstance().isThemeMusic()) {
                    SoundLoader.stopSound("dr_loop.wav");
                } else {
                    SoundLoader.playSound("dr_loop.wav");
                }
                CommandCenter.getInstance().setThemeMusic(!CommandCenter.getInstance().isThemeMusic());
                break;
            default:
                break;
        }
    }


    @Override
    // does nothing, but we need it b/c of KeyListener contract
    public void keyTyped(KeyEvent e) {
    }


}


