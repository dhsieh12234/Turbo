package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;

public class OlympicFlagViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Olympic Flag");
        frame.setSize(400,400);
        frame.setTitle("Olympic Flag");
        JComponent component = new OlympicFlagComponent();
        frame.add(component);

        frame.setVisible(true);
    }

}
