package edu.uchicago.gerber._08final.turbo.mvc.view;

import edu.uchicago.gerber._08final.turbo.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Game;
import edu.uchicago.gerber._08final.turbo.mvc.controller.Utils;
import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;
import edu.uchicago.gerber._08final.turbo.mvc.model.prime.PolarPoint;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;


public class GamePanel extends Panel {

    // ==============================================================
    // FIELDS
    // ==============================================================
    private final Font fontNormal = new Font("SansSerif", Font.BOLD, 12);
    private final Font fontBig = new Font("SansSerif", Font.BOLD + Font.ITALIC, 36);
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private FontMetrics fontMetrics;
    private int fontWidth;
    private int fontHeight;

    //used to draw number of ships remaining
    private final Point[] pntShipsRemaining;

    //used for double-buffering
    private Image imgOff;
    private Graphics grpOff;

    private final Game game;

    // ==============================================================
    // CONSTRUCTOR
    // ==============================================================

    public GamePanel(Dimension dim, Game game) {
        this.game = game;

        GameFrame gameFrame = new GameFrame();

        gameFrame.getContentPane().add(this);

        // Robert Alef's awesome falcon design
        List<Point> listShip = new ArrayList<>();
        listShip.add(new Point(0,9));
        listShip.add(new Point(-1, 6));
        listShip.add(new Point(-1,3));
        listShip.add(new Point(-4, 1));
        listShip.add(new Point(4,1));
        listShip.add(new Point(-4,1));
        listShip.add(new Point(-4, -2));
        listShip.add(new Point(-1, -2));
        listShip.add(new Point(-1, -9));
        listShip.add(new Point(-1, -2));
        listShip.add(new Point(-4, -2));
        listShip.add(new Point(-10, -8));
        listShip.add(new Point(-5, -9));
        listShip.add(new Point(-7, -11));
        listShip.add(new Point(-4, -11));
        listShip.add(new Point(-2, -9));
        listShip.add(new Point(-2, -10));
        listShip.add(new Point(-1, -10));
        listShip.add(new Point(-1, -9));
        listShip.add(new Point(1, -9));
        listShip.add(new Point(1, -10));
        listShip.add(new Point(2, -10));
        listShip.add(new Point(2, -9));
        listShip.add(new Point(4, -11));
        listShip.add(new Point(7, -11));
        listShip.add(new Point(5, -9));
        listShip.add(new Point(10, -8));
        listShip.add(new Point(4, -2));
        listShip.add(new Point(1, -2));
        listShip.add(new Point(1, -9));
        listShip.add(new Point(1, -2));
        listShip.add(new Point(4,-2));
        listShip.add(new Point(4, 1));
        listShip.add(new Point(1, 3));
        listShip.add(new Point(1,6));
        listShip.add(new Point(0,9));


        pntShipsRemaining = listShip.toArray(new Point[0]);

        gameFrame.pack();
        initFontInfo();
        gameFrame.setSize(dim);
        //change the name of the game-frame to your game name
        gameFrame.setTitle("TURBO");
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        setFocusable(true);
    }


    // ==============================================================
    // METHODS
    // ==============================================================

    private void drawFalconStatus(final Graphics graphics){

        graphics.setColor(Color.white);
        graphics.setFont(fontNormal);
        final int OFFSET_LEFT = 220;
        final int OFFSET_TOP = fontHeight + 10;

        // Draw the number of cars passed in the upper-left corner
        Font largeFont = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(largeFont);

        int carsPassed = CommandCenter.getInstance().getCarsPassed();
        String carsPassedText = "Cars Passed: " + carsPassed;

        int yPosition = OFFSET_TOP + 50; // Adjust this value to move the text further down
        graphics.drawString(carsPassedText, OFFSET_LEFT, yPosition);



        //draw the level upper-right corner
        // Set a larger font for the text

        // Draw the target cars passed in the upper-right corner
        String targetText = "Target Cars: " + decimalFormat.format(CommandCenter.getInstance().getCAR_PASS_THRESHOLD());
        graphics.drawString(targetText, Game.DIM.width - OFFSET_LEFT, yPosition);

//        String levelText = "Level : [" + CommandCenter.getInstance().getLevel() + "]  " +
//        CommandCenter.getInstance().getUniverse().toString().replace('_', ' ');
//        graphics.drawString(levelText, Game.DIM.width - OFFSET_LEFT, fontHeight); //upper-right corner
//        graphics.drawString("Target : " + decimalFormat.format(CommandCenter.getInstance().getCAR_PASS_THRESHOLD()),
//                Game.DIM.width - OFFSET_LEFT,
//                fontHeight * 2);

        //build the status string array with possible messages in middle of screen
        List<String> statusArray = new ArrayList<>();
//        if (CommandCenter.getInstance().getUserCar().getShowLevel() > 0) statusArray.add(levelText);
//        if (CommandCenter.getInstance().getUserCar().isMaxSpeedAttained()) statusArray.add("WARNING - SLOW DOWN");
//        if (CommandCenter.getInstance().getUserCar().getNukeMeter() > 0) statusArray.add("PRESS F for NUKE");

            //draw the statusArray strings to middle of screen
        if (!statusArray.isEmpty())
            displayTextOnScreen(graphics, statusArray.toArray(new String[0]));



    }

    //this is used for development, you can remove it from your final game
    private void drawNumFrame(Graphics g) {
        g.setColor(Color.white);
        g.setFont(fontNormal);
        g.drawString("FRAME[JAVA]:" + CommandCenter.getInstance().getFrame(), fontWidth,
                Game.DIM.height  - (fontHeight + 22));

    }

    private void drawMeters(Graphics g){

        //will be a number between 0-100 inclusive
        int shieldValue =   CommandCenter.getInstance().getUserCar().getShield() / 2;
        int nukeValue = CommandCenter.getInstance().getUserCar().getNukeMeter() /6;

        drawOneMeter(g, Color.CYAN, 1, shieldValue);
        drawOneMeter(g, Color.YELLOW, 2, nukeValue);


    }

    private void drawOneMeter(Graphics g, Color color, int offSet, int percent) {

        int xVal = Game.DIM.width - (100 + 120 * offSet);
        int yVal = Game.DIM.height - 45;

        //draw meter
        g.setColor(color);
        g.fillRect(xVal, yVal, percent, 10);

        //draw gray box
        g.setColor(Color.DARK_GRAY);
        g.drawRect(xVal, yVal, 100, 10);
    }

    @Override
    public void update(Graphics g) {

        // The following "off" vars are used for the off-screen double-buffered image.
        imgOff = createImage(Game.DIM.width, Game.DIM.height);
        // Get its graphics context
        grpOff = imgOff.getGraphics();

        // Fill the entire off-screen image with black background
        grpOff.setColor(Color.BLACK);
        grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);

        // This is used for development, you may remove drawNumFrame() in your final game.
        drawNumFrame(grpOff);

        // Get the current game state
        CommandCenter.GameState gameState = CommandCenter.getInstance().getGameState();

        // Handle different game states
        if (gameState == CommandCenter.GameState.START_SCREEN) {
            int carsPassed = CommandCenter.getInstance().getCarsPassed();
            // Display the home screen
            displayTextOnScreen(grpOff,
                    "WELCOME TO THE GAME",
                    "YOU PASSED THE LEVEL",
                    "You passed the target of " + (CommandCenter.getInstance().getCAR_PASS_THRESHOLD() - 5) + " cars!",
                    "Press 'S' to Start to move to the Next Level",
                    "Use Arrow Keys to Move",
                    "Avoid Obstacles and Pass Enemy Cars");
        } else if (gameState == CommandCenter.GameState.PLAYING) {
            // Check if the game is paused
            if (CommandCenter.getInstance().isPaused()) {
                displayTextOnScreen(grpOff, "Game Paused");
            } else {
                // Game is playing and not paused
                moveDrawMovables(grpOff,
                        CommandCenter.getInstance().getMovBackground(),
                        CommandCenter.getInstance().getMovRaceway(),
                        CommandCenter.getInstance().getMovDebris(),
                        CommandCenter.getInstance().getMovFriends(),
                        CommandCenter.getInstance().getMovFoes(),
                        CommandCenter.getInstance().getMovFloaters()
                );

//                drawNumberShipsRemaining(grpOff);
                drawMeters(grpOff);
                drawFalconStatus(grpOff);
                drawTimer(grpOff);
            }
        } else if (gameState == CommandCenter.GameState.GAME_OVER || gameState == CommandCenter.GameState.TIME_UP) {

            int carsPassed = CommandCenter.getInstance().getCarsPassed();
            // Display game over screen
            displayTextOnScreen(grpOff,
                    "GAME OVER",
                    "You passed " + carsPassed + " cars!",
                    "Use the arrow keys to turn and thrust",
                    "Use the space bar to fire",
                    "'S' to Start",
                    "'P' to Pause",
                    "'Q' to Quit",
                    "'M' to toggle music",
                    "'A' to toggle radar"
            );
        }

        // After drawing all the movables or text on the offscreen-image, copy it in one fell-swoop to graphics context
        // of the game panel, and show it for ~40ms. If you attempt to draw sprites directly on the gamePanel, e.g.
        // without the use of a double-buffered off-screen image, you will see flickering.
        g.drawImage(imgOff, 0, 0, this);
    }


    // Add the drawTimer method
    private void drawTimer(Graphics g) {
        // Get the elapsed time from the game timer
        long elapsedTime = game.getGameTimer().getElapsedTime();

        // Convert to minutes and seconds
        int totalSeconds = (int) (elapsedTime / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // Format the time string
        String timeString = String.format("Time Left: %02d:%02d", minutes, seconds);

        // Set font and color
        g.setFont(fontBig);

        // Change color when time is less than 10 seconds
        if (elapsedTime <= 10_000) { // 10 seconds
            if ((elapsedTime / 500) % 2 == 0) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.YELLOW);
            }
        } else {
            g.setColor(Color.WHITE);
        }

        // Calculate the position to center the text at the top middle
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(timeString);
        int x = (Game.DIM.width - textWidth) / 2;
        int y = fm.getAscent() + 20; // Adjust y as needed

        // Draw the string
        g.drawString(timeString, x, y);
    }


    //this method causes all sprites to move and draw themselves. This method takes a variable number of teams.
    @SafeVarargs
    private final void moveDrawMovables(final Graphics g, List<Movable>... teams) {

        for (List<Movable> team : teams) {
            for (Movable mov : team) {
                mov.move();
                mov.draw(g);
            }
        }

    }




    // Draw the number of falcons remaining on the bottom-right of the screen.
    private void drawNumberShipsRemaining(Graphics g) {
        int numFalcons = CommandCenter.getInstance().getNumFalcons();
        while (numFalcons > 1) {
            drawOneShip(g, numFalcons--);
        }
    }


    private void drawOneShip(Graphics g, int offSet) {

        g.setColor(Color.ORANGE);

        final int SHIP_RADIUS = 15;
        final int X_POS = Game.DIM.width - (27 * offSet);
        final int Y_POS = Game.DIM.height - 45;

        //the reason we convert to polar-points is that it's much easier to rotate polar-points.
        List<PolarPoint> polars = Utils.cartesiansToPolars(pntShipsRemaining);

        Function<PolarPoint, PolarPoint> rotatePolarBy90 =
                pp -> new PolarPoint(
                        pp.getR(),
                        pp.getTheta() + Math.toRadians(90.0) //rotated Theta
                );

        Function<PolarPoint, Point> polarToCartesian =
                pp -> new Point(
                        (int)  (pp.getR() * SHIP_RADIUS * Math.sin(pp.getTheta())),
                        (int)  (pp.getR() * SHIP_RADIUS * Math.cos(pp.getTheta())));

        Function<Point, Point> adjustForLocation =
                pnt -> new Point(
                        pnt.x + X_POS,
                        pnt.y + Y_POS);


        g.drawPolygon(

                polars.stream()
                        .map(rotatePolarBy90)
                        .map(polarToCartesian)
                        .map(adjustForLocation)
                        .map(pnt -> pnt.x)
                        .mapToInt(Integer::intValue)
                        .toArray(),

                polars.stream()
                        .map(rotatePolarBy90)
                        .map(polarToCartesian)
                        .map(adjustForLocation)
                        .map(pnt -> pnt.y)
                        .mapToInt(Integer::intValue)
                        .toArray(),

                polars.size());


    }

    private void initFontInfo() {
        Graphics g = getGraphics();            // get the graphics context for the panel
        g.setFont(fontNormal);                        // take care of some simple font stuff
        fontMetrics = g.getFontMetrics();
        fontWidth = fontMetrics.getMaxAdvance();
        fontHeight = fontMetrics.getHeight();
        g.setFont(fontBig);                    // set font info
    }


    // This method draws some text to the middle of the screen
    private void displayTextOnScreen(final Graphics graphics, String... lines) {

        //AtomicInteger is safe to pass into a stream
        final AtomicInteger spacer = new AtomicInteger(0);
        Arrays.stream(lines)
                .forEach(str ->
                            graphics.drawString(str, (Game.DIM.width - fontMetrics.stringWidth(str)) / 2,
                                    Game.DIM.height / 4 + fontHeight + spacer.getAndAdd(40))

                );


    }


}
