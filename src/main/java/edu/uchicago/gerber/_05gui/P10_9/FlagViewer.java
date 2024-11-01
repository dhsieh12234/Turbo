package edu.uchicago.gerber._05gui.P10_9;

import edu.uchicago.gerber._05gui.P10_5.HouseComponent;

import javax.swing.*;

public class FlagViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flag");
        frame.setSize(400,400);
        frame.setTitle("Flag");
        JComponent component = new FlagComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
