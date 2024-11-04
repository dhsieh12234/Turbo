package edu.uchicago.gerber._05gui.P11_9;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleComponent extends JComponent {
    private Point centerPoint = null;
    private Point peripheryPoint = null;
    private boolean centerSet = false;

    public CircleComponent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!centerSet) {
                    centerPoint = e.getPoint();
                    centerSet = true;
                } else {
                    peripheryPoint = e.getPoint();
                    centerSet = false;
                    repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (centerPoint != null && peripheryPoint != null) {
            int radius = (int) centerPoint.distance(peripheryPoint);
            g.setColor(Color.BLUE);
            g.drawOval(centerPoint.x - radius, centerPoint.y - radius, 2 * radius, 2 * radius);
        }
    }

}
