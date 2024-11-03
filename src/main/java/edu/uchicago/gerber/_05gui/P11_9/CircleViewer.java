package edu.uchicago.gerber._05gui.P11_9;

import javax.swing.*;


public class CircleViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle");
        frame.setSize(500,500);
        frame.setTitle("Circle");
        JComponent component = new CircleComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
