package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;

public class OlympicFlagViewerv2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Olympic Flagv2");
        frame.setSize(400,400);
        frame.setTitle("Olympic Flag");
        JComponent component = new OlympicFlagComponentv2();
        frame.add(component);

        frame.setVisible(true);
    }

}
