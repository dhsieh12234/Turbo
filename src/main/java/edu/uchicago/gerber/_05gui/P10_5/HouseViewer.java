package edu.uchicago.gerber._05gui.P10_5;

import edu.uchicago.gerber._05gui.P10_2.BullsEyeComponent;

import javax.swing.*;

public class HouseViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setTitle("House");
        JComponent component = new HouseComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
