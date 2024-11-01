package edu.uchicago.gerber._05gui.P10_5;

import javax.swing.*;
import java.awt.*;

public class HouseComponent extends JComponent {
    public void drawHouse(Graphics g, int x, int y, int width, int height, Color color) {
        //House
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(Color.black);
        g.drawRect(x, y, width, height);

        //Roof
        g.setColor(Color.black);
        int RoofHeight = height / 2;
        int[] roofX = { x, x + width / 2, x + width };
        int[] roofY = { y, y - RoofHeight,  y};
        g.drawPolygon(roofX, roofY, 3);

        g.setColor(color);
        g.fillPolygon(roofX, roofY, 3);

        //Door
        g.setColor(Color.black);
        int doorWidth = width / 4;
        int doorHeight = height / 2;
        int doorX = x + width / 4;
        int doorY = y + height - doorHeight;
        g.drawRect(doorX, doorY, doorWidth, doorHeight);

        g.setColor(Color.white);
        g.fillRect(doorX, doorY, doorWidth, doorHeight);

        //Window
        g.setColor(Color.black);
        int windowLength = width / 5;
        int windowX = x + (3 * width / 4) - (windowLength / 2);
        int windowY = doorY + doorHeight / 4 - windowLength / 2;
        g.drawRect(windowX, windowY, windowLength, windowLength);

        g.setColor(Color.white);
        g.fillRect(windowX, windowY, windowLength, windowLength);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawHouse(g, 50, 200, 100, 100, Color.green);   // Small house
//        drawHouse(g, 200, 200, 150, 150, Color.red);  // Medium house
//        drawHouse(g, 400, 200, 200, 200, Color.blue);  // Large house
    }
}
