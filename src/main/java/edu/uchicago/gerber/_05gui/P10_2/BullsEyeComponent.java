package edu.uchicago.gerber._05gui.P10_2;

import javax.swing.*;
import java.awt.*;

public class BullsEyeComponent extends JComponent {
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(20, 10, 350, 350);
        g.setColor(Color.WHITE);
        g.fillOval(57, 47, 275, 275);
        g.setColor(Color.BLACK);
        g.fillOval(94, 84, 200, 200);
        g.setColor(Color.WHITE);
        g.fillOval(131, 121, 125, 125);
        g.setColor(Color.BLACK);
        g.fillOval(168, 158, 50, 50);
    }
}
