package edu.uchicago.gerber._05gui.P10_9;

import javax.swing.*;
import java.awt.*;

public class FlagComponent extends JComponent {
    public void paintComponent(Graphics g) {
        drawFlag(g, Color.black ,Color.red,Color.yellow, 100, 100);
        drawFlag(g, Color.red ,Color.white,Color.green, 100, 300);
    }
    public void drawFlag(Graphics g, Color color1, Color color2, Color color3, int x, int y) {

        int rightX = x + 200;
        int bottomY = y + 120;

        g.setColor(color1);
        g.fillRect(x,y,200,40);
        g.setColor(color2);
        g.fillRect(x,y+40,200,40);
        g.setColor(color3);
        g.fillRect(x,y + 80,200,40);

        g.setColor(Color.black);
        g.drawLine(x, y, rightX, y);
        g.drawLine(x, bottomY, rightX, bottomY);
        g.drawLine(x, y, x,bottomY);
        g.drawLine(rightX,y, rightX,bottomY);
    }
}
