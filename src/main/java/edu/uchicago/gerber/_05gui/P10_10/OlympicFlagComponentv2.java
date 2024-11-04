package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;
import java.awt.*;

public class OlympicFlagComponentv2 extends JComponent {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawRing(g2d,50,100,Color.blue);
        drawRing(g2d,155,100,Color.black);
        drawRing(g2d,260,100,Color.red);
        drawRing(g2d,96,150,Color.yellow);
        drawRing(g2d,206,150,Color.green);
    }

    public void drawRing(Graphics2D g2d, int positionX, int positionY, Color color) {
        g2d.setColor(color);

        g2d.setStroke(new BasicStroke(8));

        g2d.drawOval(positionX, positionY, 100, 100);

    }
}
