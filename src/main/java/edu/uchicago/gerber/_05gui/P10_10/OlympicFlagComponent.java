package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;
import java.awt.*;

public class OlympicFlagComponent extends JComponent {
    public void paintComponent(Graphics g) {
        drawRing(g,50,100,Color.blue);
        drawRing(g,150,100,Color.black);
        drawRing(g,250,100,Color.red);
        drawRing(g,100,150,Color.yellow);
        drawRing(g,200,150,Color.green);
    }

    public void drawRing(Graphics g, int positionX, int positionY, Color color) {
        g.setColor(color);
        g.fillOval(positionX , positionY, 100, 100);

//        Color transparentColor = new Color(255, 255, 255, 0);
        g.setColor(Color.white);
        g.fillOval(positionX + 10, positionY + 10, 80, 80);

    }
}
