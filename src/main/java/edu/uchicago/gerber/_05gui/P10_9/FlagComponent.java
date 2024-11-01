package edu.uchicago.gerber._05gui.P10_9;

import javax.swing.*;
import java.awt.*;

public class FlagComponent extends JComponent {
    public void paintComponent(Graphics g) {
        drawFlag(g, Color.black ,Color.red,Color.yellow);
        drawFlag(g, Color.red ,Color.white,Color.green);
    }
    public void drawFlag(Graphics g, Color color1, Color color2, Color color3) {

        int leftX = 100;
        int rightX = 300;
        int topY = 100;
        int bottomY = 220;

        g.setColor(color1);
        g.fillRect(100,100,200,40);
        g.setColor(color2);
        g.fillRect(100,140,200,40);
        g.setColor(color3);
        g.fillRect(100,180,200,40);

        g.setColor(Color.black);
        g.drawLine(leftX, topY, rightX, topY);
        g.drawLine(leftX, bottomY, rightX, bottomY);
        g.drawLine(leftX, topY, leftX,bottomY);
        g.drawLine(rightX,topY, rightX,bottomY);
    }
}
